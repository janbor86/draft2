package com.lazyprogrammer.draft2.data.terrain.generator;

import com.lazyprogrammer.draft2.data.Coordinate;
import com.lazyprogrammer.draft2.data.map.MapConfig;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
public class OkayFiller implements MapFillingStrategy {

  private final MapConfig mapConfig;
  private final Random random;

  @Override
  public Coordinate next(List<Coordinate> allUndefined) {
    int size = allUndefined.size();
    int pivot = size % 3;
    int slice = size / 6;
    int fullSize = mapConfig.columnNo() * mapConfig.rowNo();
    int correctedColumnNo = mapConfig.rowNo() * size / fullSize;
    int random = this.random.nextInt(-1, 2);
    int jiggle = correctedColumnNo * random;
    int index = Math.max(Math.min(2 * pivot * slice + slice + jiggle, size - 1), 0);
    return allUndefined.get(index);
  }
}
