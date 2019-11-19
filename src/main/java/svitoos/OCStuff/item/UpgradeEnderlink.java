package svitoos.OCStuff.item;

import li.cil.oc.api.CreativeTab;
import net.minecraft.item.Item;
import svitoos.OCStuff.OCStuff;

public class UpgradeEnderlink extends Item {
  public UpgradeEnderlink() {
    setRegistryName(OCStuff.MOD_ID, "upgrade_enderlink");
    setCreativeTab(CreativeTab.instance);
    setTranslationKey("upgrade_enderlink");
  }
}
