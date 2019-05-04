package svitoos.OCStuff.item;

import li.cil.oc.api.CreativeTab;
import net.minecraft.item.Item;
import svitoos.OCStuff.OCStuff;

public class UpgradeUltimateGeolyzer extends Item {
  public UpgradeUltimateGeolyzer() {
    setCreativeTab(CreativeTab.instance);
    setUnlocalizedName("UpgradeUltimateGeolyzer");
    setTextureName(OCStuff.MOD_ID + ":" + "UpgradeUltimateGeolyzer");
  }
}
