package svitoos.OCStuff.driver.ic2.init;

import net.minecraftforge.common.MinecraftForge;
import svitoos.OCStuff.driver.ic2.EventHandlerIndustrialCraft2;

public final class Events {
  public static void init() {
    MinecraftForge.EVENT_BUS.register(new EventHandlerIndustrialCraft2());
  }
}
