package svitoos.OCStuff.item;

import svitoos.OCStuff.OCStuff;

public class Item extends net.minecraft.item.Item {
  private final String id;

  public Item(String id) {
    this.id = id;
    setUnlocalizedName(id);
    setTextureName(OCStuff.MOD_ID + ":" + id);
  }

  public String getItemId() {
    return id;
  }
}
