package com.lazyprogrammer.draft2.swing.map;

import com.lazyprogrammer.draft2.swing.data.GameMap;

public interface MapGenerator {

  public GameMap createGameMap(MapConfig mapConfig);
}
