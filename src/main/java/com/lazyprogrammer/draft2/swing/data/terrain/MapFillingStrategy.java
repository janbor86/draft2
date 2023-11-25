package com.lazyprogrammer.draft2.swing.data.terrain;

import com.lazyprogrammer.draft2.swing.data.Coordinate;

import java.util.List;

public interface MapFillingStrategy {
  Coordinate next(List<Coordinate> allUndefined);
}
