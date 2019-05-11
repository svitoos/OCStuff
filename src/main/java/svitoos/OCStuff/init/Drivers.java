package svitoos.OCStuff.init;

import li.cil.oc.api.API;
import svitoos.OCStuff.Mods;
import svitoos.OCStuff.driver.DriverUpgradeItemCharger;

public final class Drivers {
  public static void init() {
    API.driver.add(new DriverUpgradeItemCharger());
    if (Mods.IndustrialCraft2()) {
      svitoos.OCStuff.driver.ic2.init.Drivers.init();
    }
  }
}
