package com.lazyprogrammer.draft2.swing.map;


import com.lazyprogrammer.draft2.swing.data.GameMap;
import com.lazyprogrammer.draft2.swing.data.Terrain;
import com.lazyprogrammer.draft2.swing.data.TileAttribute;
import lombok.extern.slf4j.Slf4j;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.random.RandomGenerator;
import java.util.stream.Collectors;

@Slf4j
public class RandomElevationGenerator implements MapGenerator {

  public GameMap createGameMap(MapConfig mapConfig) {
    final var gameMap = new GameMap(mapConfig);
    final var random = Random.from(RandomGenerator.getDefault());
    final var columnNo = mapConfig.columnNo();
    final var rowNo = mapConfig.rowNo();
    var bound = 6000;
    var depth = 4000;
    for (int i = 0; i < columnNo; i++) {
      for (int j = 0; j < rowNo; j++)
        if (i < 2 || j < 2 || i > columnNo - 2 || j > rowNo - 2) {
          gameMap.write(new Point(i, j), TileAttribute.ELEVATION, -depth);
        } else {
          var value = random.nextInt(bound) + random.nextInt(bound) - depth;
          gameMap.write(new Point(i, j), TileAttribute.ELEVATION, value);
        }
    }
    bound = bound * 2;
    do {
      RandomElevationGenerator.log.info("smooth");
      final var minMax = smooth(gameMap);
      bound = minMax.y - minMax.x;
    } while (bound > 11111);
    final var elevationRange = bound;
    gameMap.getCoordinates()
           .forEach(point -> {
             final var elevation = gameMap.read(point, TileAttribute.ELEVATION);
             if (elevation < -2001)
               gameMap.write(point, TileAttribute.TERRAIN, Terrain.OCEAN.ordinal());
             else if (elevation < -201)
               gameMap.write(point, TileAttribute.TERRAIN, Terrain.SEA.ordinal());
             else if (elevation < 0)
               gameMap.write(point, TileAttribute.TERRAIN, Terrain.ISLANDS.ordinal());
             else if (elevation < 101)
               gameMap.write(point, TileAttribute.TERRAIN, Terrain.WETLAND.ordinal());
             else if (elevation < 300)
               gameMap.write(point, TileAttribute.TERRAIN, Terrain.PLAINS.ordinal());
             else if (elevation < 1000)
               gameMap.write(point, TileAttribute.TERRAIN, Terrain.HILLS.ordinal());
             else if (elevation < 2500)
               gameMap.write(point, TileAttribute.TERRAIN, Terrain.MOUNTAIN.ordinal());
             else
               gameMap.write(point, TileAttribute.TERRAIN, Terrain.MOUNTAIN_RANGE.ordinal());
           });
    printTerrainStat(gameMap);
    return gameMap;
  }

  private void printTerrainStat(GameMap gameMap) {
    var size = gameMap.getCoordinates()
                      .size();
    log.info("tiles : {}", size);
    var stat = gameMap.getCoordinates()
                      .stream()
                      .collect(Collectors.toMap(c -> gameMap.read(c, TileAttribute.TERRAIN), c -> 1, Integer::sum));
    for (Terrain value : Terrain.values()) {
      var sum = stat.get(value.ordinal());
      if (sum == null) {
        sum = 0;
      }
      log.info("{} : {}({}%)", value, sum, 100 * sum / size);
    }
  }

  private Point smooth(GameMap gameMap) {
    Map<Point, Integer> averages = new HashMap<>();
    gameMap.getCoordinates()
           .forEach(point -> {
             final var neighbors = gameMap.getNeighbors(point);
             neighbors.add(point);
             Double average = neighbors.stream()
                                       .map(n -> gameMap.read(n, TileAttribute.ELEVATION))
                                       .collect(Collectors.averagingInt(value -> value));
             averages.put(point, average.intValue());
           });
    averages.forEach((k, v) -> gameMap.write(k, TileAttribute.ELEVATION, v));
    final var min = averages.values()
                            .stream()
                            .mapToInt(value -> value)
                            .min()
                            .getAsInt();
    final var max = averages.values()
                            .stream()
                            .mapToInt(value -> value)
                            .max()
                            .getAsInt();
    return new Point(min, max);
  }

}
