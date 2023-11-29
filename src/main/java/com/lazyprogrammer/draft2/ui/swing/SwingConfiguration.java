package com.lazyprogrammer.draft2.ui.swing;

import com.lazyprogrammer.draft2.game.map.GameMap;
import com.lazyprogrammer.draft2.game.map.terrain.TerrainRepository;
import com.lazyprogrammer.draft2.ui.swing.graphics.Drawer;
import com.lazyprogrammer.draft2.ui.swing.graphics.Painter;
import com.lazyprogrammer.draft2.ui.swing.map.MapComponent;
import com.lazyprogrammer.draft2.ui.swing.map.MapView;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.util.List;
import javax.swing.JLayer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwingConfiguration {

  @Bean
  MapComponent hexMapComponent(
      GameMap gameMap,
      MapView mapView,
      List<Painter> painters,
      List<MouseAdapter> mouseAdapters,
      List<KeyAdapter> keyAdapters) {

    final var hexMapComponent = new MapComponent(gameMap.getCoordinates(), mapView, painters);
    hexMapComponent.setFocusable(true);
    mouseAdapters.forEach(hexMapComponent::addMouseAdapter);
    keyAdapters.forEach(hexMapComponent::addKeyListener);
    return hexMapComponent;
  }

  @Bean
  JLayer<MapComponent> infoLayer(
      MapComponent mapComponent, Drawer drawer, TerrainRepository terrainRepository) {
    return new JLayer<>(
        mapComponent, new InfoLayerUI(mapComponent.getMapView(), drawer, terrainRepository));
  }

}
