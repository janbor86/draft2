package com.lazyprogrammer.draft2.swing.data.terrain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class PermissiveContextHandler implements WaveFunctionContextHandler {

  private final Map<TerrainType, List<TerrainType>> possibleNeighbors;

  @Override
  public Map<TerrainType, Integer> calculateModifiedProbabilities(List<TerrainType> context,
                                                                  Map<TerrainType, Integer> probabilities) {
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
