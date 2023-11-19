package com.lazyprogrammer.draft2.swing.data;

import com.lazyprogrammer.draft2.swing.map.MapConfig;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GameMap {

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
}
