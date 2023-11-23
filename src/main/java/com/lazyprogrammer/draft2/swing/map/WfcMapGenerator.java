package com.lazyprogrammer.draft2.swing.map;

import com.lazyprogrammer.draft2.swing.data.GameMap;
import com.lazyprogrammer.draft2.swing.data.Terrain;
import com.lazyprogrammer.draft2.swing.data.TileAttribute;

import java.awt.Point;

public class WfcMapGenerator implements MapGenerator {

  @Override
  public GameMap createGameMap(MapConfig mapConfig) {
    var gameMap = new GameMap(mapConfig);
    final var columnNo = mapConfig.columnNo();
    final var rowNo = mapConfig.rowNo();
    for (int i = 0; i < columnNo; i++) {
      gameMap.write(new Point(i, 0), TileAttribute.TERRAIN, Terrain.OCEAN.ordinal());
      gameMap.write(new Point(i, 1), TileAttribute.TERRAIN, Terrain.OCEAN.ordinal());
    }
    for (int j = 0; j < rowNo; j++) {
      gameMap.write(new Point(0, j), TileAttribute.TERRAIN, Terrain.OCEAN.ordinal());
      gameMap.write(new Point(1, j), TileAttribute.TERRAIN, Terrain.OCEAN.ordinal());
    }
    return gameMap;
  }
}
