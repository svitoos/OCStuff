package svitoos.OCStuff;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {
  private static Configuration configuration;

  public static boolean regRecipeUpgradeUltimateNavigation;

  static void init(File file) {
    configuration = new Configuration(file, true);
    configuration.load();
    regRecipeUpgradeUltimateNavigation =
        configuration.getBoolean(
            "UpgradeUltimateNavigation",
            "recipe",
            true,
            "Add recipe for Ultimate Navigation Upgrade");
    configuration.save();
  }
}
