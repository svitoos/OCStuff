package svitoos.OCStuff.init;

import cpw.mods.fml.common.registry.GameRegistry;
import li.cil.oc.api.API;
import net.minecraft.item.ItemStack;
import svitoos.OCStuff.Config;
import svitoos.OCStuff.driver.DriverUpgradeItemCharger;
import svitoos.OCStuff.driver.DriverUpgradeUltimateGeolyzer;
import svitoos.OCStuff.driver.DriverUpgradeUltimateNavigation;
import svitoos.OCStuff.item.UpgradeItemCharger;
import svitoos.OCStuff.item.UpgradeUltimateGeolyzer;
import svitoos.OCStuff.item.UpgradeUltimateNavigation;

public final class Items {

  public static UpgradeUltimateNavigation ultimateNavigationUpgrade;
  public static UpgradeUltimateGeolyzer ultimateGeolyzerUpgrade;
  public static UpgradeItemCharger chargerUpgrade;

  private Items() {}

  public static void init() {
    ultimateNavigationUpgrade = new UpgradeUltimateNavigation();
    GameRegistry.registerItem(ultimateNavigationUpgrade, "ultimateNavigationUpgrade");
    API.driver.add(new DriverUpgradeUltimateNavigation());
    if (Config.regRecipeUpgradeUltimateNavigation) {
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
    API.driver.add(new DriverUpgradeUltimateGeolyzer());
    if (Config.regRecipeUpgradeUltimateGeolyzer) {
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

    chargerUpgrade = new UpgradeItemCharger();
    GameRegistry.registerItem(chargerUpgrade, "chargerUpgrade");
    API.driver.add(new DriverUpgradeItemCharger());
  }
}
