package svitoos.OCStuff.item;

import li.cil.oc.api.CreativeTab;
import net.minecraft.item.Item;
import svitoos.OCStuff.OCStuff;

public class UpgradeCropnalyzer extends Item {

  public UpgradeCropnalyzer() {
    setRegistryName(OCStuff.MOD_ID, "upgrade_cropnalyzer");
    setCreativeTab(CreativeTab.instance);
    setTranslationKey("upgrade_cropnalyzer");
  }
}
