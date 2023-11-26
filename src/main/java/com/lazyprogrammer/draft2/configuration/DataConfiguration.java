package com.lazyprogrammer.draft2.configuration;

import com.lazyprogrammer.draft2.swing.data.GameMap;
import com.lazyprogrammer.draft2.swing.data.terrain.*;
import com.lazyprogrammer.draft2.swing.data.terrain.generator.MapFillingStrategy;
import com.lazyprogrammer.draft2.swing.data.terrain.generator.RandomNeighborFiller;
import com.lazyprogrammer.draft2.swing.data.terrain.generator.RestrictiveContextHandler;
import com.lazyprogrammer.draft2.swing.data.terrain.generator.TerrainGenerator;
import com.lazyprogrammer.draft2.swing.data.terrain.generator.WaveFunctionCatalyst;
import com.lazyprogrammer.draft2.swing.data.terrain.generator.WaveFunctionContextHandler;
import com.lazyprogrammer.draft2.swing.map.MapConfig;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataConfiguration {

  @Bean
  TerrainRepository terrainRepository(GameMap gameMap) {
    return new TerrainRepository(gameMap);
  }

  @Bean
  TerrainGenerator terrainGenerator(
      WaveFunctionCatalyst wfc,
      TerrainRepository terrainRepository,
      MapFillingStrategy mapFillingStrategy) {
    final var terrainGenerator = new TerrainGenerator(wfc, terrainRepository, mapFillingStrategy);
    terrainGenerator.load();
    return terrainGenerator;
  }

  @Bean
  MapFillingStrategy mapFillingStrategy(TerrainRepository repository) {
    return new RandomNeighborFiller(repository, new Random());
  }

  @Bean
  WaveFunctionCatalyst waveFunctionCatalyst(
      MapConfig mapConfig, WaveFunctionContextHandler contextHandler) {
    var probabilities = getProbabilities(mapConfig);
    return new WaveFunctionCatalyst(probabilities, new Random(), contextHandler);
  }

  private Map<TerrainType, Integer> getProbabilities(MapConfig mapConfig) {
    Map<TerrainType, Integer> probabilities = new HashMap<>();
    final var columnNo = mapConfig.columnNo();
    final var rowNo = mapConfig.rowNo();
    final var allTile = columnNo * rowNo;
    probabilities.put(TerrainType.OCEAN, 40 * allTile / 100);
    probabilities.put(TerrainType.SEA, 20 * allTile / 100);
    probabilities.put(TerrainType.ISLANDS, 4 * allTile / 100);
    probabilities.put(TerrainType.WETLAND, 3 * allTile / 100);
    probabilities.put(TerrainType.PLAINS, 15 * allTile / 100);
    probabilities.put(TerrainType.HILLS, 9 * allTile / 100);
    probabilities.put(TerrainType.MOUNTAINS, 6 * allTile / 100);
    probabilities.put(TerrainType.MOUNTAIN_RANGE, 3 * allTile / 100);
    final var assigned = probabilities.values().stream().flatMapToInt(IntStream::of).sum();
    probabilities.put(
        TerrainType.PLAINS, probabilities.get(TerrainType.PLAINS) + allTile - assigned);
    return probabilities;
  }

  @Bean
  WaveFunctionContextHandler waveFunctionContextHandler() {
    return new RestrictiveContextHandler();
  }
}
