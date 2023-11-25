package com.lazyprogrammer.draft2.swing.data.terrain;

import com.lazyprogrammer.draft2.swing.data.Coordinate;

import java.util.List;

public class SimpleFiller implements MapFillingStrategy {
  @Override
  public Coordinate next(List<Coordinate> allUndefined) {
    return allUndefined.get(0);
  }
}
