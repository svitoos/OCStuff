package svitoos.OCStuff.item;

import li.cil.oc.api.driver.item.Slot;
import li.cil.oc.api.network.EnvironmentHost;
import li.cil.oc.api.network.ManagedEnvironment;
import net.minecraft.item.ItemStack;
import svitoos.OCStuff.Config;

public class UpgradeItemCharger extends ItemWithDriver {

  public UpgradeItemCharger() {
    super("upgrade_item_charger");
  }

  @Override
  public boolean isEnabled() {
    return Config.itemChargerUpgradeEnabled;
  }

  @Override
  public ManagedEnvironment createEnvironment(ItemStack stack, EnvironmentHost host) {
    return new svitoos.OCStuff.driver.UpgradeItemCharger(host);
  }

  @Override
  public String slot(ItemStack stack) {
    return Slot.Upgrade;
  }

  @Override
  public int tier(ItemStack stack) {
    return Config.itemChargerUpgradeTier;
  }

  @Override
  public boolean worksWith(ItemStack stack, Class<? extends EnvironmentHost> host) {
    return worksWith(stack) && (isRobot(host) || isDrone(host));
  }

  @Override
  public Class<?> getEnvironment(ItemStack stack) {
    return worksWith(stack) ? svitoos.OCStuff.driver.UpgradeItemCharger.class : null;
  }
}
