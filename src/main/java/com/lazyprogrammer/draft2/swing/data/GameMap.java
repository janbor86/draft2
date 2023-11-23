package com.lazyprogrammer.draft2.swing.data;

import com.lazyprogrammer.draft2.swing.map.MapConfig;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class GameMap {

  private static final Set<Point> neighbors = Set.of(new Point(-1, 0), new Point(-1, 1), new Point(0, -1),
      new Point(0, +1), new Point(1, 0), new Point(1, 1));
  private final Map<Point, Tile> tiles;

  public GameMap(MapConfig mapConfig) {
    tiles = new HashMap<>();
    for (int i = 0; i < mapConfig.columnNo(); i++) {
      for (int j = 0; j < mapConfig.rowNo(); j++) {
        tiles.put(new Point(i, j), new Tile(new TileInfo()));
      }
    }
  }

  public Integer read(Point coordinate, TileAttribute attribute) {
    return tiles.get(coordinate)
                .info()
                .get(attribute);
  }

  public void write(Point coordinate, TileAttribute attribute, Integer value) {
    tiles.get(coordinate)
         .info()
         .set(attribute, value);
  }

  public Set<Point> getCoordinates() {
    return tiles.keySet();
  }

  public Set<Point> getNeighbors(Point coordinate) {
    return neighbors.stream()
                    .map(n -> {
                      final var point = new Point(coordinate);
                      var transformationOffset = -1 * n.x * n.x * coordinate.x % 2;
                      point.translate(n.x, n.y + +transformationOffset);
                      return point;
                    })
                    .filter(tiles::containsKey)
                    .collect(Collectors.toSet());
  }
}
