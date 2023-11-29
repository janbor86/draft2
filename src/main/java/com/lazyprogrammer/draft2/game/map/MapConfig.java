package com.lazyprogrammer.draft2.game.map;

import com.lazyprogrammer.draft2.ui.swing.blueprint.MathConst;

import java.util.Objects;

public final class MapConfig {
  private final int columnNo;
  private final int rowNo;
  private int gridSize;

  public MapConfig(int columnNo, int rowNo, int gridSize) {
    this.columnNo = columnNo;
    this.rowNo = rowNo;
    this.gridSize = gridSize;
  }

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

  public int columnNo() {
    return columnNo;
  }

  public int rowNo() {
    return rowNo;
  }

  public int gridSize() {
    return gridSize;
  }

  public void setGridSize(int value) {
    gridSize = value;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) return true;
    if (obj == null || obj.getClass() != this.getClass()) return false;
    var that = (MapConfig) obj;
    return this.columnNo == that.columnNo
        && this.rowNo == that.rowNo
        && this.gridSize == that.gridSize;
  }

  @Override
  public int hashCode() {
    return Objects.hash(columnNo, rowNo, gridSize);
  }
}
