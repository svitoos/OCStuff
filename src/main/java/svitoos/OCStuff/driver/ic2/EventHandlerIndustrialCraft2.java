package svitoos.OCStuff.driver.ic2;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import ic2.api.crops.CropCard;
import ic2.core.crop.TileEntityCrop;
import java.util.HashMap;
import java.util.Map;
import li.cil.oc.api.event.GeolyzerEvent;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ic2.api.crops.ICropTile;

public class EventHandlerIndustrialCraft2 {
  @SubscribeEvent
  public void onGeolyzerAnalyze(GeolyzerEvent.Analyze e) {
    final World world = e.host.world();
    final TileEntity tile = world.getTileEntity(e.x, e.y, e.z);
    if (tile instanceof ICropTile) {
      final ICropTile cropTile = (ICropTile) tile;
      final CropCard cropCard = cropTile.getCrop();
      if (cropCard != null) {
        final Map<String, Object> data = new HashMap<>();

        // basic information
        data.put("name", cropCard.owner() + ":" + cropCard.name());
        data.put("discoveredBy", cropCard.discoveredBy());
        data.put("size", cropTile.getSize());
        data.put("maxSize", cropCard.maxSize());
        data.put("nutrientStorage", cropTile.getNutrientStorage());
        data.put("waterStorage", cropTile.getHydrationStorage());
        data.put("weedExStorage", cropTile.getWeedExStorage());
        if (cropTile instanceof TileEntityCrop) {
          data.put("growthPoint", ((TileEntityCrop) cropTile).growthPoints);
        } else {
          data.put("growthPoint", 0);
        }
        data.put("growthDuration", cropCard.growthDuration(cropTile));

        // more information
        data.put("lightLevel", cropTile.getLightLevel());
        data.put("optimalHarvestSize", cropCard.getOptimalHavestSize(cropTile));
        data.put("isWeed", cropCard.isWeed(cropTile));
        data.put("canGrow", cropCard.canGrow(cropTile));
        data.put("canCross", cropCard.canCross(cropTile));
        data.put("canBeHarvested", cropCard.canBeHarvested(cropTile));
        data.put("humidity", cropTile.getHumidity());
        data.put("nutrients", cropTile.getNutrients());
        data.put("airQuality", cropTile.getAirQuality());

        e.data.put("crop", data);
        e.data.put("growth", cropTile.getSize() / (double) cropCard.maxSize());
      }
    }
  }
}
