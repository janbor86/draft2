package com.lazyprogrammer.draft2.configuration;

import com.lazyprogrammer.draft2.game.map.GameMap;
import com.lazyprogrammer.draft2.game.map.terrain.TerrainRepository;
import com.lazyprogrammer.draft2.ui.swing.InfoLayerUI;
import com.lazyprogrammer.draft2.ui.swing.MainPanel;
import com.lazyprogrammer.draft2.ui.swing.graphics.Drawer;
import com.lazyprogrammer.draft2.ui.swing.graphics.Painter;
import com.lazyprogrammer.draft2.ui.swing.map.HexMapComponent;
import com.lazyprogrammer.draft2.ui.swing.map.MapView;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.util.List;
import javax.swing.JLayer;
import javax.swing.JPanel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwingConfiguration {
  @Bean
  JPanel mainPanel(HexMapComponent hexMapComponent, JLayer<HexMapComponent> infoLayer) {
    return new MainPanel(hexMapComponent, infoLayer);
  }

  @Bean
  HexMapComponent hexMapComponent(
      GameMap gameMap,
      MapView mapView,
      List<Painter> painters,
      List<MouseAdapter> mouseAdapters,
      List<KeyAdapter> keyAdapters) {

    final var hexMapComponent = new HexMapComponent(gameMap.getCoordinates(), mapView, painters);
    hexMapComponent.setFocusable(true);
    mouseAdapters.forEach(hexMapComponent::addMouseAdapter);
    keyAdapters.forEach(hexMapComponent::addKeyListener);
    return hexMapComponent;
  }

  @Bean
  JLayer<HexMapComponent> infoLayer(
      HexMapComponent hexMapComponent, Drawer drawer, TerrainRepository terrainRepository) {
    return new JLayer<>(
        hexMapComponent, new InfoLayerUI(hexMapComponent.getMapView(), drawer, terrainRepository));
  }
}
