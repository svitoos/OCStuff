package svitoos.OCStuff.init;

import li.cil.oc.api.API;
import li.cil.oc.api.driver.EnvironmentProvider;
import li.cil.oc.api.driver.Item;
import svitoos.OCStuff.Mods;
import svitoos.OCStuff.driver.ic2.ConvertCropSeedItem;

public final class Drivers {
  public static void init() {
    Items.regItems.stream()
        .filter(item -> item.isEnabled() && item instanceof Item)
        .map(item -> (Item) item)
        .forEach(Drivers::regItemDriver);

    if (Mods.IndustrialCraft2()) {
      API.driver.add(new ConvertCropSeedItem());
    }
  }

  private static void regItemDriver(Item d) {
    API.driver.add(d);
    if (d instanceof EnvironmentProvider) {
      API.driver.add((EnvironmentProvider) d);
    }
  }
}
