package com.lazyprogrammer.draft2.configuration;

import com.lazyprogrammer.draft2.swing.Hex;
import com.lazyprogrammer.draft2.swing.HexMapComponent;
import com.lazyprogrammer.draft2.swing.InfoLayerUI;
import com.lazyprogrammer.draft2.swing.MainPanel;
import com.lazyprogrammer.draft2.swing.blueprint.Blueprints;
import com.lazyprogrammer.draft2.swing.data.GameMap;
import com.lazyprogrammer.draft2.swing.data.terrain.TerrainRepository;
import com.lazyprogrammer.draft2.swing.graphics.Drawer;
import com.lazyprogrammer.draft2.swing.graphics.Painter;
import com.lazyprogrammer.draft2.swing.map.MapView;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.swing.JLayer;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.util.List;

@Configuration
public class SwingConfiguration {
  @Bean
  JPanel mainPanel(HexMapComponent hexMapComponent, JLayer<HexMapComponent> infoLayer) {
    return new MainPanel(hexMapComponent, infoLayer);
  }

  @Bean
  HexMapComponent hexMapComponent(GameMap gameMap, MapView mapView, List<Painter> painters) {
    return new HexMapComponent(gameMap, mapView, painters);
  }

  @Bean
  JLayer<HexMapComponent> infoLayer(HexMapComponent hexMapComponent, final BufferedImage highlightImage,
                                    TerrainRepository terrainRepository) {
    return new JLayer<>(hexMapComponent, new InfoLayerUI(hexMapComponent, highlightImage, terrainRepository));
  }

  @Bean
  BufferedImage highlightImage(Drawer drawer, HexMapComponent hexMapComponent) {
    return drawer.drawHex(Blueprints.highlight(Hex.sizeOf(hexMapComponent.getMapView()
                                                                         .getZoomLevel())));
  }

}
