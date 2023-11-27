package com.lazyprogrammer.draft2.data.terrain.generator;

import com.lazyprogrammer.draft2.data.terrain.TerrainType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Slf4j
@RequiredArgsConstructor
public class WaveFunctionCatalyst {

  private final Map<TerrainType, Integer> probabilities;
  private final Random random;
  private final WaveFunctionContextHandler contextHandler;

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
    if (context.isEmpty()) return collapse();
    final var probabilitiesBasedOnContext =
        contextHandler.calculateModifiedProbabilities(context, probabilities);
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

  private int getPossibilityRange(Map<TerrainType, Integer> probabilities) {
    return probabilities.values().stream().reduce(0, Integer::sum);
  }

  public void reduce(TerrainType type) {
    final var oldValue = probabilities.get(type);
    if (oldValue > 0) probabilities.put(type, oldValue - 1);
  }
}
