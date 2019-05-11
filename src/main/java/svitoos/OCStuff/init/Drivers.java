package svitoos.OCStuff.init;

import svitoos.OCStuff.Mods;

public final class Drivers {
  public static void init() {
    if (Mods.IndustrialCraft2()) {
      svitoos.OCStuff.driver.ic2.init.Drivers.init();
    }
  }
}
