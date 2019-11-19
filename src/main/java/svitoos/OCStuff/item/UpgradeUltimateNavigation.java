package svitoos.OCStuff.item;

import li.cil.oc.api.CreativeTab;

import net.minecraft.item.Item;
import svitoos.OCStuff.OCStuff;

public class UpgradeUltimateNavigation extends Item {

  public UpgradeUltimateNavigation() {
    setRegistryName(OCStuff.MOD_ID, "upgrade_ultimate_navigation");
    setCreativeTab(CreativeTab.instance);
    setTranslationKey("upgrade_ultimate_navigation");
  }
}
