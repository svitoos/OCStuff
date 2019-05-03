package svitoos.OCStuff;

import li.cil.oc.api.Network;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.EnvironmentHost;
import li.cil.oc.api.prefab.ManagedEnvironment;
import li.cil.oc.api.network.Visibility;
import li.cil.oc.api.internal.Rotatable;

public class UpgradeUltimateNavigationEnv extends ManagedEnvironment {
  private final EnvironmentHost host;

  public UpgradeUltimateNavigationEnv(EnvironmentHost host) {
    this.host = host;
    setNode(
        Network.newNode(this, Visibility.Network)
            .withComponent("ultimate_navigation")
            .withConnector()
            .create());
  }

  @Callback(doc = "function():number, number, number -- Get the current absolute position of the robot.")
  public Object[] getPosition(Context context, Arguments arguments) {
    return new Object[] {host.xPosition(), host.yPosition(), host.zPosition()};
  }

  @Callback(doc = "function():number -- Get the current orientation of the robot.")
  public Object[] getFacing(Context context, Arguments arguments) {
    return new Object[]{((Rotatable)host).facing().ordinal()};
  }
}
