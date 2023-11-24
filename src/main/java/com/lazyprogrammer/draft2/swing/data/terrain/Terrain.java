package com.lazyprogrammer.draft2.swing.data.terrain;

public record Terrain (String name, TerrainType type){

  public static Terrain of(int terrainCode) {
    if (terrainCode < 0 || terrainCode >= TerrainType.values().length) {
      return new Terrain("UNDEFINED", TerrainType.UNDEFINED);
    }
    var type = TerrainType.values()[terrainCode];
    return new Terrain(type.toString(), type);
  }
}
