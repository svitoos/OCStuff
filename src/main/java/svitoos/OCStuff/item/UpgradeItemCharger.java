package svitoos.OCStuff.item;

import li.cil.oc.api.CreativeTab;
import net.minecraft.item.Item;
import svitoos.OCStuff.OCStuff;

public class UpgradeItemCharger extends Item {

  public UpgradeItemCharger() {
    setRegistryName(OCStuff.MOD_ID, "upgrade_item_charger");
    setCreativeTab(CreativeTab.instance);
    setTranslationKey("upgrade_item_charger");
  }
}
