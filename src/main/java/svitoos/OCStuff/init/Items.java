package svitoos.OCStuff.init;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import svitoos.OCStuff.Config;
import svitoos.OCStuff.Mods;
import svitoos.OCStuff.item.UpgradeUltimateGeolyzer;
import svitoos.OCStuff.item.UpgradeUltimateNavigation;
import svitoos.OCStuff.item.UpgradeEnderlink;
import svitoos.OCStuff.item.UpgradeItemCharger;
import svitoos.OCStuff.item.UpgradeCropnalyzer;
import svitoos.OCStuff.util.RecipeUtils;

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
  }

  public static void register(IForgeRegistry<Item> registry) {
    registry.register(ultimateNavigationUpgrade);
    if (Config.ultimateNavigationUpgradeRecipe) {
      RecipeUtils.addShapedRecipe(
          new ItemStack(ultimateNavigationUpgrade),
          "xgx",
          "cic",
          "xpx",
          'g',
          net.minecraft.init.Items.MAP,
          'x',
          "ingotGold",
          'c',
          "oc:circuitChip2",
          'i',
          net.minecraft.init.Items.ENDER_EYE,
          'p',
          "oc:materialCircuitBoardPrinted");
    }

    registry.register(ultimateGeolyzerUpgrade);
    if (Config.ultimateGeolyzerUpgradeRecipe) {
      RecipeUtils.addShapedRecipe(
          new ItemStack(ultimateGeolyzerUpgrade),
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

    registry.register(enderlinkUpgrade);
    if (Config.enderlinkUpgradeRecipe) {
      RecipeUtils.addShapedRecipe(
          new ItemStack(enderlinkUpgrade),
          "xgx",
          "cic",
          "xpx",
          'g',
          "oc:inventoryControllerUpgrade",
          'x',
          net.minecraft.init.Items.ENDER_EYE,
          'c',
          "oc:circuitChip3",
          'i',
          "oc:materialInterweb",
          'p',
          "oc:materialCircuitBoardPrinted");
    }

    registry.register(itemChargerUpgrade);
    if (Config.itemChargerUpgradeRecipe) {
      RecipeUtils.addShapedRecipe(
          new ItemStack(itemChargerUpgrade),
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
      svitoos.OCStuff.init.ic2.Items.register(registry);
    }
  }
}
