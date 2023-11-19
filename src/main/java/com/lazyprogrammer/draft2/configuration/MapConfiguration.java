package com.lazyprogrammer.draft2.configuration;

import com.lazyprogrammer.draft2.swing.blueprint.BlueprintMap;
import com.lazyprogrammer.draft2.swing.graphics.Drawer;
import com.lazyprogrammer.draft2.swing.graphics.ElevationPainter;
import com.lazyprogrammer.draft2.swing.graphics.Painter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.Point;

@Configuration
public class MapConfiguration {

  @Bean
  Drawer drawer() {
    return new Drawer();
  }

  @Bean
  Painter elevationPainter(final Drawer drawer) {
    return new ElevationPainter(drawer, BlueprintMap.<Point>builder()
                                                    .build());
  }
}
