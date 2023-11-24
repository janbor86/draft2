package com.lazyprogrammer.draft2.swing.data.terrain;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


@RequiredArgsConstructor
public class WaveFunctionCatalyst {

  private final Map<TerrainType, Integer> probabilities;
  private final Map<TerrainType, List<TerrainType>> possibleNeighbors;
  private final Random random;

  public boolean hasCollapsed() {
    return getPossibilityRange(probabilities) == 0;
  }

  public TerrainType collapse(List<TerrainType> context) {
    if (this.hasCollapsed())
      throw new IllegalStateException("This wave function has already collapsed");
    if (context.isEmpty())
      return collapse();
    final var probabilitiesBasedOnContext = calculateModifiedProbabilities(context);
    return collapse(probabilitiesBasedOnContext);
  }

  private TerrainType collapse(Map<TerrainType, Integer> probabilities) {
    var possibilityRange = getPossibilityRange(probabilities);
    if (possibilityRange == 0) {
      return collapse();
    }
    var outcome = random.nextInt(possibilityRange) + 1;
    var counter = 0;
    for (Map.Entry<TerrainType, Integer> entry : probabilities.entrySet()) {
      TerrainType key = entry.getKey();
      Integer value = entry.getValue();
      counter += value;
      if (counter >= outcome) {
        return key;
      }
    }
    throw new AssertionError("We shouldn't be here");
  }

  private TerrainType collapse() {
    return collapse(probabilities);
  }

  private Map<TerrainType, Integer> calculateModifiedProbabilities(List<TerrainType> context) {
    final var allowedNeighbors = context.stream()
                                        .flatMap(terrainType -> possibleNeighbors.get(terrainType)
                                                                                 .stream())
                                        .toList();
    final Map<TerrainType, Integer> probabilitiesBasedOnContext = new HashMap<>();
    allowedNeighbors.forEach(terrainType -> probabilitiesBasedOnContext.compute(terrainType,
        (key, oldValue) -> probabilities.get(key) + (oldValue != null ? oldValue : 0)));
    return probabilitiesBasedOnContext;
  }

  private int getPossibilityRange(Map<TerrainType, Integer> probabilities) {
    return probabilities.values()
                        .stream()
                        .reduce(0, Integer::sum);
  }

}
