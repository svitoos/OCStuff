package svitoos.OCStuff;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

class Config {
  private static Configuration configuration;

  static void init(File file) {
    configuration = new Configuration(file, true);
    configuration.load();
    configuration.save();
  }
}
