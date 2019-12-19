package svitoos.OCStuff;

import java.io.File;
import net.minecraftforge.common.config.Configuration;

public class Config {
  public static boolean ultimateNavigationUpgradeEnabled;
  public static boolean ultimateNavigationUpgradeRecipe;
  public static int ultimateNavigationUpgradeTier;
  public static boolean ultimateGeolyzerUpgradeEnabled;
  public static boolean ultimateGeolyzerUpgradeRecipe;
  public static int ultimateGeolyzerUpgradeTier;
  public static double geolyzerCostPerRange;
  public static boolean enderlinkUpgradeEnabled;
  public static boolean enderlinkUpgradeRecipe;
  public static int enderlinkUpgradeTier;
  public static double enderlinkTransferCost;
  public static boolean itemChargerUpgradeEnabled;
  public static boolean itemChargerUpgradeRecipe;
  public static int itemChargerUpgradeTier;
  public static boolean integrationIndustrialCraft2;
  public static boolean cropnalyzerUpgradeEnabled;
  public static boolean cropnalyzerUpgradeRecipe;
  public static int cropnalyzerUpgradeTier;
  public static double[] cropnalyzerScanCost;
  private static Configuration configuration;

  static void init(File file) {
    configuration = new Configuration(file, true);
    configuration.load();

    // Ultimate Navigation Upgrade

    ultimateNavigationUpgradeEnabled = getEnable("UpgradeUltimateNavigation");
    ultimateNavigationUpgradeRecipe = getRecipe("UpgradeUltimateNavigation");
    ultimateNavigationUpgradeTier = getTier("UpgradeUltimateNavigation", 0);

    // Ultimate Geolyzer Upgrade

    ultimateGeolyzerUpgradeEnabled = getEnable("UpgradeUltimateGeolyzer");
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

    enderlinkUpgradeEnabled = getEnable("UpgradeEnderlink");
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

    itemChargerUpgradeEnabled = getEnable("UpgradeItemCharger");
    itemChargerUpgradeRecipe = getRecipe("UpgradeItemCharger");
    itemChargerUpgradeTier = getTier("UpgradeItemCharger", 1);

    // IndustrialCraft2 integration

    integrationIndustrialCraft2 =
        configuration.getBoolean("IndustrialCraft2", "integration", true, "");

    // Cropnalyzer Upgrade

    cropnalyzerUpgradeEnabled = getEnable("UpgradeCropnalyzer");
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

  private static boolean getEnable(String name) {
    return configuration.getBoolean(name, "enable", true, "");
  }
}
