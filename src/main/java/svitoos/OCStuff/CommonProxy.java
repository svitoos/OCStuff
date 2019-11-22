package svitoos.OCStuff;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import svitoos.OCStuff.init.Drivers;
import svitoos.OCStuff.init.Events;
import svitoos.OCStuff.init.Items;
import svitoos.OCStuff.init.Recipes;

public class CommonProxy {

  public void preInit(FMLPreInitializationEvent e) {}

  public void init(FMLInitializationEvent e) {
    Items.init();
    Items.registerItems();
    Recipes.add();
    Events.init();
    Drivers.init();
  }

  public void postInit(FMLPostInitializationEvent e) {}
}
