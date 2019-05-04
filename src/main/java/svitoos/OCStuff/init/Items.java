package svitoos.OCStuff.init;

import cpw.mods.fml.common.registry.GameRegistry;
import li.cil.oc.api.API;
import net.minecraft.item.ItemStack;
import svitoos.OCStuff.Config;
import svitoos.OCStuff.driver.DriverUpgradeUltimateNavigation;
import svitoos.OCStuff.item.UpgradeUltimateNavigation;

public final class Items {

  public static UpgradeUltimateNavigation ultimateNavigationUpgrade;

  private Items() {}

  public static void init() {
    ultimateNavigationUpgrade = new UpgradeUltimateNavigation();
    GameRegistry.registerItem(ultimateNavigationUpgrade, "ultimateNavigationUpgrade");
    API.driver.add(new DriverUpgradeUltimateNavigation());
    if (Config.regRecipeUpgradeUltimateNavigation) {
      GameRegistry.addRecipe(
          new ItemStack(ultimateNavigationUpgrade),
          "xgx",
          "cic",
          "xpx",
          'g',
          net.minecraft.init.Items.map,
          'x',
          net.minecraft.init.Items.gold_ingot,
          'c',
          li.cil.oc.api.Items.get("chip2").createItemStack(1),
          'i',
          net.minecraft.init.Items.ender_eye,
          'p',
          li.cil.oc.api.Items.get("printedCircuitBoard").createItemStack(1));
    }
  }
}
