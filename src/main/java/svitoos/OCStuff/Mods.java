package svitoos.OCStuff;

import cpw.mods.fml.common.Loader;

public final class Mods {
  public static boolean IndustrialCraft2() {
    return Config.integrationIndustrialCraft2 && Loader.isModLoaded("IC2");
  }
}
