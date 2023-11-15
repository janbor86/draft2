package com.lazyprogrammer.draft2.swing.map;

import com.lazyprogrammer.draft2.swing.Hex;

import java.awt.Point;

public record HexMapConfig(int columnNo, int rowNo, Hex hex) {

  public int hexHeight() {
    return (int) hex.getHeight();
  }

  public int hexWidth() {
    return (int) hex.getWidth();
  }

  public int verticalSpacing() {
    return hexHeight();
  }

  public int horizontalSpacing() {
    return (int) (hex.getWidth() * 0.75D);
  }

  public int width() {
    return horizontalSpacing() * columnNo + hexWidth() / 4;
  }

  public int height() {
    return verticalSpacing() * rowNo;
  }

  public int hexSize() {
    return hex.getSize();
  }

  public Point translateCoordinateToScreenLocation(Point coordinate, Point offset) {
    var x = coordinate.x * horizontalSpacing();
    var verticalOffset = (int) (((coordinate.x % 2) * hexHeight()) / 2D);
    var y = coordinate.y * verticalSpacing() - verticalOffset;
    return new Point(x + offset.x, y + offset.y);
  }
}
