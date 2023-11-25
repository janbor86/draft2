package com.lazyprogrammer.draft2.swing.data.terrain;

import java.util.List;
import java.util.Map;

public interface WaveFunctionContextHandler {
  Map<TerrainType, Integer> calculateModifiedProbabilities(
      List<TerrainType> context, Map<TerrainType, Integer> probabilities);
}
