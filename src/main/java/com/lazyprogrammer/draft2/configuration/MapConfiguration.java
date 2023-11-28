package com.lazyprogrammer.draft2.configuration;

import com.lazyprogrammer.draft2.data.GameMap;
import com.lazyprogrammer.draft2.data.map.MapConfig;
import com.lazyprogrammer.draft2.data.map.MapGenerator;
import com.lazyprogrammer.draft2.data.map.WfcMapGenerator;
import com.lazyprogrammer.draft2.data.terrain.TerrainRepository;
import com.lazyprogrammer.draft2.ui.swing.blueprint.BlueprintMap;
import com.lazyprogrammer.draft2.ui.swing.graphics.Drawer;
import com.lazyprogrammer.draft2.ui.swing.graphics.GridPainter;
import com.lazyprogrammer.draft2.ui.swing.graphics.Painter;
import com.lazyprogrammer.draft2.ui.swing.graphics.TilePainter;
import com.lazyprogrammer.draft2.ui.swing.map.MapView;
import java.util.HashMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class MapConfiguration {

  @Bean
  MapConfig mapConfig() {
    return new MapConfig(128, 80, 5);
  }

  @Bean
  MapGenerator mapGenerator() {
    return new WfcMapGenerator();
  }

  @Bean
  GameMap gameMap(MapConfig mapConfig, MapGenerator mapGenerator) {
    return mapGenerator.createGameMap(mapConfig);
  }

  @Bean
  MapView mapView(MapConfig mapConfig) {
    return new MapView(mapConfig);
  }

  @Bean
  @Order(3)
  Painter gridPainter(Drawer drawer) {
    return new GridPainter(drawer);
  }

  @Bean
  @Order(2)
  Painter tilePainter(Drawer drawer, TerrainRepository terrainRepository) {
    return new TilePainter(drawer, new BlueprintMap<>(new HashMap<>()), terrainRepository);
  }

  @Bean
  Drawer drawer() {
    return new Drawer();
  }
}
