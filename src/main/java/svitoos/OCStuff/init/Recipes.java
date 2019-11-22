package svitoos.OCStuff.init;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import svitoos.OCStuff.Config;
import svitoos.OCStuff.Mods;
import svitoos.OCStuff.util.RecipeUtils;

public final class Recipes {
  public static void add() {
    final Item ender_eye = net.minecraft.init.Items.ender_eye;
    final Item map = net.minecraft.init.Items.map;

    if (Config.ultimateNavigationUpgradeRecipe) {
      RecipeUtils.addShapedRecipe(
          new ItemStack(Items.ultimateNavigationUpgrade),
          "xgx",
          "cic",
          "xpx",
          'g',
          map,
          'x',
          "ingotGold",
          'c',
          "oc:circuitChip2",
          'i',
          ender_eye,
          'p',
          "oc:materialCircuitBoardPrinted");
    }

    if (Config.ultimateGeolyzerUpgradeRecipe) {
      RecipeUtils.addShapedRecipe(
          new ItemStack(Items.ultimateGeolyzerUpgrade),
          "xgx",
          "cic",
          "xpx",
          'g',
          "oc:geolyzer",
          'x',
          "ingotGold",
          'c',
          "oc:circuitChip3",
          'i',
          "oc:materialInterweb",
          'p',
          "oc:materialCircuitBoardPrinted");
    }

    if (Config.enderlinkUpgradeRecipe) {
      RecipeUtils.addShapedRecipe(
          new ItemStack(Items.enderlinkUpgrade),
          "xgx",
          "cic",
          "xpx",
          'g',
          "oc:inventoryControllerUpgrade",
          'x',
          ender_eye,
          'c',
          "oc:circuitChip3",
          'i',
          "oc:materialInterweb",
          'p',
          "oc:materialCircuitBoardPrinted");
    }

    if (Config.itemChargerUpgradeRecipe) {
      RecipeUtils.addShapedRecipe(
          new ItemStack(Items.itemChargerUpgrade),
          "x x",
          "cic",
          "xpx",
          'x',
          "gemDiamond",
          'c',
          "oc:circuitChip3",
          'i',
          "oc:charger",
          'p',
          "oc:materialCircuitBoardPrinted");
    }

    if (Mods.IndustrialCraft2()) {
      svitoos.OCStuff.init.ic2.Recipes.add();
    }
  }
}
