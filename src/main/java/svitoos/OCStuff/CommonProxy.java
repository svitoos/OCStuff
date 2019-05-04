package svitoos.OCStuff;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import svitoos.OCStuff.init.Items;

public class CommonProxy {

  public void preInit(FMLPreInitializationEvent e) {}

  public void init(FMLInitializationEvent e) {
    Items.init();
  }

  public void postInit(FMLPostInitializationEvent e) {}
}
