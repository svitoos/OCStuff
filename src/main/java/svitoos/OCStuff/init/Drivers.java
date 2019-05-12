package svitoos.OCStuff.init;

import li.cil.oc.api.API;
import svitoos.OCStuff.Mods;
import svitoos.OCStuff.driver.DriverUpgradeEnderlink;
import svitoos.OCStuff.driver.DriverUpgradeItemCharger;
import svitoos.OCStuff.driver.DriverUpgradeUltimateGeolyzer;
import svitoos.OCStuff.driver.DriverUpgradeUltimateNavigation;
import svitoos.OCStuff.driver.ic2.ConvertCropSeedItem;
import svitoos.OCStuff.driver.ic2.DriverCropnalyzer;

public final class Drivers {
  public static void init() {
    API.driver.add(new DriverUpgradeUltimateNavigation());
    API.driver.add(new DriverUpgradeUltimateGeolyzer());
    API.driver.add(new DriverUpgradeItemCharger());
    API.driver.add(new DriverUpgradeEnderlink());
    if (Mods.IndustrialCraft2()) {
      API.driver.add(new ConvertCropSeedItem());
      API.driver.add(new DriverCropnalyzer());
    }
  }
}
