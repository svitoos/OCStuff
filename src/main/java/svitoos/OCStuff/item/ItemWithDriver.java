package svitoos.OCStuff.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import li.cil.oc.api.driver.EnvironmentProvider;
import li.cil.oc.api.driver.item.HostAware;
import li.cil.oc.api.network.EnvironmentHost;
import li.cil.oc.api.network.ManagedEnvironment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import svitoos.OCStuff.util.OCUtils;

public class ItemWithDriver extends Item
    implements li.cil.oc.api.driver.Item, HostAware, EnvironmentProvider {

  public ItemWithDriver(String id) {
    super(id);
  }

  @Override
  public Class<?> getEnvironment(ItemStack stack) {
    return null;
  }

  @Override
  public boolean worksWith(ItemStack stack, Class<? extends EnvironmentHost> host) {
    return worksWith(stack);
  }

  @Override
  public boolean worksWith(ItemStack stack) {
    return stack != null && stack.getItem().equals(this);
  }

  @Override
  public ManagedEnvironment createEnvironment(ItemStack stack, EnvironmentHost host) {
    return null;
  }

  @Override
  public String slot(ItemStack stack) {
    return null;
  }

  @Override
  public int tier(ItemStack stack) {
    return 0;
  }

  @Override
  public NBTTagCompound dataTag(ItemStack stack) {
    return null;
  }

  public boolean isAdapter(Class<? extends EnvironmentHost> host) {
    return li.cil.oc.api.internal.Adapter.class.isAssignableFrom(host);
  }

  public boolean isComputer(Class<? extends EnvironmentHost> host) {
    return li.cil.oc.api.internal.Case.class.isAssignableFrom(host);
  }

  public boolean isRobot(Class<? extends EnvironmentHost> host) {
    return li.cil.oc.api.internal.Robot.class.isAssignableFrom(host);
  }

  public boolean isRotatable(Class<? extends EnvironmentHost> host) {
    return li.cil.oc.api.internal.Rotatable.class.isAssignableFrom(host);
  }

  public boolean isServer(Class<? extends EnvironmentHost> host) {
    return li.cil.oc.api.internal.Server.class.isAssignableFrom(host);
  }

  public boolean isTablet(Class<? extends EnvironmentHost> host) {
    return li.cil.oc.api.internal.Tablet.class.isAssignableFrom(host);
  }

  public boolean isDrone(Class<? extends EnvironmentHost> host) {
    return li.cil.oc.api.internal.Drone.class.isAssignableFrom(host);
  }

  @Override
  public EnumRarity getRarity(ItemStack stack) {
    return OCUtils.getRarityByTier(stack);
  }

  // Mostly stolen from Sangar
  @Override
  @SideOnly(Side.CLIENT)
  @SuppressWarnings("unchecked")
  public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced) {
    OCUtils.addTooltip(stack, tooltip, advanced);
  }
}
