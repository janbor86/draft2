package com.lazyprogrammer.draft2.game.map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapConfiguration {

  @Bean
  MapConfig mapConfig() {
    return new MapConfig(128, 80, 5);
  }

  @Bean
  GameMap gameMap(MapConfig mapConfig, MapGenerator mapGenerator) {
    return mapGenerator.createGameMap(mapConfig);
  }


}
