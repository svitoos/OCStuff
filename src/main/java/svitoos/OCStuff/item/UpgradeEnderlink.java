package svitoos.OCStuff.item;

import li.cil.oc.api.CreativeTab;
import net.minecraft.item.Item;
import svitoos.OCStuff.OCStuff;

public class UpgradeEnderlink extends Item {
  public UpgradeEnderlink() {
    setCreativeTab(CreativeTab.instance);
    setUnlocalizedName("UpgradeEnderlink");
    setTextureName(OCStuff.MOD_ID + ":" + "UpgradeEnderlink");
  }
}
