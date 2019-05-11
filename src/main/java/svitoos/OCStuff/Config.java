package svitoos.OCStuff;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {
  private static Configuration configuration;

  public static boolean regRecipeUpgradeUltimateNavigation;
  public static boolean regRecipeUpgradeUltimateGeolyzer;

  public static double geolyzerCostPerRange;

  public static boolean itemChargerUpgradeRecipe;
  public static int itemChargerUpgradeTier;

  public static boolean integrationIndustrialCraft2;

  public static boolean cropnalyzerUpgradeEnabled;
  public static boolean cropnalyzerUpgradeRecipe;
  public static int cropnalyzerUpgradeTier;
  public static double[] cropnalyzerScanCost;

  static void init(File file) {
    configuration = new Configuration(file, true);
    configuration.load();

    regRecipeUpgradeUltimateNavigation =
        configuration.getBoolean(
            "UpgradeUltimateNavigation",
            "recipe",
            true,
            "Add recipe for Ultimate Navigation Upgrade");

    regRecipeUpgradeUltimateGeolyzer =
        configuration.getBoolean(
            "UpgradeUltimateGeolyzer", "recipe", true, "Add recipe for Ultimate Geolyzer Upgrade");

    geolyzerCostPerRange =
        configuration
            .get(
                "geolyzer",
                "costPerRange",
                1.0,
                "How much energy is consumed when the Geolyzer analyzes a ranged block. (geolyzerScan + range * costPerRange)")
            .getDouble();

    itemChargerUpgradeRecipe = configuration.getBoolean("UpgradeItemCharger", "recipe", true, "");
    itemChargerUpgradeTier = configuration.getInt("UpgradeItemCharger", "tier", 1, 0, 2, "");

    integrationIndustrialCraft2 =
        configuration.getBoolean("IndustrialCraft2", "integration", true, "");

    cropnalyzerUpgradeEnabled = configuration.getBoolean("UpgradeCropnalyzer", "item", true, "");
    cropnalyzerUpgradeRecipe =
        configuration.getBoolean(
            "UpgradeCropnalyzer", "recipe", true, "Add recipe for Cropnalyzer Upgrade");
    cropnalyzerUpgradeTier = configuration.getInt("UpgradeCropnalyzer", "tier", 1, 0, 2, "");

    cropnalyzerScanCost =
        configuration
            .get(
                "cropnalyzer",
                "scanCost",
                new double[] {0, 40, 360, 3600},
                "[Default: 0 40 360 3600]")
            .getDoubleList();

    configuration.save();
  }
}
