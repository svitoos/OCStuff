package svitoos.OCStuff.item;

import li.cil.oc.api.driver.item.Slot;
import li.cil.oc.api.network.EnvironmentHost;
import li.cil.oc.api.network.ManagedEnvironment;
import net.minecraft.item.ItemStack;
import svitoos.OCStuff.Config;
import svitoos.OCStuff.Mods;

public class UpgradeCropnalyzer extends ItemWithDriver {

  public UpgradeCropnalyzer() {
    super("upgrade_cropnalyzer");
  }

  @Override
  public boolean isEnabled() {
    return Mods.IndustrialCraft2() && Config.cropnalyzerUpgradeEnabled;
  }

  @Override
  public ManagedEnvironment createEnvironment(ItemStack stack, EnvironmentHost host) {
    return new svitoos.OCStuff.driver.ic2.UpgradeCropnalyzer(host);
  }

  @Override
  public String slot(ItemStack stack) {
    return Slot.Upgrade;
  }

  @Override
  public int tier(ItemStack stack) {
    return Config.cropnalyzerUpgradeTier;
  }

  @Override
  public boolean worksWith(ItemStack stack, Class<? extends EnvironmentHost> host) {
    return worksWith(stack) && (isRobot(host) || isDrone(host));
  }

  @Override
  public Class<?> getEnvironment(ItemStack stack) {
    return worksWith(stack) ? svitoos.OCStuff.driver.ic2.UpgradeCropnalyzer.class : null;
  }
}
