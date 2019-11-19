package svitoos.OCStuff;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import svitoos.OCStuff.init.Drivers;
import svitoos.OCStuff.init.Events;
import svitoos.OCStuff.init.Items;

public class CommonProxy {

  public void preInit(FMLPreInitializationEvent e) {
    Items.init();
  }

  public void init(FMLInitializationEvent e) {
    Events.init();
    Drivers.init();
  }

  public void postInit(FMLPostInitializationEvent e) {}
}
