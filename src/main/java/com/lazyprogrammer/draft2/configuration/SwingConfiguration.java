package com.lazyprogrammer.draft2.configuration;

import com.lazyprogrammer.draft2.ui.swing.blueprint.Hex;
import com.lazyprogrammer.draft2.ui.swing.map.HexMapComponent;
import com.lazyprogrammer.draft2.ui.swing.InfoLayerUI;
import com.lazyprogrammer.draft2.ui.swing.MainPanel;
import com.lazyprogrammer.draft2.ui.swing.map.MapMouseAdapter;
import com.lazyprogrammer.draft2.ui.swing.map.WaveFunctionAdapter;
import com.lazyprogrammer.draft2.ui.swing.map.WaveFunctionKeyAdapter;
import com.lazyprogrammer.draft2.ui.swing.blueprint.Blueprints;
import com.lazyprogrammer.draft2.data.GameMap;
import com.lazyprogrammer.draft2.data.terrain.generator.TerrainGenerator;
import com.lazyprogrammer.draft2.data.terrain.TerrainRepository;
import com.lazyprogrammer.draft2.ui.swing.graphics.Drawer;
import com.lazyprogrammer.draft2.ui.swing.graphics.Painter;
import com.lazyprogrammer.draft2.ui.swing.map.MapView;
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
  HexMapComponent hexMapComponent(GameMap gameMap, MapView mapView, List<Painter> painters,
                                  TerrainGenerator terrainGenerator, TerrainRepository terrainRepository) {
    final var hexMapComponent = new HexMapComponent(gameMap.getCoordinates(), mapView, painters);
    final var mapMouseAdapter = new MapMouseAdapter(hexMapComponent);
    hexMapComponent.addMouseListener(mapMouseAdapter);
    hexMapComponent.addMouseMotionListener(mapMouseAdapter);
    final var waveFunctionAdapter = new WaveFunctionAdapter(terrainGenerator, hexMapComponent);
    hexMapComponent.addMouseListener(waveFunctionAdapter);
    hexMapComponent.setFocusable(true);
    final var waveFunctionKeyAdapter = new WaveFunctionKeyAdapter(terrainRepository, terrainGenerator, hexMapComponent);
    hexMapComponent.addKeyListener(waveFunctionKeyAdapter);
    return hexMapComponent;
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
