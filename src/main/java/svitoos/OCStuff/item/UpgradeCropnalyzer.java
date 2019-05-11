package svitoos.OCStuff.item;

import li.cil.oc.api.CreativeTab;
import net.minecraft.item.Item;
import svitoos.OCStuff.OCStuff;

public class UpgradeCropnalyzer extends Item {

  public UpgradeCropnalyzer() {
    setCreativeTab(CreativeTab.instance);
    setUnlocalizedName("UpgradeCropnalyzer");
    setTextureName(OCStuff.MOD_ID + ":" + "UpgradeCropnalyzer");
  }
}
