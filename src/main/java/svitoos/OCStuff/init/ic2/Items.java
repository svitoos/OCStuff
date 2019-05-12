package svitoos.OCStuff.init.ic2;

import cpw.mods.fml.common.registry.GameRegistry;
import ic2.core.Ic2Items;
import ic2.core.util.StackUtil;
import net.minecraft.item.ItemStack;
import svitoos.OCStuff.Config;
import svitoos.OCStuff.item.UpgradeCropnalyzer;

public final class Items {
  public static void init() {
    svitoos.OCStuff.init.Items.cropnalyzerUpgrade = new UpgradeCropnalyzer();
    GameRegistry.registerItem(svitoos.OCStuff.init.Items.cropnalyzerUpgrade, "cropnalyzerUpgrade");
    if (Config.cropnalyzerUpgradeRecipe) {
      GameRegistry.addRecipe(
          new ItemStack(svitoos.OCStuff.init.Items.cropnalyzerUpgrade),
          "x x",
          "cic",
          "xpx",
          'x',
          net.minecraft.init.Items.gold_ingot,
          'c',
          li.cil.oc.api.Items.get("chip2").createItemStack(1),
          'i',
          StackUtil.copyWithWildCard(Ic2Items.cropnalyzer),
          'p',
          li.cil.oc.api.Items.get("printedCircuitBoard").createItemStack(1));
    }
  }
}
