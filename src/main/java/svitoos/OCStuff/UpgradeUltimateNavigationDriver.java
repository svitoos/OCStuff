package svitoos.OCStuff;

import li.cil.oc.api.driver.item.HostAware;
import li.cil.oc.api.driver.item.Slot;
import li.cil.oc.api.internal.Drone;
import li.cil.oc.api.network.EnvironmentHost;
import li.cil.oc.api.network.ManagedEnvironment;
import li.cil.oc.api.prefab.DriverItem;
import net.minecraft.item.ItemStack;

public class UpgradeUltimateNavigationDriver extends DriverItem implements HostAware {

  UpgradeUltimateNavigationDriver() {
    super(new ItemStack(OCStuff.itemUltimateNavigationUpgrade));
  }

  @Override
  public ManagedEnvironment createEnvironment(ItemStack stack, EnvironmentHost host) {
    return new UpgradeUltimateNavigationEnv(host);
  }

  @Override
  public String slot(ItemStack stack) {
    return Slot.Upgrade;
  }

  @Override
  public boolean worksWith(ItemStack stack, Class<? extends EnvironmentHost> host) {
    return worksWith(stack)
        && (isRobot(host) || Drone.class.isAssignableFrom(host));
  }

  @Override
  public int tier(ItemStack stack) {
    return 0;
  }
}
