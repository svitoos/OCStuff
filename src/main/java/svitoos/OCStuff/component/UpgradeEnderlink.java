package svitoos.OCStuff.component;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import java.util.HashMap;
import java.util.Map;
import li.cil.oc.api.Network;
import li.cil.oc.api.driver.DeviceInfo;
import li.cil.oc.api.internal.Agent;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.EnvironmentHost;
import li.cil.oc.api.network.Message;
import li.cil.oc.api.network.Node;
import li.cil.oc.api.network.Visibility;
import li.cil.oc.api.prefab.ManagedEnvironment;
import li.cil.oc.util.ExtendedArguments.ExtendedArguments;
import li.cil.oc.util.InventoryUtils;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import scala.Option;

public class UpgradeEnderlink extends ManagedEnvironment implements DeviceInfo {

  private static final Map<String, String> deviceInfo;

  static {
    deviceInfo = new HashMap<>();
    deviceInfo.put(DeviceAttribute.Class, DeviceClass.Generic);
    deviceInfo.put(DeviceAttribute.Description, "Enderlink");
    deviceInfo.put(DeviceAttribute.Vendor, "Scrag Technologies");
    deviceInfo.put(DeviceAttribute.Product, "Enderlink v1");
  }

  private static Multimap<String, UpgradeEnderlink> channels;

  public static void init() {
    channels = HashMultimap.create();
  }

  public static void cleanup() {
    channels.clear();
    channels = null;
  }

  private final EnvironmentHost host;
  private String sourceChannel;
  private boolean isPublic;
  private String sinkChannel;
  private String sinkOwner;

  public UpgradeEnderlink(EnvironmentHost host) {
    this.host = host;
    setNode(
        Network.newNode(this, Visibility.Network)
            .withComponent("enderlink", Visibility.Neighbors)
            .withConnector()
            .create());
  }

  @Override
  public Map<String, String> getDeviceInfo() {
    return deviceInfo;
  }

  @Callback
  public Object[] sink(Context context, Arguments arguments) {
    if (arguments.count() > 0) {
      final String channel = arguments.checkAny(0) == null ? null : arguments.checkString(0);
      final String owner = sinkChannel == null ? null : arguments.optString(1, getOwnerName());
      sinkChannel = channel;
      sinkOwner = owner;
    }
    return new Object[] {sinkChannel, sinkOwner};
  }

  @Callback
  public Object[] source(Context context, Arguments arguments) {
    if (arguments.count() > 0) {
      final String channel = arguments.checkAny(0) == null ? null : arguments.checkString(0);
      isPublic = arguments.optBoolean(1, false);
      if (sourceChannel != null) {
        unlink();
      }
      sourceChannel = channel;
      if (sourceChannel != null) {
        link();
      }
    }
    return new Object[] {sourceChannel, getOwnerName(), isPublic};
  }

  @Callback
  public Object[] sinkAvailable(Context context, Arguments arguments) {
    return new Object[] {getSink() != null};
  }

  @Callback
  public Object[] transferItem(Context context, Arguments arguments) {
    final int count = new ExtendedArguments(arguments).optItemCount(0, 64);
    UpgradeEnderlink sink = getSink();
    if (sink == null) {
      return new Object[] {0, "unavailable"};
    }
    Agent agent = (Agent) host;
    IInventory inventory = agent.mainInventory();
    final ItemStack stack = inventory.getStackInSlot(agent.selectedSlot());
    if (stack != null && stack.stackSize > 0) {
      final int stackSize = stack.stackSize;
      if (!sink.insertItem(stack, Math.min(stackSize, count))) {
        return new Object[] {0, "blocked"};
      } else if (stack.stackSize == 0) {
        inventory.setInventorySlotContents(agent.selectedSlot(), null);
      } else {
        inventory.markDirty();
      }
      return new Object[] {stackSize - stack.stackSize};
    }
    return new Object[] {0};
  }

  @Override
  public void load(NBTTagCompound nbt) {
    super.load(nbt);
    if (nbt.hasKey("sourceChannel")) {
      sourceChannel = nbt.getString("sourceChannel");
    }
    if (nbt.hasKey("sinkChannel") && nbt.hasKey("sinkOwner")) {
      sinkChannel = nbt.getString("sinkChannel");
      sinkOwner = nbt.getString("sinkOwner");
    }
  }

  @Override
  public void onConnect(Node node) {
    if (node == this.node() && isOpen()) {
      link();
    }
  }

  @Override
  public void onDisconnect(Node node) {
    if (node == this.node() && isOpen()) {
      unlink();
    }
  }

  @Override
  public void save(NBTTagCompound nbt) {
    super.save(nbt);
    if (sourceChannel != null) {
      nbt.setString("sourceChannel", sourceChannel);
    } else {
      nbt.removeTag("sourceChannel");
    }
    if (sinkChannel != null) {
      nbt.setString("sinkChannel", sinkChannel);
      nbt.setString("sinkOwner", sinkOwner);
    } else {
      nbt.removeTag("sinkChannel");
      nbt.removeTag("sinkOwner");
    }
  }

  @Override
  public void onMessage(Message message) {
    super.onMessage(message);
    if (message.name().equals("computer.stopped")) {
      if (isOpen()) {
        unlink();
        sourceChannel = null;
      }
      sinkChannel = null;
      sinkOwner = null;
    }
  }

  private boolean insertItem(ItemStack stack, int count) {
    Agent agent = (Agent) host;
    IInventory inventory = agent.mainInventory();
    return InventoryUtils.insertIntoInventorySlot(
        stack, inventory, Option.apply(ForgeDirection.UP), agent.selectedSlot(), count, false);
  }

  private void link() {
    channels.put(getSourceKey(), this);
  }

  private void unlink() {
    channels.remove(getSourceKey(), this);
  }

  private boolean isOpen() {
    return sourceChannel != null;
  }

  private String getOwnerName() {
    return ((Agent) host).ownerName();
  }

  private String getSourceKey() {
    return getOwnerName() + sourceChannel;
  }

  private UpgradeEnderlink getSink() {
    if (sinkChannel == null) {
      return null;
    }
    UpgradeEnderlink sink = channels.get(sinkOwner + sinkChannel).stream().findAny().orElse(null);
    if (sink != null && (sink.isPublic || sink.getOwnerName().equals(getOwnerName()))) {
      return sink;
    }
    return null;
  }
}
