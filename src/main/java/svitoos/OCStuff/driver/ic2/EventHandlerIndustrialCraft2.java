package svitoos.OCStuff.driver.ic2;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
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
    final TileEntity tile = world.getTileEntity(e.pos);
    if (tile instanceof ICropTile) {
      final ICropTile cropTile = (ICropTile) tile;
      final CropCard cropCard = cropTile.getCrop();
      if (cropCard != null) {
        final Map<String, Object> data = new HashMap<>();

        // basic information
        data.put("name", cropCard.getOwner() + ":" + cropCard.getId());
        data.put("discoveredBy", cropCard.getDiscoveredBy());
        data.put("size", cropTile.getCurrentSize());
        data.put("maxSize", cropCard.getMaxSize());
        data.put("nutrientStorage", cropTile.getStorageNutrients());
        data.put("waterStorage", cropTile.getStorageWater());
        data.put("weedExStorage", cropTile.getStorageWeedEX());
        data.put("growthPoints", cropTile.getGrowthPoints());
        data.put("growthDuration", cropCard.getGrowthDuration(cropTile));

        // more information
        data.put("lightLevel", cropTile.getLightLevel());
        data.put("optimalHarvestSize", cropCard.getOptimalHarvestSize(cropTile));
        data.put("isWeed", cropCard.isWeed(cropTile));
        data.put("canGrow", cropCard.canGrow(cropTile));
        data.put("canCross", cropCard.canCross(cropTile));
        data.put("canBeHarvested", cropCard.canBeHarvested(cropTile));
        data.put("humidity", cropTile.getTerrainHumidity());
        data.put("nutrients", cropTile.getTerrainNutrients());
        data.put("airQuality", cropTile.getTerrainAirQuality());

        e.data.put("crop", data);
        e.data.put("growth", cropTile.getCurrentSize() / (double) cropCard.getMaxSize());
      }
    }
  }
}
