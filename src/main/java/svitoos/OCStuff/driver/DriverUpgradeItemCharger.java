package svitoos.OCStuff.driver;

import li.cil.oc.api.driver.item.HostAware;
import li.cil.oc.api.driver.item.Slot;
import li.cil.oc.api.internal.Drone;
import li.cil.oc.api.network.EnvironmentHost;
import li.cil.oc.api.network.ManagedEnvironment;
import li.cil.oc.api.prefab.DriverItem;
import net.minecraft.item.ItemStack;
import svitoos.OCStuff.Config;
import svitoos.OCStuff.init.Items;

public class DriverUpgradeItemCharger extends DriverItem implements HostAware {

  public DriverUpgradeItemCharger() {
    super(new ItemStack(Items.itemChargerUpgrade));
  }

  @Override
  public boolean worksWith(ItemStack stack, Class<? extends EnvironmentHost> host) {
    return worksWith(stack) && (isRobot(host) || Drone.class.isAssignableFrom(host));
  }

  @Override
  public ManagedEnvironment createEnvironment(ItemStack stack, EnvironmentHost host) {
    return new UpgradeItemCharger(host);
  }

  @Override
  public String slot(ItemStack stack) {
    return Slot.Upgrade;
  }

  @Override
  public int tier(ItemStack stack) {
    return Config.itemChargerUpgradeTier;
  }
}
