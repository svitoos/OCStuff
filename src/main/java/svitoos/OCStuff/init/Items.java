package svitoos.OCStuff.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import svitoos.OCStuff.Config;
import svitoos.OCStuff.Mods;
import svitoos.OCStuff.item.Item;
import svitoos.OCStuff.item.UpgradeUltimateGeolyzer;
import svitoos.OCStuff.item.UpgradeUltimateNavigation;
import svitoos.OCStuff.item.UpgradeEnderlink;
import svitoos.OCStuff.item.UpgradeItemCharger;
import svitoos.OCStuff.item.UpgradeCropnalyzer;

public final class Items {

  public static UpgradeUltimateNavigation ultimateNavigationUpgrade;
  public static UpgradeUltimateGeolyzer ultimateGeolyzerUpgrade;
  public static UpgradeEnderlink enderlinkUpgrade;
  public static UpgradeItemCharger itemChargerUpgrade;
  public static UpgradeCropnalyzer cropnalyzerUpgrade;

  private Items() {}

  public static void init() {
    ultimateNavigationUpgrade = new UpgradeUltimateNavigation();
    ultimateGeolyzerUpgrade = new UpgradeUltimateGeolyzer();
    enderlinkUpgrade = new UpgradeEnderlink();
    itemChargerUpgrade = new UpgradeItemCharger();

    if (Mods.IndustrialCraft2()) {
      cropnalyzerUpgrade = new UpgradeCropnalyzer();
    }
  }

  public static void registerItems() {
    registerItem(ultimateNavigationUpgrade);
    registerItem(ultimateGeolyzerUpgrade);
    registerItem(enderlinkUpgrade);
    registerItem(itemChargerUpgrade);
    registerItem(cropnalyzerUpgrade);
  }

  private static void registerItem(Item item) {
    if (item != null) {
      GameRegistry.registerItem(item, item.getItemId());
    }
  }
}
