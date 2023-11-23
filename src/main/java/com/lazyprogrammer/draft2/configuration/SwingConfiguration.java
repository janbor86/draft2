package com.lazyprogrammer.draft2.configuration;

import com.lazyprogrammer.draft2.swing.HexMapComponent;
import com.lazyprogrammer.draft2.swing.MainPanel;
import com.lazyprogrammer.draft2.swing.data.GameMap;
import com.lazyprogrammer.draft2.swing.graphics.Painter;
import com.lazyprogrammer.draft2.swing.map.MapView;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.swing.JPanel;
import java.util.List;

@Configuration
public class SwingConfiguration {
  @Bean
  JPanel mainPanel(HexMapComponent hexMapComponent) {
    return new MainPanel(hexMapComponent);
  }

  @Bean
  HexMapComponent hexMapComponent(GameMap gameMap, MapView mapView, List<Painter> painters) {
    return new HexMapComponent(gameMap, mapView, painters);
  }

}
