package com.lazyprogrammer.draft2.ui.swing.blueprint;

public class MathConst {

  private MathConst() {
    throw new IllegalStateException("Utility class");
  }

  public static final double SQRT3 = Math.sqrt(3.0D);
  public static final double HEXAGON_POINTS_ANGLE = Math.PI / 3.0D;

  public static final double[] HEXAGON_POINTS_ANGLE_SIN =
      new double[] {
        Math.sin(0D),
        Math.sin(MathConst.HEXAGON_POINTS_ANGLE),
        Math.sin(2D * MathConst.HEXAGON_POINTS_ANGLE),
        Math.sin(3D * MathConst.HEXAGON_POINTS_ANGLE),
        Math.sin(4D * MathConst.HEXAGON_POINTS_ANGLE),
        Math.sin(5D * MathConst.HEXAGON_POINTS_ANGLE)
      };

  public static final double[] HEXAGON_POINTS_ANGLE_COS =
      new double[] {
        Math.cos(0D),
        Math.cos(MathConst.HEXAGON_POINTS_ANGLE),
        Math.cos(2D * MathConst.HEXAGON_POINTS_ANGLE),
        Math.cos(3D * MathConst.HEXAGON_POINTS_ANGLE),
        Math.cos(4D * MathConst.HEXAGON_POINTS_ANGLE),
        Math.cos(5D * MathConst.HEXAGON_POINTS_ANGLE)
      };
}
