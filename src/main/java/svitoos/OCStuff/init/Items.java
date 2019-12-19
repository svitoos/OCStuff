package svitoos.OCStuff.init;

import cpw.mods.fml.common.registry.GameRegistry;
import java.util.ArrayList;
import java.util.List;
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

  public static List<Item> regItems = new ArrayList<>();

  private Items() {}

  public static void init() {
    ultimateNavigationUpgrade = new UpgradeUltimateNavigation();
    ultimateGeolyzerUpgrade = new UpgradeUltimateGeolyzer();
    enderlinkUpgrade = new UpgradeEnderlink();
    itemChargerUpgrade = new UpgradeItemCharger();
    cropnalyzerUpgrade = new UpgradeCropnalyzer();
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
      regItems.add(item);
    }
  }
}
