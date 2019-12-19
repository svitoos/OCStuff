package svitoos.OCStuff.driver;

import java.util.Map;
import li.cil.oc.api.driver.DeviceInfo;
import li.cil.oc.api.prefab.ManagedEnvironment;
import svitoos.OCStuff.util.OCUtils;

/**
 * stolen from Vexatos
 * https://github.com/Vexatos/Computronics/blob/master/src/main/java/pl/asie/computronics/oc/driver/ManagedEnvironmentWithComponentConnector.java
 */
public abstract class ManagedEnvironmentWithDeviceInfo extends ManagedEnvironment
    implements DeviceInfo {
  private Map<String, String> deviceInfo;

  @Override
  public Map<String, String> getDeviceInfo() {
    if (deviceInfo == null) {
      OCUtils.Device device = deviceInfo();
      if (device != null) {
        return deviceInfo = device.deviceInfo();
      }
    }
    return deviceInfo;
  }

  protected abstract OCUtils.Device deviceInfo();
}
