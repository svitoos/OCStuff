package svitoos.OCStuff.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import svitoos.OCStuff.Config;
import svitoos.OCStuff.Mods;
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
    GameRegistry.registerItem(ultimateNavigationUpgrade, "ultimateNavigationUpgrade");
    if (Config.ultimateNavigationUpgradeRecipe) {
      GameRegistry.addRecipe(
          new ItemStack(ultimateNavigationUpgrade),
          "xgx",
          "cic",
          "xpx",
          'g',
          net.minecraft.init.Items.map,
          'x',
          net.minecraft.init.Items.gold_ingot,
          'c',
          li.cil.oc.api.Items.get("chip2").createItemStack(1),
          'i',
          net.minecraft.init.Items.ender_eye,
          'p',
          li.cil.oc.api.Items.get("printedCircuitBoard").createItemStack(1));
    }

    ultimateGeolyzerUpgrade = new UpgradeUltimateGeolyzer();
    GameRegistry.registerItem(ultimateGeolyzerUpgrade, "ultimateGeolyzerUpgrade");
    if (Config.ultimateGeolyzerUpgradeRecipe) {
      GameRegistry.addRecipe(
          new ItemStack(ultimateGeolyzerUpgrade),
          "xgx",
          "cic",
          "xpx",
          'g',
          li.cil.oc.api.Items.get("geolyzer").createItemStack(1),
          'x',
          net.minecraft.init.Items.gold_ingot,
          'c',
          li.cil.oc.api.Items.get("chip3").createItemStack(1),
          'i',
          li.cil.oc.api.Items.get("interweb").createItemStack(1),
          'p',
          li.cil.oc.api.Items.get("printedCircuitBoard").createItemStack(1));
    }

    enderlinkUpgrade = new UpgradeEnderlink();
    GameRegistry.registerItem(enderlinkUpgrade, "enderlinkUpgrade");
    if (Config.enderlinkUpgradeRecipe) {
      GameRegistry.addRecipe(
          new ItemStack(enderlinkUpgrade),
          "xgx",
          "cic",
          "xpx",
          'g',
          li.cil.oc.api.Items.get("inventoryControllerUpgrade").createItemStack(1),
          'x',
          net.minecraft.init.Items.ender_eye,
          'c',
          li.cil.oc.api.Items.get("chip3").createItemStack(1),
          'i',
          li.cil.oc.api.Items.get("interweb").createItemStack(1),
          'p',
          li.cil.oc.api.Items.get("printedCircuitBoard").createItemStack(1));
    }

    itemChargerUpgrade = new UpgradeItemCharger();
    GameRegistry.registerItem(itemChargerUpgrade, "itemChargerUpgrade");
    if (Config.itemChargerUpgradeRecipe) {
      GameRegistry.addRecipe(
          new ItemStack(itemChargerUpgrade),
          "x x",
          "cic",
          "xpx",
          'x',
          net.minecraft.init.Items.diamond,
          'c',
          li.cil.oc.api.Items.get("chip3").createItemStack(1),
          'i',
          li.cil.oc.api.Items.get("charger").createItemStack(1),
          'p',
          li.cil.oc.api.Items.get("printedCircuitBoard").createItemStack(1));
    }

    if (Mods.IndustrialCraft2()) {
      svitoos.OCStuff.init.ic2.Items.init();
    }

  }
}
