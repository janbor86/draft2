package com.lazyprogrammer.draft2.game.map.terrain.generator;

import com.lazyprogrammer.draft2.game.map.Coordinate;

import java.util.List;

public interface MapFillingStrategy {
  Coordinate next(List<Coordinate> allUndefined);
}
