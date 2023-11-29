package com.lazyprogrammer.draft2.game.map;

import java.util.Arrays;
import java.util.Random;

public class Maps {

  public static MapMatrix createLameOne(MapConfig mapConfig) {
    final var integers = initializeMap(mapConfig.columnNo(), mapConfig.rowNo());
    return new MapMatrix(integers);
  }

  private static Integer[][] initializeMap(int columnNo, int rowNo) {
    final Integer[][] map = new Integer[columnNo][rowNo];
    createOcean(map);
    final var random = new Random();
    createLand(map, random);
    createForest(map, random);
    createMountains(map, random);
    return map;
  }

  private static void createMountains(Integer[][] map, Random random) {
    final var columnNo = map.length;
    final var rowNo = map[0].length;
    final var centerX = columnNo / 2;
    final var centerY = rowNo / 2;
    final var landLimit = columnNo * rowNo / 27;
    int landCount = 0;
    while (landCount < landLimit) {
      final var posX = range(random, 3, columnNo - 3);
      final var posY = range(random, 3, rowNo - 3);
      if (map[posX][posY] > 0) {
        final var X = Math.abs(centerX - posX);
        final var Y = Math.abs(centerY - posY);
        final var value = 3 - Math.min(range(random, 0, X + Y), 3);
        if (value == 3) {
          map[posX][posY] = value;
          landCount++;
        }
      }
    }
  }

  private static void createForest(Integer[][] map, Random random) {
    final var columnNo = map.length;
    final var rowNo = map[0].length;
    final var centerX = columnNo / 2;
    final var centerY = rowNo / 2;
    final var landLimit = columnNo * rowNo / 9;
    int landCount = 0;
    while (landCount < landLimit) {
      final var posX = range(random, 2, columnNo - 2);
      final var posY = range(random, 2, rowNo - 2);
      if (map[posX][posY] > 0) {
        final var X = Math.abs(centerX - posX);
        final var Y = Math.abs(centerY - posY);
        final var value = 2 - Math.min(range(random, 0, X + Y), 2);
        if (value == 2) {
          map[posX][posY] = value;
          landCount++;
        }
      }
    }
  }

  private static void createLand(Integer[][] map, Random random) {
    final var columnNo = map.length;
    final var rowNo = map[0].length;
    final var centerX = columnNo / 2;
    final var centerY = rowNo / 2;
    final var landLimit = columnNo * rowNo / 3;
    int landCount = 0;
    while (landCount < landLimit) {
      final var posX = range(random, 1, columnNo - 1);
      final var posY = range(random, 1, rowNo - 1);
      if (map[posX][posY] != 1) {
        final var X = Math.abs(centerX - posX);
        final var Y = Math.abs(centerY - posY);
        final var value = 1 - Math.min(range(random, 0, X + Y), 1);
        if (value == 1) {
          map[posX][posY] = value;
          landCount++;
        }
      }
    }
  }

  private static void createOcean(Integer[][] map) {
    for (Integer[] integers : map)
      Arrays.fill(integers, 0);
  }

  private static int range(Random random, int min, int max) {
    if (min == max)
      return min;
    return random.nextInt(max - min) + min;
  }

  public static MapMatrix emptyOne(MapConfig mapConfig) {
    Integer[][] map = new Integer[mapConfig.columnNo()][mapConfig.rowNo()];
    for (Integer[] integers : map)
      Arrays.fill(integers, 0);
    return new MapMatrix(map);
  }
}
