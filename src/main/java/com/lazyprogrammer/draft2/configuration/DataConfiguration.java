package com.lazyprogrammer.draft2.configuration;

import com.lazyprogrammer.draft2.swing.data.GameMap;
import com.lazyprogrammer.draft2.swing.data.terrain.TerrainRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataConfiguration {

  @Bean
  TerrainRepository terrainRepository(GameMap gameMap) {
    return new TerrainRepository(gameMap);
  }

}
