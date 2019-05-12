package svitoos.OCStuff.init;

import net.minecraftforge.common.MinecraftForge;
import svitoos.OCStuff.Mods;
import svitoos.OCStuff.driver.ic2.EventHandlerIndustrialCraft2;

public class Events {
  public static void init() {
    if (Mods.IndustrialCraft2()) {
      MinecraftForge.EVENT_BUS.register(new EventHandlerIndustrialCraft2());
    }
  }
}
