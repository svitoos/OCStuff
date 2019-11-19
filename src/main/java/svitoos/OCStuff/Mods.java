package svitoos.OCStuff;

import net.minecraftforge.fml.common.Loader;

public final class Mods {
  public static boolean IndustrialCraft2() {
    return Config.integrationIndustrialCraft2 && Loader.isModLoaded("ic2");
  }
}
