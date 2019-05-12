package svitoos.OCStuff;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {
  private static Configuration configuration;

  public static boolean ultimateNavigationUpgradeRecipe;
  public static int ultimateNavigationUpgradeTier;

  public static boolean ultimateGeolyzerUpgradeRecipe;
  public static int ultimateGeolyzerUpgradeTier;

  public static double geolyzerCostPerRange;

  public static boolean enderlinkUpgradeRecipe;
  public static int enderlinkUpgradeTier;

  public static double enderlinkTransferCost;

  public static boolean itemChargerUpgradeRecipe;
  public static int itemChargerUpgradeTier;

  public static boolean integrationIndustrialCraft2;

  public static boolean cropnalyzerUpgradeRecipe;
  public static int cropnalyzerUpgradeTier;

  public static double[] cropnalyzerScanCost;

  static void init(File file) {
    configuration = new Configuration(file, true);
    configuration.load();

    // Ultimate Navigation Upgrade

    ultimateNavigationUpgradeRecipe = getRecipe("UpgradeUltimateNavigation");
    ultimateNavigationUpgradeTier = getTier("UpgradeUltimateNavigation", 0);

    // Ultimate Geolyzer Upgrade

    ultimateGeolyzerUpgradeRecipe = getRecipe("UpgradeUltimateGeolyzer");
    ultimateGeolyzerUpgradeTier = getTier("UpgradeUltimateGeolyzer", 0);

    geolyzerCostPerRange =
        configuration
            .get(
                "geolyzer",
                "costPerRange",
                1.0,
                "How much energy is consumed when the Geolyzer analyzes a ranged block. (geolyzerScan + range * costPerRange)")
            .getDouble();

    // Enderlink Upgrade

    enderlinkUpgradeRecipe = getRecipe("UpgradeEnderlink");
    enderlinkUpgradeTier = getTier("UpgradeEnderlnk", 2);

    enderlinkTransferCost =
        configuration
            .get(
                "enderlink",
                "transferCost",
                100.0,
                "How much energy is consumed when the Enderlink transfers item/fluid.")
            .getDouble();

    // Item Charger Upgrade

    itemChargerUpgradeRecipe = getRecipe("UpgradeItemCharger");
    itemChargerUpgradeTier = getTier("UpgradeItemCharger", 1);

    // IndustrialCraft2 integration

    integrationIndustrialCraft2 =
        configuration.getBoolean("IndustrialCraft2", "integration", true, "");

    // Cropnalyzer Upgrade

    cropnalyzerUpgradeRecipe = getRecipe("UpgradeCropnalyzer");
    cropnalyzerUpgradeTier = getTier("UpgradeCropnalyzer", 1);

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

  private static int getTier(String name, int defaultTier) {
    return configuration.getInt(name, "tier", defaultTier, 0, 2, "");
  }

  private static boolean getRecipe(String name) {
    return configuration.getBoolean(name, "recipe", true, "");
  }
}
