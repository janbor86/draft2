package com.lazyprogrammer.draft2.configuration;

import com.lazyprogrammer.draft2.game.map.GameMap;
import com.lazyprogrammer.draft2.game.map.terrain.TerrainRepository;
import com.lazyprogrammer.draft2.game.map.terrain.generator.TerrainGenerator;
import com.lazyprogrammer.draft2.ui.swing.InfoLayerUI;
import com.lazyprogrammer.draft2.ui.swing.MainPanel;
import com.lazyprogrammer.draft2.ui.swing.graphics.Drawer;
import com.lazyprogrammer.draft2.ui.swing.graphics.Painter;
import com.lazyprogrammer.draft2.ui.swing.graphics.PaintingConfiguration;
import com.lazyprogrammer.draft2.ui.swing.map.HexMapComponent;
import com.lazyprogrammer.draft2.ui.swing.map.MapMouseAdapter;
import com.lazyprogrammer.draft2.ui.swing.map.MapPaintKeyAdapter;
import com.lazyprogrammer.draft2.ui.swing.map.MapView;
import com.lazyprogrammer.draft2.ui.swing.map.WaveFunctionAdapter;
import com.lazyprogrammer.draft2.ui.swing.map.WaveFunctionKeyAdapter;
import java.util.List;
import javax.swing.JLayer;
import javax.swing.JPanel;
import org.springframework.context.ApplicationEventPublisher;
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
      TerrainGenerator terrainGenerator,
      TerrainRepository terrainRepository,
      ApplicationEventPublisher publisher) {
    final var hexMapComponent =
        new HexMapComponent(
            gameMap.getCoordinates(), mapView, painters, new PaintingConfiguration(), publisher);
    final var mapMouseAdapter = new MapMouseAdapter(hexMapComponent);
    hexMapComponent.addMouseAdapter(mapMouseAdapter);
    final var waveFunctionAdapter = new WaveFunctionAdapter(terrainGenerator, hexMapComponent);
    hexMapComponent.addMouseListener(waveFunctionAdapter);
    hexMapComponent.setFocusable(true);
    final var waveFunctionKeyAdapter =
        new WaveFunctionKeyAdapter(terrainRepository, terrainGenerator, hexMapComponent);
    hexMapComponent.addKeyListener(waveFunctionKeyAdapter);
    final var mapPaintKeyAdapter = new MapPaintKeyAdapter(hexMapComponent);
    hexMapComponent.addKeyListener(mapPaintKeyAdapter);
    return hexMapComponent;
  }

  @Bean
  JLayer<HexMapComponent> infoLayer(
      HexMapComponent hexMapComponent, Drawer drawer, TerrainRepository terrainRepository) {
    return new JLayer<>(
        hexMapComponent, new InfoLayerUI(hexMapComponent.getMapView(), drawer, terrainRepository));
  }
}
