package com.lazyprogrammer.draft2.configuration;

import com.lazyprogrammer.draft2.swing.data.GameMap;
import com.lazyprogrammer.draft2.swing.data.terrain.TerrainGenerator;
import com.lazyprogrammer.draft2.swing.data.terrain.TerrainRepository;
import com.lazyprogrammer.draft2.swing.data.terrain.TerrainType;
import com.lazyprogrammer.draft2.swing.data.terrain.WaveFunctionCatalyst;
import com.lazyprogrammer.draft2.swing.map.MapConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

@Configuration
public class DataConfiguration {

  @Bean
  TerrainRepository terrainRepository(GameMap gameMap) {
    return new TerrainRepository(gameMap);
  }

  @Bean
  TerrainGenerator terrainGenerator(WaveFunctionCatalyst wfc, TerrainRepository terrainRepository) {
    final var terrainGenerator = new TerrainGenerator(wfc, terrainRepository);
    terrainGenerator.load();
    return terrainGenerator;
  }

  @Bean
  WaveFunctionCatalyst waveFunctionCatalyst(MapConfig mapConfig) {
    var probabilities = getProbabilities(mapConfig);
    var possibleNeighbors = getPossibleNeighbors();
    return new WaveFunctionCatalyst(probabilities, possibleNeighbors, new Random());
  }

  private Map<TerrainType, Integer> getProbabilities(MapConfig mapConfig) {
    Map<TerrainType, Integer> probabilities = new HashMap<>();
    final var columnNo = mapConfig.columnNo();
    final var rowNo = mapConfig.rowNo();
    final var allTile = columnNo * rowNo;
    probabilities.put(TerrainType.OCEAN, 45 * allTile / 100);
    probabilities.put(TerrainType.SEA, 15 * allTile / 100);
    probabilities.put(TerrainType.ISLANDS, 5 * allTile / 100);
    probabilities.put(TerrainType.WETLAND, 4 * allTile / 100);
    probabilities.put(TerrainType.PLAINS, 17 * allTile / 100);
    probabilities.put(TerrainType.HILLS, 8 * allTile / 100);
    probabilities.put(TerrainType.MOUNTAINS, 4 * allTile / 100);
    probabilities.put(TerrainType.MOUNTAIN_RANGE, 2 * allTile / 100);
    final var assigned = probabilities.values()
                                      .stream()
                                      .flatMapToInt(IntStream::of)
                                      .sum();
    probabilities.put(TerrainType.PLAINS, probabilities.get(TerrainType.PLAINS) + allTile - assigned);
    return probabilities;
  }

  private Map<TerrainType, List<TerrainType>> getPossibleNeighbors() {
    return Map.of(
        TerrainType.OCEAN,
        List.of(TerrainType.OCEAN, TerrainType.SEA, TerrainType.ISLANDS),
        TerrainType.SEA,
        List.of(TerrainType.SEA, TerrainType.ISLANDS, TerrainType.PLAINS),
        TerrainType.ISLANDS,
        List.of(TerrainType.OCEAN, TerrainType.SEA, TerrainType.HILLS),
        TerrainType.WETLAND,
        List.of(TerrainType.WETLAND),
        TerrainType.PLAINS,
        List.of(TerrainType.WETLAND, TerrainType.PLAINS, TerrainType.HILLS),
        TerrainType.HILLS,
        List.of(TerrainType.PLAINS, TerrainType.HILLS, TerrainType.MOUNTAINS),
        TerrainType.MOUNTAINS,
        List.of(TerrainType.MOUNTAINS, TerrainType.MOUNTAIN_RANGE),
        TerrainType.MOUNTAIN_RANGE,
        List.of(TerrainType.MOUNTAIN_RANGE)
    );
  }

}
