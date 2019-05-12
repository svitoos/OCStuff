package svitoos.OCStuff.driver.ic2;

import li.cil.oc.api.driver.item.HostAware;
import li.cil.oc.api.internal.Drone;
import li.cil.oc.api.network.EnvironmentHost;
import li.cil.oc.api.network.ManagedEnvironment;
import li.cil.oc.api.prefab.DriverItem;
import li.cil.oc.common.Slot;
import net.minecraft.item.ItemStack;
import svitoos.OCStuff.Config;
import svitoos.OCStuff.Mods;
import svitoos.OCStuff.init.Items;

public class DriverCropnalyzer extends DriverItem implements HostAware {

  public DriverCropnalyzer() {
    super(new ItemStack(Items.cropnalyzerUpgrade));
  }

  @Override
  public boolean worksWith(ItemStack stack, Class<? extends EnvironmentHost> host) {
    return worksWith(stack) && (isRobot(host) || Drone.class.isAssignableFrom(host));
  }

  @Override
  public ManagedEnvironment createEnvironment(ItemStack stack, EnvironmentHost host) {
    if (Mods.IndustrialCraft2() && Config.cropnalyzerUpgradeEnabled) {
      return new UpgradeCropnalyzer(host);
    }
    return null;
  }

  @Override
  public String slot(ItemStack stack) {
    return Slot.Upgrade();
  }

  @Override
  public int tier(ItemStack stack) {
    return Config.cropnalyzerUpgradeTier;
  }
}
