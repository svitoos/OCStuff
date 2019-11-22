package svitoos.OCStuff;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.StringFormatterMessageFactory;

import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerAboutToStartEvent;
import cpw.mods.fml.common.event.FMLServerStoppedEvent;
import svitoos.OCStuff.driver.UpgradeEnderlink;

@Mod(
    modid = OCStuff.MOD_ID,
    name = OCStuff.MOD_NAME,
    version = OCStuff.VERSION /*@MCVERSIONDEP@*/,
    dependencies = "required-after:OpenComputers@[1.6.2,)")
public class OCStuff {

  public static final String MOD_ID = "@MODID@";
  public static final String MOD_NAME = "@MODNAME@";
  public static final String VERSION = "@MODVERSION@";

  @Mod.Instance public static OCStuff instance;

  static Logger logger;

  @SidedProxy(
      clientSide = "svitoos.OCStuff.ClientProxy",
      serverSide = "svitoos.OCStuff.CommonProxy")
  public static CommonProxy proxy;

  @Mod.EventHandler
  public void preInit(FMLPreInitializationEvent e) {
    logger = LogManager.getLogger(MOD_ID, new StringFormatterMessageFactory());
    Config.init(e.getSuggestedConfigurationFile());
    proxy.preInit(e);
  }

  @Mod.EventHandler
  public void init(FMLInitializationEvent e) {
    proxy.init(e);
  }

  @Mod.EventHandler
  public void postInit(FMLPostInitializationEvent e) {
    proxy.postInit(e);
  }

  @Mod.EventHandler
  public void serverLoad(FMLServerStartingEvent event) {}

  @Mod.EventHandler
  public void serverStart(FMLServerAboutToStartEvent event) {
    UpgradeEnderlink.init();
  }

  @Mod.EventHandler
  public void serverStop(FMLServerStoppedEvent event) {
    UpgradeEnderlink.cleanup();
  }

  public static void info(String format, Object... data) {
    logger.log(Level.INFO, format, data);
  }

  public static void warn(String format, Object... data) {
    logger.log(Level.WARN, format, data);
  }
}
