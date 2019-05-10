package svitoos.OCStuff.item;

import li.cil.oc.api.CreativeTab;
import net.minecraft.item.Item;
import svitoos.OCStuff.OCStuff;

public class UpgradeItemCharger extends Item {

  public UpgradeItemCharger() {
    setCreativeTab(CreativeTab.instance);
    setUnlocalizedName("UpgradeItemCharger");
    setTextureName(OCStuff.MOD_ID + ":" + "UpgradeItemCharger");
  }
}
