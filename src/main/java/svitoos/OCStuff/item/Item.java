package svitoos.OCStuff.item;

import li.cil.oc.api.CreativeTab;
import svitoos.OCStuff.OCStuff;

public class Item extends net.minecraft.item.Item {
  private final String id;

  public Item(String id) {
    this.id = id;
    setUnlocalizedName(id);
    setTextureName(OCStuff.MOD_ID + ":" + id);
    if (isEnabled()) {
      setCreativeTab(CreativeTab.instance);
    }
  }

  public boolean isEnabled() {
    return true;
  }

  public li.cil.oc.api.driver.Item getDriver() {
    return null;
  }

  public String getItemId() {
    return id;
  }
}
