package svitoos.OCStuff.driver;

import li.cil.oc.Settings;
import li.cil.oc.api.Network;
import li.cil.oc.api.internal.Agent;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.Connector;
import li.cil.oc.api.network.EnvironmentHost;
import li.cil.oc.api.network.Message;
import li.cil.oc.api.network.Visibility;
import li.cil.oc.integration.util.ItemCharge;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import svitoos.OCStuff.util.OCUtils;
import svitoos.OCStuff.util.OCUtils.Device;

public class UpgradeItemCharger extends ManagedEnvironmentWithDeviceInfo {

  private final EnvironmentHost host;
  private boolean charge;

  public UpgradeItemCharger(EnvironmentHost host) {
    this.host = host;
    setNode(
        Network.newNode(this, Visibility.Network)
            .withComponent("itemcharger", Visibility.Neighbors)
            .withConnector()
            .create());
  }

  @Override
  protected Device deviceInfo() {
    return new OCUtils.Device(
        DeviceClass.Generic, "Item Charger", OCUtils.Vendors.Scrag, "ItemCharger");
  }

  @Override
  public boolean canUpdate() {
    return true;
  }

  @Override
  public void update() {
    super.update();
    if (charge && host.world().getTotalWorldTime() % Settings.get().tickFrequency() == 0) {
      Agent agent = (Agent) host;
      ItemStack stack = agent.mainInventory().getStackInSlot(agent.selectedSlot());
      if (stack != null && stack.stackSize > 0 && ItemCharge.canCharge(stack)) {
        final double charge = Settings.get().chargeRateTablet() * Settings.get().tickFrequency();
        Connector connector = (Connector) node();
        if (Settings.get().ignorePower()
            || (charge > 0 && connector.globalBuffer() >= charge * 2)) {
          final double offered = charge + connector.changeBuffer(-charge);
          final double surplus = ItemCharge.charge(stack, offered);
          connector.changeBuffer(surplus);
        }
      }
    }
  }

  @Callback(
      doc =
          "function([enable:boolean]):boolean -- Activate or deactivate item charging. Returns whether the charger is currently charging item.")
  public Object[] charge(Context context, Arguments arguments) {
    if (arguments.count() > 0) {
      charge = arguments.checkBoolean(0);
    }
    return new Object[] {charge};
  }

  @Override
  public void load(NBTTagCompound nbt) {
    super.load(nbt);
    charge = nbt.getBoolean("charge");
  }

  @Override
  public void save(NBTTagCompound nbt) {
    super.save(nbt);
    nbt.setBoolean("charge", charge);
  }

  @Override
  public void onMessage(Message message) {
    super.onMessage(message);
    if (message.name().equals("computer.stopped")) {
      charge = false;
    }
  }
}
