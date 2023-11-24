package com.lazyprogrammer.draft2.swing.map;

import com.lazyprogrammer.draft2.swing.data.Coordinate;
import com.lazyprogrammer.draft2.swing.data.GameMap;
import com.lazyprogrammer.draft2.swing.data.TileAttribute;
import com.lazyprogrammer.draft2.swing.data.terrain.TerrainType;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
public class WfcMapGenerator implements MapGenerator {

  @Override
  public GameMap createGameMap(MapConfig mapConfig) {
    var gameMap = new GameMap(mapConfig);
    final var columnNo = mapConfig.columnNo();
    final var rowNo = mapConfig.rowNo();
    var usedCoordinates = initOceansAtTheEdge(columnNo, gameMap, rowNo);
    var availableCoordinates = new HashSet<>(gameMap.getCoordinates());
    availableCoordinates.removeAll(usedCoordinates);
    var coordinate = pickRandomCoord(availableCoordinates);
    log.info("{}", gameMap.read(coordinate, TileAttribute.TERRAIN));
    return gameMap;
  }

  private static Coordinate pickRandomCoord(HashSet<Coordinate> availableCoordinates) {
    int randomIndex = ThreadLocalRandom.current()
                                       .nextInt(availableCoordinates.size());
    int i = 0;
    for (Coordinate coordinate : availableCoordinates) {
      if (i == randomIndex) {
        return coordinate;
      }
      i++;
    }
    throw new AssertionError("We shouldn't be here!");
  }

  private static Set<Coordinate> initOceansAtTheEdge(int columnNo, GameMap gameMap, int rowNo) {
    Set<Coordinate> usedCoordinates = new HashSet<>();
    for (int i = 0; i < columnNo; i++) {
      var p1 = new Coordinate(i, 0);
      gameMap.write(p1, TileAttribute.TERRAIN, TerrainType.OCEAN.ordinal());
      var p2 = new Coordinate(i, 1);
      gameMap.write(p2, TileAttribute.TERRAIN, TerrainType.OCEAN.ordinal());
      var p3 = new Coordinate(i, rowNo - 2);
      gameMap.write(p3, TileAttribute.TERRAIN, TerrainType.OCEAN.ordinal());
      var p4 = new Coordinate(i, rowNo - 1);
      gameMap.write(p4, TileAttribute.TERRAIN, TerrainType.OCEAN.ordinal());
      usedCoordinates.add(p1);
      usedCoordinates.add(p2);
      usedCoordinates.add(p3);
      usedCoordinates.add(p4);
    }
    for (int j = 0; j < rowNo; j++) {
      var p1 = new Coordinate(0, j);
      gameMap.write(p1, TileAttribute.TERRAIN, TerrainType.OCEAN.ordinal());
      var p2 = new Coordinate(1, j);
      gameMap.write(p2, TileAttribute.TERRAIN, TerrainType.OCEAN.ordinal());
      var p3 = new Coordinate(columnNo - 2, j);
      gameMap.write(p3, TileAttribute.TERRAIN, TerrainType.OCEAN.ordinal());
      var p4 = new Coordinate(columnNo - 1, j);
      gameMap.write(p4, TileAttribute.TERRAIN, TerrainType.OCEAN.ordinal());
      usedCoordinates.add(p1);
      usedCoordinates.add(p2);
      usedCoordinates.add(p3);
      usedCoordinates.add(p4);
    }
    return usedCoordinates;
  }
}
