package com.lazyprogrammer.draft2.swing.data.terrain;

import com.lazyprogrammer.draft2.swing.data.Coordinate;
import com.lazyprogrammer.draft2.swing.data.GameMap;
import com.lazyprogrammer.draft2.swing.data.TileAttribute;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TerrainRepository {

  private final GameMap gameMap;

  public Terrain findTerrain(Coordinate coordinate) {
    var terrainCode = gameMap.read(coordinate, TileAttribute.TERRAIN);
    return Terrain.of(terrainCode);
  }
}
