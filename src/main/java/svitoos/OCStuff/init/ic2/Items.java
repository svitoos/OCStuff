package svitoos.OCStuff.init.ic2;

import ic2.api.item.IC2Items;
import ic2.core.util.StackUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.registries.IForgeRegistry;
import svitoos.OCStuff.Config;
import svitoos.OCStuff.item.UpgradeCropnalyzer;
import svitoos.OCStuff.util.RecipeUtils;

public final class Items {
  public static void register(IForgeRegistry<Item> registry) {
    svitoos.OCStuff.init.Items.cropnalyzerUpgrade = new UpgradeCropnalyzer();
    registry.register(svitoos.OCStuff.init.Items.cropnalyzerUpgrade);
    if (Config.cropnalyzerUpgradeRecipe) {
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
