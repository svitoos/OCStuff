package svitoos.OCStuff.driver;

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
import li.cil.oc.api.network.Connector;
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
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import scala.Option;
import svitoos.OCStuff.Config;

public class UpgradeEnderlink extends ManagedEnvironment implements DeviceInfo {

  private static final Map<String, String> deviceInfo;

  static {
    deviceInfo = new HashMap<>();
    deviceInfo.put(DeviceAttribute.Class, DeviceClass.Generic);
    deviceInfo.put(DeviceAttribute.Description, "Enderlink");
    deviceInfo.put(DeviceAttribute.Vendor, "Scrag Technologies");
    deviceInfo.put(DeviceAttribute.Product, "Enderlink v1");
  }

  private static Multimap<String, UpgradeEnderlink> enderlinks;

  public static void init() {
    enderlinks = HashMultimap.create();
  }

  public static void cleanup() {
    enderlinks.clear();
    enderlinks = null;
  }

  private final EnvironmentHost host;
  private String name;
  private boolean isPublic;
  private String remoteName;
  private String remoteOwner;

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

  @Callback(doc = "remote([name:string[, owner:string]]):string,string -- Returns the currently remote enderlink; sets the remote enderlink if specified.")
  public Object[] remote(Context context, Arguments arguments) {
    if (arguments.count() > 0) {
      final String name = arguments.checkAny(0) == null ? null : arguments.checkString(0);
      final String owner = name == null ? null : arguments.optString(1, getOwnerName());
      remoteName = name;
      remoteOwner = owner;
    }
    return new Object[] {remoteName, remoteOwner};
  }

  @Callback(doc = "remoteAvailable():boolean")
  public Object[] remoteAvailable(Context context, Arguments arguments) {
    return new Object[] {getRemote() != null};
  }

  @Callback(
      doc =
          "transferItem([count:number=64]):number[,string] -- Transfers some items from selected slot into the remote enderlink.")
  public Object[] transferItem(Context context, Arguments arguments) {
    final int count = new ExtendedArguments(arguments).optItemCount(0, 64);
    UpgradeEnderlink remote = getRemote();
    if (remote == null) {
      return new Object[] {0, "unavailable"};
    }
    if (!((Connector) node()).tryChangeBuffer(-Config.enderlinkTransferCost)) {
      return new Object[] {0, "not enough energy"};
    }
    Agent agent = (Agent) host;
    IInventory inventory = agent.mainInventory();
    final ItemStack stack = inventory.getStackInSlot(agent.selectedSlot());
    if (remote != this && stack != null && stack.stackSize > 0 && count > 0) {
      final int stackSize = stack.stackSize;
      if (remote.insertItem(stack, Math.min(stackSize, count))) {
        if (stack.stackSize == 0) {
          inventory.setInventorySlotContents(agent.selectedSlot(), null);
        } else {
          inventory.markDirty();
        }
        return new Object[] {stackSize - stack.stackSize};
      }
    }
    return new Object[] {0};
  }

  @Callback(
      doc =
          "transferFluid([amount:number=1000]):number[,string] -- Transfers some fluids from selected tank into the remote enderlink.")
  public Object[] transferFluid(Context context, Arguments arguments) {
    final int amount =
        new ExtendedArguments(arguments).optFluidCount(0, FluidContainerRegistry.BUCKET_VOLUME);
    UpgradeEnderlink remote = getRemote();
    if (remote == null) {
      return new Object[] {0, "unavailable"};
    }
    if (!((Connector) node()).tryChangeBuffer(-Config.enderlinkTransferCost)) {
      return new Object[] {0, "not enough energy"};
    }
    Agent agent = (Agent) host;
    IFluidTank tank = agent.tank().getFluidTank(agent.selectedTank());
    if (remote != this && tank != null) {
      return new Object[] {remote.insertFluid(tank, Math.min(amount, tank.getFluidAmount()))};
    }
    return new Object[] {0};
  }

  @Callback(doc = "getName():string")
  public Object[] getName(Context context, Arguments arguments) {
    return new Object[] {name};
  }

  @Callback(doc = "isPublic():boolean")
  public Object[] isPublic(Context context, Arguments arguments) {
    return new Object[] {isPublic};
  }

  @Callback(doc = "isOpen():boolean")
  public Object[] isOpen(Context context, Arguments arguments) {
    return new Object[] {isOpen()};
  }

  @Callback(doc = "getOwner():string")
  public Object[] getOwner(Context context, Arguments arguments) {
    return new Object[] {getOwnerName()};
  }

  @Callback(doc = "open(name:string[, isPublic:boolean=false]):boolean")
  public Object[] open(Context context, Arguments arguments) {
    return new Object[] {open(arguments.checkString(0), arguments.optBoolean(1, false))};
  }

  @Callback(doc = "close():boolean")
  public Object[] close(Context context, Arguments arguments) {
    return new Object[] {close()};
  }

  @Override
  public void load(NBTTagCompound nbt) {
    super.load(nbt);
    if (nbt.hasKey("name")) {
      name = nbt.getString("name");
    }
    if (nbt.hasKey("remoteName") && nbt.hasKey("remoteOwner")) {
      remoteName = nbt.getString("remoteName");
      remoteOwner = nbt.getString("remoteOwner");
    }
  }

  @Override
  public void onConnect(Node node) {
    if (node == this.node() && isOpen()) {
      reg();
    }
  }

  @Override
  public void onDisconnect(Node node) {
    if (node == this.node() && isOpen()) {
      unreg();
    }
  }

  @Override
  public void save(NBTTagCompound nbt) {
    super.save(nbt);
    if (name != null) {
      nbt.setString("name", name);
    } else {
      nbt.removeTag("name");
    }
    if (remoteName != null) {
      nbt.setString("remoteName", remoteName);
      nbt.setString("remoteOwner", remoteOwner);
    } else {
      nbt.removeTag("remoteName");
      nbt.removeTag("remoteOwner");
    }
  }

  @Override
  public void onMessage(Message message) {
    super.onMessage(message);
    if (message.name().equals("computer.stopped")) {
      close();
      remoteName = null;
      remoteOwner = null;
    }
  }

  private boolean open(String name, boolean isPublic) {
    if (isOpen()) {
      return false;
    }
    this.name = name;
    this.isPublic = isPublic;
    reg();
    return true;
  }

  private boolean close() {
    if (isOpen()) {
      unreg();
      name = null;
      isPublic = false;
      return true;
    }
    return false;
  }

  private boolean insertItem(ItemStack stack, int count) {
    Agent agent = (Agent) host;
    IInventory inventory = agent.mainInventory();
    return InventoryUtils.insertIntoInventorySlot(
        stack, inventory, Option.apply(ForgeDirection.UP), agent.selectedSlot(), count, false);
  }

  private int insertFluid(IFluidTank source, int amount) {
    Agent agent = (Agent) host;
    IFluidTank sink = agent.tank().getFluidTank(agent.selectedTank());
    FluidStack drained = source.drain(amount, false);
    int filled = sink.fill(drained, false);
    return sink.fill(source.drain(filled, true), true);
  }

  private void reg() {
    enderlinks.put(getKey(), this);
  }

  private void unreg() {
    enderlinks.remove(getKey(), this);
  }

  private boolean isOpen() {
    return name != null;
  }

  private String getOwnerName() {
    return ((Agent) host).ownerName();
  }

  private String getKey() {
    return getOwnerName() + name;
  }

  private UpgradeEnderlink getRemote() {
    if (remoteName == null) {
      return null;
    }
    UpgradeEnderlink remote =
        enderlinks.get(remoteOwner + remoteName).stream().findAny().orElse(null);
    if (remote != null && (remote.isPublic || remote.getOwnerName().equals(getOwnerName()))) {
      return remote;
    }
    return null;
  }
}
