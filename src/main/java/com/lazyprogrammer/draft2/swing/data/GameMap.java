package com.lazyprogrammer.draft2.swing.data;

import lombok.Getter;
import net.lazyprogrammer.hex.swing.map.HexMapConfig;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class GameMap {

  @Getter
  private final HexMapConfig mapConfig;
  private final Map<Point, Tile> tiles;

  public GameMap(HexMapConfig mapConfig) {
    this.mapConfig = mapConfig;
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

}
