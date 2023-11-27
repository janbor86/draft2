package com.lazyprogrammer.draft2.data.terrain.generator;

import com.lazyprogrammer.draft2.data.Coordinate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomFiller implements MapFillingStrategy {

  @Override
  public Coordinate next(List<Coordinate> allUndefined) {
    ArrayList<Coordinate> coordinates = new ArrayList<>(allUndefined);
    Collections.shuffle(coordinates);
    return coordinates.get(0);
  }
}
