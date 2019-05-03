package svitoos.OCStuff;

import li.cil.oc.api.API;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class CommonProxy {

  public void preInit(FMLPreInitializationEvent e) {}

  public void init(FMLInitializationEvent e) {
    OCStuff.itemUltimateNavigationUpgrade = new UpgradeUltimateNavigationItem();
    GameRegistry.registerItem(OCStuff.itemUltimateNavigationUpgrade, "ultimateNavigationUpgrade");
    API.driver.add(new UpgradeUltimateNavigationDriver());
    if (Config.regRecipeUpgradeUltimateNavigation) {
      GameRegistry.addRecipe(
          new ItemStack(OCStuff.itemUltimateNavigationUpgrade),
          "xgx",
          "cic",
          "xpx",
          'g',
          Items.map,
          'x',
          Items.gold_ingot,
          'c',
          li.cil.oc.api.Items.get("chip2").createItemStack(1),
          'i',
          Items.ender_eye,
          'p',
          li.cil.oc.api.Items.get("printedCircuitBoard").createItemStack(1));
    }
  }

  public void postInit(FMLPostInitializationEvent e) {}
}
