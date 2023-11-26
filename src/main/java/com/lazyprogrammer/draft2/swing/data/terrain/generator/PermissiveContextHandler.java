package com.lazyprogrammer.draft2.swing.data.terrain.generator;

import com.lazyprogrammer.draft2.swing.data.terrain.TerrainType;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PermissiveContextHandler implements WaveFunctionContextHandler {

  private final Map<TerrainType, List<TerrainType>> possibleNeighbors;

  public PermissiveContextHandler() {
    possibleNeighbors =
        Map.of(
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
            List.of(TerrainType.HILLS, TerrainType.MOUNTAINS),
            TerrainType.MOUNTAINS,
            List.of(TerrainType.MOUNTAINS, TerrainType.MOUNTAIN_RANGE),
            TerrainType.MOUNTAIN_RANGE,
            List.of(TerrainType.MOUNTAIN_RANGE));
  }

  @Override
  public Map<TerrainType, Integer> calculateModifiedProbabilities(
      List<TerrainType> context, Map<TerrainType, Integer> probabilities) {
    log.info("context: {}", context);
    final var allowedNeighbors =
        context.stream().map(possibleNeighbors::get).flatMap(Collection::stream).toList();
    log.info("allowed neighbors: {}", allowedNeighbors);
    final Map<TerrainType, Integer> probabilitiesBasedOnContext = new HashMap<>();
    allowedNeighbors.forEach(
        terrainType ->
            probabilitiesBasedOnContext.compute(
                terrainType,
                (key, oldValue) -> probabilities.get(key) + (oldValue != null ? oldValue : 0)));
    return probabilitiesBasedOnContext;
  }
}
