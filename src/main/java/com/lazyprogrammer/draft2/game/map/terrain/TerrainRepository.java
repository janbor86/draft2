package com.lazyprogrammer.draft2.game.map.terrain;

import com.lazyprogrammer.draft2.game.map.Coordinate;
import com.lazyprogrammer.draft2.game.map.GameMap;
import com.lazyprogrammer.draft2.game.map.TileAttribute;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class TerrainRepository {

  private final GameMap gameMap;

  public void write(Coordinate coordinate, TerrainType terrainType) {
    gameMap.write(coordinate, TileAttribute.TERRAIN, terrainType.ordinal());
  }

  public List<TerrainType> findNeighboringTerrain(Coordinate coordinate, int range) {
    var neighbors = new HashSet<Coordinate>();
    if (range < 1) return Collections.emptyList();
    neighbors.addAll(gameMap.getNeighbors(coordinate));
    while (--range > 0) {
      var newNeighbors = new HashSet<Coordinate>();
      neighbors.forEach(n -> newNeighbors.addAll(gameMap.getNeighbors(n)));
      neighbors.addAll(newNeighbors);
    }
    return neighbors.stream()
        .map(this::findTerrain)
        .filter(Terrain::isDefined)
        .map(Terrain::type)
        .toList();
  }

  public Terrain findTerrain(Coordinate coordinate) {
    var terrainCode = gameMap.read(coordinate, TileAttribute.TERRAIN);
    return Terrain.of(terrainCode);
  }

  public List<TerrainType> findNeighboringTerrain(Coordinate coordinate) {
    return findNeighboringTerrain(coordinate, 1);
  }

  public List<Coordinate> findAll(TerrainType terrainType) {
    final var all = findAll();
    return all.entrySet().stream()
        .filter(e -> terrainType == e.getValue().type())
        .map(Map.Entry::getKey)
        .toList();
  }

  public Map<Coordinate, Terrain> findAll() {
    return gameMap.getCoordinates().stream().collect(Collectors.toMap(c -> c, this::findTerrain));
  }
}
