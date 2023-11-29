package com.lazyprogrammer.draft2.game.map;

import com.lazyprogrammer.draft2.game.map.terrain.TerrainType;
import java.util.HashSet;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WfcMapGenerator implements MapGenerator {

  @Override
  public GameMap createGameMap(MapConfig mapConfig) {
    var gameMap = new GameMap(mapConfig);
    final var columnNo = mapConfig.columnNo();
    final var rowNo = mapConfig.rowNo();
    var usedCoordinates = initOceansAtTheEdge(columnNo, gameMap, rowNo);
    return gameMap;
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
