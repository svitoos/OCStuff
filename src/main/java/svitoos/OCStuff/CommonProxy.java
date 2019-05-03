package svitoos.OCStuff;

import li.cil.oc.api.API;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {

  public void preInit(FMLPreInitializationEvent e) {}

  public void init(FMLInitializationEvent e) {
    OCStuff.itemUltimateNavigationUpgrade = new UpgradeUltimateNavigationItem();
    GameRegistry.registerItem(OCStuff.itemUltimateNavigationUpgrade, "ultimateNavigationUpgrade");
    API.driver.add(new UpgradeUltimateNavigationDriver());
  }

  public void postInit(FMLPostInitializationEvent e) {}
}
