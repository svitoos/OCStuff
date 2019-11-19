package svitoos.OCStuff.driver.ic2;

import ic2.api.crops.CropCard;
import ic2.api.crops.Crops;
import ic2.core.item.ItemCropSeed;
import java.util.HashMap;
import java.util.Map;
import li.cil.oc.api.driver.Converter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ConvertCropSeedItem implements Converter {

  @Override
  public void convert(final Object value, final Map<Object, Object> output) {
    if (value instanceof ItemStack) {
      final ItemStack stack = (ItemStack) value;
      final Item item = stack.getItem();
      if (item instanceof ItemCropSeed && stack.getTagCompound() != null) {
        final ItemCropSeed itemCropSeed = (ItemCropSeed) item;
        final Map<String, Object> crop = new HashMap<>();
        final CropCard cropCard = Crops.instance.getCropCard(stack);
        final int scanLevel = itemCropSeed.getScannedFromStack(stack);
        crop.put("scanLevel", scanLevel);
        if (scanLevel == 0) {
          crop.put("name", "unknown");
        } else if (scanLevel > 0 && cropCard != null) {
          crop.put("name", cropCard.getOwner() + ":" + cropCard.getId());
          if (scanLevel >= 2) {
            crop.put("tier", 0);
            crop.put("discoveredBy", cropCard.getDiscoveredBy());
          }
          if (scanLevel >= 3) {
            crop.put("attributes", cropCard.getAttributes());
          }
          if (scanLevel >= 4) {
            final Map<String, Object> stat = new HashMap<>();
            stat.put("growth", itemCropSeed.getGrowthFromStack(stack));
            stat.put("gain", itemCropSeed.getGainFromStack(stack));
            stat.put("resistance", itemCropSeed.getResistanceFromStack(stack));
            crop.put("stat", stat);
          }
        } else {
          crop.put("name", "invalid");
        }
        output.put("crop", crop);
      }
    }
  }
}
