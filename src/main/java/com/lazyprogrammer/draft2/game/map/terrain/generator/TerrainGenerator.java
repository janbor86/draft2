package com.lazyprogrammer.draft2.game.map.terrain.generator;

import com.lazyprogrammer.draft2.configuration.DataConfiguration;
import com.lazyprogrammer.draft2.game.map.Coordinate;
import com.lazyprogrammer.draft2.game.map.MapConfig;
import com.lazyprogrammer.draft2.game.map.terrain.Terrain;
import com.lazyprogrammer.draft2.game.map.terrain.TerrainRepository;
import com.lazyprogrammer.draft2.game.map.terrain.TerrainType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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

  public void reset(MapConfig mapConfig) {
    wfc.setProbabilities(DataConfiguration.getProbabilities(mapConfig));
  }

  public void generateTerrainAt(Coordinate coordinate) {
    if (hasBeenDefined(coordinate)) return;
    if (wfc.hasCollapsed()) {
      log.debug("Wave function already collapsed!");
      return;
    }
    final var neighboringTerrain = repository.findNeighboringTerrain(coordinate, 1);
    final var outcome = wfc.collapse(neighboringTerrain);
    log.debug("{} has been generated at {}", outcome, coordinate);
    repository.write(coordinate, outcome);
  }

  public boolean hasBeenDefined(Coordinate coordinate) {
    return repository.findTerrain(coordinate).isDefined();
  }

  public void load() {
    final var all = repository.findAll();
    all.values().stream().filter(Terrain::isDefined).map(Terrain::type).forEach(wfc::reduce);
  }

  public void generate() {
    final var numberOfMissingPieces = repository.findAll(TerrainType.UNDEFINED).size();
    generate(numberOfMissingPieces);
  }
}
