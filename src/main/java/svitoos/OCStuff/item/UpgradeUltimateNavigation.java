package svitoos.OCStuff.item;

import li.cil.oc.api.CreativeTab;

import net.minecraft.item.Item;
import svitoos.OCStuff.OCStuff;

public class UpgradeUltimateNavigation extends Item {

  public UpgradeUltimateNavigation() {
    setCreativeTab(CreativeTab.instance);
    setUnlocalizedName("UpgradeUltimateNavigation");
    setTextureName(OCStuff.MOD_ID + ":" + "UpgradeUltimateNavigation");
  }
}
