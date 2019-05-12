package svitoos.OCStuff.driver;

import li.cil.oc.api.driver.item.HostAware;
import li.cil.oc.api.driver.item.Slot;
import li.cil.oc.api.network.EnvironmentHost;
import li.cil.oc.api.network.ManagedEnvironment;
import li.cil.oc.api.prefab.DriverItem;
import net.minecraft.item.ItemStack;
import svitoos.OCStuff.Config;
import svitoos.OCStuff.init.Items;

public class DriverUpgradeUltimateNavigation extends DriverItem implements HostAware {

  public DriverUpgradeUltimateNavigation() {
    super(new ItemStack(Items.ultimateNavigationUpgrade));
  }

  @Override
  public ManagedEnvironment createEnvironment(ItemStack stack, EnvironmentHost host) {
    return new UpgradeUltimateNavigation(host);
  }

  @Override
  public String slot(ItemStack stack) {
    return Slot.Upgrade;
  }

  @Override
  public boolean worksWith(ItemStack stack, Class<? extends EnvironmentHost> host) {
    return worksWith(stack) && (isRotatable(host));
  }

  @Override
  public int tier(ItemStack stack) {
    return Config.ultimateNavigationUpgradeTier;
  }
}
