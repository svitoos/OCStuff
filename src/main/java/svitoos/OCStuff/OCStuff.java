package svitoos.OCStuff;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.StringFormatterMessageFactory;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import svitoos.OCStuff.driver.UpgradeEnderlink;
import svitoos.OCStuff.init.Items;

@Mod(
    modid = OCStuff.MOD_ID,
    name = OCStuff.MOD_NAME,
    version = OCStuff.VERSION /*@MCVERSIONDEP@*/,
    dependencies = "required-after:opencomputers@[1.7.5,)")
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

  @Mod.EventBusSubscriber
  public static final class RegistryHandler {
    @SubscribeEvent
    public static void onRegisterItems(RegistryEvent.Register<Item> event) {
      Items.register(event.getRegistry());
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onRegisterModels(ModelRegistryEvent event) {

      OBJLoader.INSTANCE.addDomain(MOD_ID);

      registerItemModel(Items.ultimateGeolyzerUpgrade);
      registerItemModel(Items.ultimateNavigationUpgrade);
      registerItemModel(Items.itemChargerUpgrade);
      registerItemModel(Items.enderlinkUpgrade);
      if (Items.cropnalyzerUpgrade != null) {
        registerItemModel(Items.cropnalyzerUpgrade);
      }
    }

    @SideOnly(Side.CLIENT)
    private static void registerItemModel(Item item) {
      ModelLoader.setCustomModelResourceLocation(
          item, 0, new ModelResourceLocation(item.getRegistryName().toString()));
    }
  }
}
