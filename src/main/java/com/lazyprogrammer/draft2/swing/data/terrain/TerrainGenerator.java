package com.lazyprogrammer.draft2.swing.data.terrain;

import com.lazyprogrammer.draft2.swing.data.Coordinate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class TerrainGenerator {

  private final WaveFunctionCatalyst wfc;
  private final TerrainRepository repository;

  public boolean hasBeenDefined(Coordinate coordinate) {
    return repository.findTerrain(coordinate)
                     .isDefined();
  }

  public void generateTerrainAt(Coordinate coordinate) {
    if (hasBeenDefined(coordinate))
      return;
    if (wfc.hasCollapsed()) {
      log.info("Wave function already collapsed!");
      return;
    }
    final var neighboringTerrain = repository.findNeighboringTerrain(coordinate);
    final var outcome = wfc.collapse(neighboringTerrain);
    log.info("{} has been generated at {}", outcome, coordinate);
    repository.write(coordinate, outcome);
  }

  public void load() {
    final var all = repository.findAll();
    all.values()
       .stream()
       .filter(Terrain::isDefined)
       .map(Terrain::type)
       .forEach(wfc::reduce);
  }
}
