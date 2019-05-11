package svitoos.OCStuff.driver.ic2.init;

import li.cil.oc.api.API;
import svitoos.OCStuff.Config;
import svitoos.OCStuff.driver.ic2.ConvertCropSeedItem;
import svitoos.OCStuff.driver.ic2.DriverCropnalyzer;

public final class Drivers {
  public static void init() {
    API.driver.add(new ConvertCropSeedItem());
    if (Config.cropnalyzerUpgradeEnabled) {
      API.driver.add(new DriverCropnalyzer());
    }
  }
}
