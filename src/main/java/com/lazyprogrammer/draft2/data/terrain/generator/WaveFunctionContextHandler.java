package com.lazyprogrammer.draft2.data.terrain.generator;

import com.lazyprogrammer.draft2.data.terrain.TerrainType;

import java.util.List;
import java.util.Map;

public interface WaveFunctionContextHandler {
  Map<TerrainType, Integer> calculateModifiedProbabilities(
      List<TerrainType> context, Map<TerrainType, Integer> probabilities);
}
