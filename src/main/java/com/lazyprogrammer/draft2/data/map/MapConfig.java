package com.lazyprogrammer.draft2.data.map;

import com.lazyprogrammer.draft2.ui.swing.blueprint.MathConst;

public record MapConfig(int columnNo, int rowNo, int gridSize) {

  public int hexHeight() {
    return (int) (gridSize * MathConst.SQRT3);
  }

  public int hexWidth() {
    return gridSize * 2;
  }

  public int verticalSpacing() {
    return hexHeight();
  }

  public int horizontalSpacing() {
    return (int) (hexWidth() * 0.75D);
  }

  public int width() {
    return horizontalSpacing() * columnNo + hexWidth() / 4;
  }

  public int height() {
    return verticalSpacing() * rowNo;
  }

  @Override
  public String toString() {
    return "MapConfig["
        + "columnNo="
        + columnNo
        + ", "
        + "rowNo="
        + rowNo
        + ", "
        + "gridSize="
        + gridSize
        + ']';
  }
}
