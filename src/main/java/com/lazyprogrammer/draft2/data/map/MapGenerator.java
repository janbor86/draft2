package com.lazyprogrammer.draft2.data.map;

import com.lazyprogrammer.draft2.data.GameMap;

public interface MapGenerator {

  GameMap createGameMap(MapConfig mapConfig);
}
