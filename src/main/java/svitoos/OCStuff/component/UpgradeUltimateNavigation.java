package svitoos.OCStuff.component;

import java.util.HashMap;
import java.util.Map;
import li.cil.oc.api.internal.Tablet;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.machine.Machine;
import li.cil.oc.api.network.EnvironmentHost;
import li.cil.oc.api.network.Message;
import li.cil.oc.server.component.UpgradeNavigation;
import li.cil.oc.util.BlockPosition;
import net.minecraft.nbt.NBTTagCompound;

public class UpgradeUltimateNavigation extends UpgradeNavigation {
  private final EnvironmentHost host;
  private final Map<String, String> deviceInfo;

  public UpgradeUltimateNavigation(EnvironmentHost host) {
    super(host);
    this.host = host;
    deviceInfo = new HashMap<>();
    deviceInfo.put(DeviceAttribute.Class, DeviceClass.Generic);
    deviceInfo.put(DeviceAttribute.Description, "Ultimate Navigation Upgrade");
    deviceInfo.put(DeviceAttribute.Vendor, "Scrag Technologies");
    deviceInfo.put(DeviceAttribute.Product, "PathFinder v4");
  }

  @Override
  public Map<String, String> getDeviceInfo() {
    return deviceInfo;
  }

  @Callback(
      doc = "function():number, number, number -- Get the current absolute position of the robot.")
  @Override
  public Object[] getPosition(Context context, Arguments arguments) {
    return new Object[] {host.xPosition(), host.yPosition(), host.zPosition()};
  }

  @Callback(doc = "function():number -- Get the operational range of the navigation upgrade.")
  @Override
  public Object[] getRange(Context context, Arguments arguments) {
    return new Object[] {Integer.MAX_VALUE};
  }

  @Override
  public void onMessage(Message message) {
    super.onMessage(message);
    if (message.name().equals("tablet.use")) {
      if (message.source().host() instanceof Machine) {
        Machine machine = (Machine) message.source().host();
        if (machine.host() instanceof Tablet) {
          if (message.data().length == 8
              && message.data()[3] instanceof BlockPosition
              && message.data()[0] instanceof NBTTagCompound) {
            NBTTagCompound nbt = (NBTTagCompound) message.data()[0];
            BlockPosition blockPos = (BlockPosition) message.data()[3];
            nbt.setInteger("posX", blockPos.x());
            nbt.setInteger("posY", blockPos.y());
            nbt.setInteger("posZ", blockPos.z());
          }
        }
      }
    }
  }
}
