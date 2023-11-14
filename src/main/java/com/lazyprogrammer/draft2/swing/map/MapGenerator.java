package com.lazyprogrammer.draft2.swing.map;

import net.lazyprogrammer.hex.swing.data.GameMap;
import net.lazyprogrammer.hex.swing.data.TileAttribute;

import java.awt.Point;
import java.util.Random;
import java.util.random.RandomGenerator;

public class MapGenerator {

  public static GameMap createGameMap(HexMapConfig mapConfig) {
    final var gameMap = new GameMap(mapConfig);
    final var random = Random.from(RandomGenerator.getDefault());
    for (int i = 0; i < mapConfig.columnNo(); i++) {
      for (int j = 0; j < mapConfig.rowNo(); j++) {
        gameMap.write(new Point(i, j), TileAttribute.ELEVATION, random.nextInt(16000));
      }
    }
    return gameMap;
  }

}
