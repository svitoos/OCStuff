package svitoos.OCStuff;

import li.cil.oc.api.CreativeTab;

import net.minecraft.item.Item;

public class UpgradeUltimateNavigationItem extends Item {

  public UpgradeUltimateNavigationItem() {
    setCreativeTab(CreativeTab.instance);
    setUnlocalizedName("UpgradeUltimateNavigation");
    setTextureName(OCStuff.MOD_ID + ":" + "UpgradeUltimateNavigation");
  }
}
