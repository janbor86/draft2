package com.lazyprogrammer.draft2.swing.data.terrain;

import com.lazyprogrammer.draft2.swing.data.Coordinate;
import com.lazyprogrammer.draft2.swing.data.GameMap;
import com.lazyprogrammer.draft2.swing.data.TileAttribute;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class TerrainRepository {

  private final GameMap gameMap;

  public Map<Coordinate, Terrain> findAll() {
    return gameMap.getCoordinates()
                  .stream()
                  .collect(Collectors.toMap(c -> c, this::findTerrain));
  }

  public Terrain findTerrain(Coordinate coordinate) {
    var terrainCode = gameMap.read(coordinate, TileAttribute.TERRAIN);
    return Terrain.of(terrainCode);
  }

  public void write(Coordinate coordinate, TerrainType terrainType) {
    gameMap.write(coordinate, TileAttribute.TERRAIN, terrainType.ordinal());
  }

  public List<TerrainType> findNeighboringTerrain(Coordinate coordinate) {
    final var neighbors = gameMap.getNeighbors(coordinate);
    return neighbors.stream()
                    .map(this::findTerrain)
                    .filter(Terrain::isDefined)
                    .map(Terrain::type)
                    .toList();
  }

  public List<Coordinate> findAll(TerrainType terrainType) {
    final var all = findAll();
    return all.entrySet()
              .stream()
              .filter(e -> terrainType == e.getValue()
                                           .type())
              .map(Map.Entry::getKey)
              .toList();
  }
}
