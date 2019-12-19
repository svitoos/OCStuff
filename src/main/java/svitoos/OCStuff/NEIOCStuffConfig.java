package svitoos.OCStuff;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Optional;
import net.minecraft.item.ItemStack;
import svitoos.OCStuff.init.Items;

@SuppressWarnings("unused")
public class NEIOCStuffConfig implements IConfigureNEI {
  @Optional.Method(modid = "NotEnoughItems")
  @Override
  public String getName() {
    return OCStuff.class.getAnnotation(Mod.class).name();
  }

  @Optional.Method(modid = "NotEnoughItems")
  @Override
  public String getVersion() {
    return OCStuff.class.getAnnotation(Mod.class).version();
  }

  @Optional.Method(modid = "NotEnoughItems")
  @Override
  public void loadConfig() {
    Items.regItems.stream()
        .filter(item -> !item.isEnabled())
        .map(ItemStack::new)
        .forEach(API::hideItem);
  }
}
