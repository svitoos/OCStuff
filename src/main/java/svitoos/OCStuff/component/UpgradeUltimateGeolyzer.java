package svitoos.OCStuff.component;

import java.util.HashMap;
import java.util.Map;
import li.cil.oc.Settings;
import li.cil.oc.api.event.GeolyzerEvent.Analyze;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.EnvironmentHost;
import li.cil.oc.server.component.Geolyzer;
import li.cil.oc.server.component.UpgradeDatabase;
import li.cil.oc.util.DatabaseAccess;
import li.cil.oc.util.ExtendedArguments.ExtendedArguments;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import scala.Function1;
import scala.runtime.AbstractFunction1;
import svitoos.OCStuff.Config;

public class UpgradeUltimateGeolyzer extends Geolyzer {
  private final Map<String, String> deviceInfo;

  public UpgradeUltimateGeolyzer(EnvironmentHost host) {
    super(host);
    deviceInfo = new HashMap<>();
    deviceInfo.put(DeviceAttribute.Class, DeviceClass.Generic);
    deviceInfo.put(DeviceAttribute.Description, "Ultimate Geolyzer Upgrade");
    deviceInfo.put(DeviceAttribute.Vendor, "Scrag Technologies");
    deviceInfo.put(DeviceAttribute.Product, "Terrain Analyzer MkIII");
  }

  @Override
  public Map<String, String> getDeviceInfo() {
    return deviceInfo;
  }

  @Callback(
      doc = "function(x,y,z:number[,options:table]):table -- Get some information on a block.")
  public Object[] rangedAnalyze(Context context, Arguments arguments) {
    if (Settings.get().allowItemStackInspection()) {
      data d = new data(arguments);
      if (d.err != null) {
        return new Object[] {null, d.err};
      }
      final Map options = arguments.optTable(3, new HashMap());
      final Analyze event = new Analyze(host(), options, d.x, d.y, d.z);
      MinecraftForge.EVENT_BUS.post(event);
      if (event.isCanceled()) {
        return new Object[] {null, "scan was canceled"};
      } else {
        return new Object[] {event.data};
      }
    } else {
      return new Object[] {null, "not enabled in config"};
    }
  }

  @Callback(
      doc =
          "function(x,y,z:number, dbAddress:string, dbSlot:number):boolean -- Store an item stack representation of the block in a database component.")
  public Object[] rangedStore(Context context, Arguments arguments) {
    data d = new data(arguments);
    if (d.err != null) {
      return new Object[] {null, d.err};
    }
    final Block block = host().world().getBlock(d.x, d.y, d.z);
    final Item item = Item.getItemFromBlock(block);
    if (item == null) {
      return new Object[] {null, "block has no registered item representation"};
    }
    final int metadata = host().world().getBlockMetadata(d.x, d.y, d.z);
    final int damage = block.damageDropped(metadata);
    final ItemStack stack = new ItemStack(item, 1, damage);
    final Function1<UpgradeDatabase, Object[]> f =
        new AbstractFunction1<UpgradeDatabase, Object[]>() {
          @Override
          public Object[] apply(UpgradeDatabase database) {
            final int toSlot = new ExtendedArguments(arguments).checkSlot(database.data(), 4);
            final boolean nonEmpty = database.getStackInSlot(toSlot) != null;
            database.setStackInSlot(toSlot, stack);
            return new Object[] {nonEmpty};
          }
        };
    return DatabaseAccess.withDatabase(node(), arguments.checkString(3), f);
  }

  private class data {
    int x;
    int y;
    int z;
    String err;

    data(Arguments arguments) {
      final int dx = arguments.checkInteger(0);
      final int dy = arguments.checkInteger(1);
      final int dz = arguments.checkInteger(2);
      final int geolyzerRange = Settings.get().geolyzerRange();
      if (dx > geolyzerRange || dy > geolyzerRange || dz > geolyzerRange) {
        throw new IllegalArgumentException("location out of bounds");
      }
      final double range = Math.sqrt(dx * dx + dy * dy + dz * dz);
      final double energy = Settings.get().geolyzerScanCost() + range * Config.geolyzerCostPerRange;
      if (!node().tryChangeBuffer(-energy)) {
        err = "not enough energy";
        return;
      }
      x = (int) Math.floor(host().xPosition()) + dx;
      y = (int) Math.floor(host().yPosition()) + dy;
      z = (int) Math.floor(host().zPosition()) + dz;
    }
  }
}
