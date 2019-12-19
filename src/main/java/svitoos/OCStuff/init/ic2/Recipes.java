package svitoos.OCStuff.init.ic2;

import ic2.api.item.IC2Items;
import ic2.core.util.StackUtil;
import net.minecraft.item.ItemStack;
import svitoos.OCStuff.Config;
import svitoos.OCStuff.util.RecipeUtils;

public final class Recipes {
  public static void add() {
    if (Config.cropnalyzerUpgradeEnabled && Config.cropnalyzerUpgradeRecipe) {
      RecipeUtils.addShapedRecipe(
          new ItemStack(svitoos.OCStuff.init.Items.cropnalyzerUpgrade),
          "x x",
          "cic",
          "xpx",
          'x',
          "ingotGold",
          'c',
          "oc:circuitChip2",
          'i',
          StackUtil.copyWithWildCard(IC2Items.getItem("cropnalyzer")),
          'p',
          "oc:materialCircuitBoardPrinted");
    }
  }
}
