package com.lazyprogrammer.draft2.data;

import com.lazyprogrammer.draft2.data.map.MapConfig;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class GameMap {

  private static final Set<Coordinate> neighbors =
      Set.of(
          new Coordinate(-1, 0),
          new Coordinate(-1, 1),
          new Coordinate(0, -1),
          new Coordinate(0, +1),
          new Coordinate(1, 0),
          new Coordinate(1, 1));
  private final Map<Coordinate, Tile> tiles;

  public GameMap(MapConfig mapConfig) {
    tiles = new HashMap<>();
    for (int i = 0; i < mapConfig.columnNo(); i++) {
      for (int j = 0; j < mapConfig.rowNo(); j++) {
        tiles.put(new Coordinate(i, j), new Tile(new TileInfo()));
      }
    }
  }

  public Integer read(Coordinate coordinate, TileAttribute attribute) {
    return tiles.get(coordinate).info().get(attribute);
  }

  public void write(Coordinate coordinate, TileAttribute attribute, Integer value) {
    tiles.get(coordinate).info().set(attribute, value);
  }

  public Set<Coordinate> getCoordinates() {
    return new HashSet<>(tiles.keySet());
  }

  public Set<Coordinate> getNeighbors(Coordinate coordinate) {
    return neighbors.stream()
        .map(
            n -> {
              var x = coordinate.x() + n.x();
              var transformationOffset = -1 * n.x() * n.x() * coordinate.x() % 2;
              var y = coordinate.y() + n.y() + transformationOffset;
              return new Coordinate(x, y);
            })
        .filter(tiles::containsKey)
        .collect(Collectors.toSet());
  }
}
