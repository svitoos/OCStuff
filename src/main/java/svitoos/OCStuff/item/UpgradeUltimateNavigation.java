package svitoos.OCStuff.item;

import li.cil.oc.api.driver.item.Slot;
import li.cil.oc.api.network.EnvironmentHost;
import li.cil.oc.api.network.ManagedEnvironment;
import net.minecraft.item.ItemStack;
import svitoos.OCStuff.Config;

public class UpgradeUltimateNavigation extends ItemWithDriver {

  public UpgradeUltimateNavigation() {
    super("upgrade_ultimate_navigation");
  }

  @Override
  public boolean isEnabled() {
    return Config.ultimateNavigationUpgradeEnabled;
  }

  @Override
  public ManagedEnvironment createEnvironment(ItemStack stack, EnvironmentHost host) {
    return new svitoos.OCStuff.driver.UpgradeUltimateNavigation(host);
  }

  @Override
  public String slot(ItemStack stack) {
    return Slot.Upgrade;
  }

  @Override
  public int tier(ItemStack stack) {
    return Config.ultimateNavigationUpgradeTier;
  }

  @Override
  public boolean worksWith(ItemStack stack, Class<? extends EnvironmentHost> host) {
    return worksWith(stack) && (isRotatable(host));
  }

  @Override
  public Class<?> getEnvironment(ItemStack stack) {
    return worksWith(stack) ? svitoos.OCStuff.driver.UpgradeUltimateNavigation.class : null;
  }
}
