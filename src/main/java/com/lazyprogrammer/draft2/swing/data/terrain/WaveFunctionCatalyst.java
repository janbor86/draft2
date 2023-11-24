package com.lazyprogrammer.draft2.swing.data.terrain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.BiFunction;

@Slf4j
@RequiredArgsConstructor
public class WaveFunctionCatalyst {

  private final Map<TerrainType, Integer> probabilities;
  private final Map<TerrainType, List<TerrainType>> possibleNeighbors;
  private final Random random;

  @PostConstruct
  void logProbabilities() {
    log.info("Probabilities {}", probabilities);
  }

  public boolean hasCollapsed() {
    return getPossibilityRange(probabilities) == 0;
  }

  public TerrainType collapse(List<TerrainType> context) {
    logProbabilities();
    if (this.hasCollapsed())
      throw new IllegalStateException("This wave function has already collapsed");
    if (context.isEmpty())
      return collapse();
    final var probabilitiesBasedOnContext = calculateModifiedProbabilities(context);
    return collapse(probabilitiesBasedOnContext);
  }

  private TerrainType collapse(Map<TerrainType, Integer> probabilities) {
    log.info("collapse on {}", probabilities);
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
        reduce(key);
        return key;
      }
    }
    throw new AssertionError("We shouldn't be here");
  }

  private TerrainType collapse() {
    return collapse(probabilities);
  }

  private Map<TerrainType, Integer> calculateModifiedProbabilities(List<TerrainType> context) {
    log.info("context: {}", context);
    final var allowedNeighbors = getAllowedNeighbors(context);
    log.info("allowed neighbors: {}", allowedNeighbors);
    return calculateProbabilities(allowedNeighbors);
  }

  private Map<TerrainType, Integer> calculateProbabilities(List<TerrainType> allowedNeighbors) {
    final Map<TerrainType, Integer> probabilitiesBasedOnContext = new HashMap<>();
    allowedNeighbors.forEach(terrainType -> probabilitiesBasedOnContext.compute(terrainType, addTerrainProbability()));
    return probabilitiesBasedOnContext;
  }

  private BiFunction<TerrainType, Integer, Integer> addTerrainProbability() {
    return (key, oldValue) -> probabilities.get(key) + (oldValue != null ? oldValue : 0);
  }

  private List<TerrainType> getAllowedNeighbors(List<TerrainType> context) {
    return context.stream()
                  .map(possibleNeighbors::get)
                  .flatMap(Collection::stream)
                  .toList();
  }

  private int getPossibilityRange(Map<TerrainType, Integer> probabilities) {
    return probabilities.values()
                        .stream()
                        .reduce(0, Integer::sum);
  }

  public void reduce(TerrainType type) {
    final var oldValue = probabilities.get(type);
    if (oldValue > 0)
      probabilities.put(type, oldValue - 1);
  }
}
