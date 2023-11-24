package com.lazyprogrammer.draft2.swing.data;

import com.lazyprogrammer.draft2.swing.map.MapConfig;

import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class GameMap {

  private static final Set<Coordinate> neighbors = Set.of(
      new Coordinate(-1, 0),
      new Coordinate(-1, 1),
      new Coordinate(0, -1),
      new Coordinate(0, +1),
      new Coordinate(1, 0),
      new Coordinate(1, 1)
  );
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
    return tiles.get(coordinate)
                .info()
                .get(attribute);
  }

  public void write(Coordinate coordinate, TileAttribute attribute, Integer value) {
    tiles.get(coordinate)
         .info()
         .set(attribute, value);
  }

  public Set<Coordinate> getCoordinates() {
    return new HashSet<>(tiles.keySet());
  }

  public Set<Coordinate> getNeighbors(Coordinate coordinate) {
    return neighbors.stream()
                    .map(n -> {
                      final var point = new Point(coordinate.x(), coordinate.y());
                      var transformationOffset = -1 * n.x() * n.x() * coordinate.x() % 2;
                      point.translate(n.x(), n.y() + transformationOffset);
                      return new Coordinate(point.x, point.y);
                    })
                    .filter(tiles::containsKey)
                    .collect(Collectors.toSet());
  }
}
