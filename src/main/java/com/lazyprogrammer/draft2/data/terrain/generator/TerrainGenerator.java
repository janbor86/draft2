package com.lazyprogrammer.draft2.data.terrain.generator;

import com.lazyprogrammer.draft2.data.Coordinate;
import com.lazyprogrammer.draft2.data.terrain.Terrain;
import com.lazyprogrammer.draft2.data.terrain.TerrainRepository;
import com.lazyprogrammer.draft2.data.terrain.TerrainType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class TerrainGenerator {

  private final WaveFunctionCatalyst wfc;
  private final TerrainRepository repository;
  private final MapFillingStrategy mapFillingStrategy;

  public List<Coordinate> generate(int steps) {
    if (steps < 1) {
      return Collections.emptyList();
    }
    List<Coordinate> allUndefined = new ArrayList<>(repository.findAll(TerrainType.UNDEFINED));
    var counter = Math.min(steps, allUndefined.size());
    List<Coordinate> generatedTiles = new ArrayList<>();
    while (counter-- > 0) {
      var next = mapFillingStrategy.next(allUndefined);
      generateTerrainAt(next);
      generatedTiles.add(next);
      allUndefined.remove(next);
    }
    return generatedTiles;
  }

  public void generateTerrainAt(Coordinate coordinate) {
    if (hasBeenDefined(coordinate)) return;
    if (wfc.hasCollapsed()) {
      log.info("Wave function already collapsed!");
      return;
    }
    final var neighboringTerrain = repository.findNeighboringTerrain(coordinate, 1);
    final var outcome = wfc.collapse(neighboringTerrain);
    log.info("{} has been generated at {}", outcome, coordinate);
    repository.write(coordinate, outcome);
  }

  public boolean hasBeenDefined(Coordinate coordinate) {
    return repository.findTerrain(coordinate).isDefined();
  }

  public void load() {
    final var all = repository.findAll();
    all.values().stream().filter(Terrain::isDefined).map(Terrain::type).forEach(wfc::reduce);
  }
}
