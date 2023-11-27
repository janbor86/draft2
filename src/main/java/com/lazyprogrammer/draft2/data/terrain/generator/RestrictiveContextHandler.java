package com.lazyprogrammer.draft2.data.terrain.generator;

import com.lazyprogrammer.draft2.data.terrain.TerrainType;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class RestrictiveContextHandler implements WaveFunctionContextHandler {

  private final Map<TerrainType, List<TerrainType>> possibleNeighbors;

  public RestrictiveContextHandler() {
    possibleNeighbors = massiveContinent();
  }

  private Map<TerrainType, List<TerrainType>> massiveContinent() {
    return Map.of(
        TerrainType.OCEAN,
        List.of(TerrainType.OCEAN, TerrainType.ISLANDS),
        TerrainType.SEA,
        List.of(TerrainType.SEA, TerrainType.ISLANDS),
        TerrainType.ISLANDS,
        List.of(TerrainType.OCEAN, TerrainType.SEA),
        TerrainType.WETLAND,
        List.of(TerrainType.WETLAND, TerrainType.PLAINS),
        TerrainType.PLAINS,
        List.of(TerrainType.SEA, TerrainType.WETLAND, TerrainType.PLAINS, TerrainType.HILLS),
        TerrainType.HILLS,
        List.of(TerrainType.PLAINS, TerrainType.HILLS, TerrainType.MOUNTAINS),
        TerrainType.MOUNTAINS,
        List.of(TerrainType.HILLS, TerrainType.MOUNTAINS, TerrainType.MOUNTAIN_RANGE),
        TerrainType.MOUNTAIN_RANGE,
        List.of(TerrainType.OCEAN, TerrainType.MOUNTAINS, TerrainType.MOUNTAIN_RANGE)
    );
  }

  private Map<TerrainType, List<TerrainType>> fracturedContinent() {
    return Map.of(
        TerrainType.OCEAN,
        List.of(TerrainType.OCEAN, TerrainType.SEA, TerrainType.ISLANDS),
        TerrainType.SEA,
        List.of(TerrainType.OCEAN, TerrainType.SEA, TerrainType.ISLANDS, TerrainType.PLAINS),
        TerrainType.ISLANDS,
        List.of(TerrainType.OCEAN, TerrainType.SEA),
        TerrainType.WETLAND,
        List.of(TerrainType.WETLAND, TerrainType.PLAINS),
        TerrainType.PLAINS,
        List.of(TerrainType.SEA, TerrainType.WETLAND, TerrainType.PLAINS, TerrainType.HILLS),
        TerrainType.HILLS,
        List.of(TerrainType.PLAINS, TerrainType.HILLS, TerrainType.MOUNTAINS),
        TerrainType.MOUNTAINS,
        List.of(TerrainType.HILLS, TerrainType.MOUNTAINS, TerrainType.MOUNTAIN_RANGE),
        TerrainType.MOUNTAIN_RANGE,
        List.of(TerrainType.MOUNTAINS, TerrainType.MOUNTAIN_RANGE));
  }

  @Override
  public Map<TerrainType, Integer> calculateModifiedProbabilities(
      List<TerrainType> context, Map<TerrainType, Integer> probabilities) {
    log.info("context: {}", context);
    final var allowedNeighbors = new ArrayList<TerrainType>();
    for (int i = 0; i < context.size(); i++) {
      List<TerrainType> possibilities = possibleNeighbors.get(context.get(i));
      if (i == 0) {
        allowedNeighbors.addAll(possibilities);
        continue;
      }
      allowedNeighbors.removeIf(type -> !possibilities.contains(type));
    }
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
