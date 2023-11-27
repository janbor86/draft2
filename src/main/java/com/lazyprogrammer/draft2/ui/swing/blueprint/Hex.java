package com.lazyprogrammer.draft2.ui.swing.blueprint;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.awt.Polygon;
import java.awt.Shape;

@Getter
@EqualsAndHashCode(callSuper = false)
public final class Hex extends Polygon {

  private int size;
  private final int correction;
  private double width;
  private double height;

  private Hex(int sizeOfTheHex, int correction) {
    this.correction = correction;
    resize(sizeOfTheHex);
  }

  public static Shape sizeOf(int size, int correction) {
    return new Hex(size, correction);
  }

  public void resize(int sizeOfTheHex) {
    if (sizeOfTheHex < 0)
      throw new IllegalArgumentException("Size of the hex can not be smaller than 0!");
    size = sizeOfTheHex;
    width = size * 2D;
    height = size * MathConst.SQRT3;
    resetPoints();
  }

  public static Hex sizeOf(int sizeOfTheHex) {
    return new Hex(sizeOfTheHex, 0);
  }

  private void resetPoints() {
    reset();
    for (int i = 0; i < 6; i++) {
      final var x =
          (int)
              Math.round((size + correction) * MathConst.HEXAGON_POINTS_ANGLE_COS[i] + width / 2D);
      final var y =
          (int)
              Math.round((size + correction) * MathConst.HEXAGON_POINTS_ANGLE_SIN[i] + height / 2D);
      addPoint(x, y);
    }
  }
}
