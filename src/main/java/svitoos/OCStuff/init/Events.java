package svitoos.OCStuff.init;

import svitoos.OCStuff.Config;
import svitoos.OCStuff.Mods;

public class Events {
  public static void init() {
    if (Mods.IndustrialCraft2()) {
      svitoos.OCStuff.driver.ic2.init.Events.init();
    }
  }
}
