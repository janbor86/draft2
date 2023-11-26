package com.lazyprogrammer.draft2.swing.data.terrain.generator;

import com.lazyprogrammer.draft2.swing.data.Coordinate;
import com.lazyprogrammer.draft2.swing.data.terrain.TerrainRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
public class RandomNeighborFiller implements MapFillingStrategy {

  private final TerrainRepository repository;
  private final Random random;

  @Override
  public Coordinate next(List<Coordinate> allUndefined) {
    final var coordinates = new ArrayList<>(allUndefined);
    Collections.shuffle(coordinates);
    return coordinates.stream()
        .filter(c -> !repository.findNeighboringTerrain(c).isEmpty())
        .findFirst()
        .orElseThrow();
  }
}
