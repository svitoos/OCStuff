package svitoos.OCStuff;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {
  private static Configuration configuration;

  public static boolean regRecipeUpgradeUltimateNavigation;
  public static boolean regRecipeUpgradeUltimateGeolyzer;

  public static double geolyzerCostPerRange;

  public static double enderlinkTransferCost;

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
                "costPerRange",
                "geolyzer",
                1.0,
                "How much energy is consumed when the Geolyzer analyzes a ranged block. (geolyzerScan + range * costPerRange)")
            .getDouble();
    configuration.save();

    enderlinkTransferCost =
        configuration
            .get(
                "enderlink",
                "transferCost",
                100.0,
                "How much energy is consumed when the Enderlink transfers item/fluid.")
            .getDouble();
  }
}
