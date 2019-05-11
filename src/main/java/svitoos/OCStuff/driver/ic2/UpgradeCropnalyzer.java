package svitoos.OCStuff.driver.ic2;

import ic2.api.crops.Crops;
import ic2.core.item.ItemCropSeed;
import java.util.HashMap;
import java.util.Map;
import li.cil.oc.api.Network;
import li.cil.oc.api.driver.DeviceInfo;
import li.cil.oc.api.internal.Agent;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.Connector;
import li.cil.oc.api.network.EnvironmentHost;
import li.cil.oc.api.network.Visibility;
import li.cil.oc.api.prefab.ManagedEnvironment;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import svitoos.OCStuff.Config;

public class UpgradeCropnalyzer extends ManagedEnvironment implements DeviceInfo {

  private static final Map<String, String> deviceInfo;

  static {
    deviceInfo = new HashMap<>();
    deviceInfo.put(DeviceAttribute.Class, DeviceClass.Generic);
    deviceInfo.put(DeviceAttribute.Description, "Cropnalyzer");
    deviceInfo.put(DeviceAttribute.Vendor, "IndustrialCraft, Inc.");
    deviceInfo.put(DeviceAttribute.Product, "Cropnalyzer-Adapter-X1");
  }

  @Override
  public Map<String, String> getDeviceInfo() {
    return deviceInfo;
  }

  private final EnvironmentHost host;

  public UpgradeCropnalyzer(EnvironmentHost host) {
    this.host = host;
    setNode(
        Network.newNode(this, Visibility.Network)
            .withComponent("cropnalyzer")
            .withConnector()
            .create());
  }

  @Callback(
      doc =
          "analyze():boolean -- Analyzes the seeds into selected slot. Returns true if seeds are analyzed")
  public Object[] analyze(Context context, Arguments arguments) {
    Agent agent = (Agent) host;
    ItemStack stack = agent.mainInventory().getStackInSlot(agent.selectedSlot());
    if (stack.getItem() instanceof ItemCropSeed && stack.getTagCompound() != null) {
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

  @Callback(doc="Returns the humidity bonus for a biome.")
  public Object[] getHumidityBiomeBonus() {
    return new Object[] {false, Crops.instance.getHumidityBiomeBonus(getBiome())};
  }

  @Callback(doc="Returns the nutrient bonus for a biome.")
  public Object[] getNutrientBiomeBonus() {
    return new Object[] {false, Crops.instance.getNutrientBiomeBonus(getBiome())};
  }

  private BiomeGenBase getBiome() {
    return host.world()
        .getBiomeGenForCoords(
            (int) Math.floor(host.xPosition()), (int) Math.floor(host.zPosition()));
  }
}
