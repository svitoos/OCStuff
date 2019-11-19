package svitoos.OCStuff.item;

import li.cil.oc.api.CreativeTab;
import net.minecraft.item.Item;
import svitoos.OCStuff.OCStuff;

public class UpgradeUltimateGeolyzer extends Item {
  public UpgradeUltimateGeolyzer() {
    setRegistryName(OCStuff.MOD_ID, "upgrade_ultimate_geolyzer");
    setCreativeTab(CreativeTab.instance);
    setTranslationKey("upgrade_ultimate_geolyzer");
  }
}
