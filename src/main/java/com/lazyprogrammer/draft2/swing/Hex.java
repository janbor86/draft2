package com.lazyprogrammer.draft2.swing;

import lombok.Getter;

import java.awt.Polygon;

public final class Hex extends Polygon {

  @Getter
  private int size;
  @Getter
  private double width;
  @Getter
  private double height;

  private Hex(int sizeOfTheHex) {
    resize(sizeOfTheHex);
  }

  public void resize(int sizeOfTheHex) {
    if (sizeOfTheHex < 0)
      throw new IllegalArgumentException("Size of the hex can not be smaller than 0!");
    size = sizeOfTheHex;
    width = size * 2D;
    height = size * MathConst.SQRT3;
    resetPoints();
  }

  static Hex sizeOf(int sizeOfTheHex) {
    return new Hex(sizeOfTheHex);
  }

  private void resetPoints() {
    reset();
    for (int i = 0; i < 6; i++) {
      final var x = (int) (size * MathConst.HEXAGON_POINTS_ANGLE_COS[i] + width / 2D);
      final var y = (int) (size * MathConst.HEXAGON_POINTS_ANGLE_SIN[i] + height / 2D);
      addPoint(x, y);
    }
  }
}
