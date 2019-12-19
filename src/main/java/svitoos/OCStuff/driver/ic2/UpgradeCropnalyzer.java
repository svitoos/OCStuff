package svitoos.OCStuff.driver.ic2;

import ic2.api.crops.Crops;
import ic2.core.item.ItemCropSeed;
import li.cil.oc.api.Network;
import li.cil.oc.api.internal.Agent;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.Connector;
import li.cil.oc.api.network.EnvironmentHost;
import li.cil.oc.api.network.Visibility;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import svitoos.OCStuff.Config;
import svitoos.OCStuff.driver.ManagedEnvironmentWithDeviceInfo;
import svitoos.OCStuff.util.OCUtils;
import svitoos.OCStuff.util.OCUtils.Device;
import svitoos.OCStuff.util.OCUtils.Vendors;

public class UpgradeCropnalyzer extends ManagedEnvironmentWithDeviceInfo {

  private final EnvironmentHost host;

  public UpgradeCropnalyzer(EnvironmentHost host) {
    this.host = host;
    setNode(
        Network.newNode(this, Visibility.Network)
            .withComponent("cropnalyzer", Visibility.Neighbors)
            .withConnector()
            .create());
  }

  @Override
  protected Device deviceInfo() {
    return new OCUtils.Device(
        DeviceClass.Generic, "Cropnalyzer", Vendors.IC2, "Cropnalyzer-Adapter-X1");
  }

  @Callback(
      doc =
          "function():boolean -- Analyzes the seeds into selected slot. Returns true if seeds are analyzed")
  public Object[] analyze(Context context, Arguments arguments) {
    Agent agent = (Agent) host;
    ItemStack stack = agent.mainInventory().getStackInSlot(agent.selectedSlot());
    if (stack != null && stack.getItem() instanceof ItemCropSeed) {
      final int scanLevel = Math.max(ItemCropSeed.getScannedFromStack(stack), 0);
      if (scanLevel < 4) {
        if (!((Connector) node()).tryChangeBuffer(-Config.cropnalyzerScanCost[scanLevel])) {
          return new Object[] {false, "not enough energy"};
        }
        ItemCropSeed.incrementScannedOfStack(stack);
      }
      return new Object[] {true};
    }
    return new Object[] {false, "not seeds"};
  }

  @Callback(doc = "function():number -- Returns the humidity bonus for a biome.")
  public Object[] getHumidityBiomeBonus(Context context, Arguments arguments) {
    return new Object[] {Crops.instance.getHumidityBiomeBonus(getBiome())};
  }

  @Callback(doc = "function():number -- Returns the nutrient bonus for a biome.")
  public Object[] getNutrientBiomeBonus(Context context, Arguments arguments) {
    return new Object[] {Crops.instance.getNutrientBiomeBonus(getBiome())};
  }

  private BiomeGenBase getBiome() {
    return host.world()
        .getBiomeGenForCoords(
            (int) Math.floor(host.xPosition()), (int) Math.floor(host.zPosition()));
  }
}
