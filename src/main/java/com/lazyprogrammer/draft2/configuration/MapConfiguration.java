package com.lazyprogrammer.draft2.configuration;

import com.lazyprogrammer.draft2.swing.Hex;
import com.lazyprogrammer.draft2.swing.blueprint.BlueprintMap;
import com.lazyprogrammer.draft2.swing.data.GameMap;
import com.lazyprogrammer.draft2.swing.graphics.Drawer;
import com.lazyprogrammer.draft2.swing.graphics.ElevationPainter;
import com.lazyprogrammer.draft2.swing.graphics.GridPainter;
import com.lazyprogrammer.draft2.swing.graphics.Painter;
import com.lazyprogrammer.draft2.swing.graphics.TilePainter;
import com.lazyprogrammer.draft2.swing.map.MapConfig;
import com.lazyprogrammer.draft2.swing.map.MapGenerator;
import com.lazyprogrammer.draft2.swing.map.MapView;
import com.lazyprogrammer.draft2.swing.map.WfcMapGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.HashMap;

@Configuration
public class MapConfiguration {

  @Bean
  MapConfig mapConfig() {
    return new MapConfig(60, 30, Hex.sizeOf(28));
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
  Painter tilePainter(Drawer drawer) {
    return new TilePainter(drawer, new BlueprintMap<>(new HashMap<>()));
  }

  @Bean
  @Order(1)
  Painter elevationPainter(Drawer drawer) {
    return new ElevationPainter(drawer, new BlueprintMap<>(new HashMap<>()));
  }

  @Bean
  Drawer drawer() {
    return new Drawer();
  }
}
