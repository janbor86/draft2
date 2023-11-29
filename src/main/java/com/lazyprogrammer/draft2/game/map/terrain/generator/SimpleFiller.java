package com.lazyprogrammer.draft2.game.map.terrain.generator;

import com.lazyprogrammer.draft2.game.map.Coordinate;

import java.util.List;

public class SimpleFiller implements MapFillingStrategy {
  @Override
  public Coordinate next(List<Coordinate> allUndefined) {
    return allUndefined.get(0);
  }
}
