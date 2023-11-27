package com.lazyprogrammer.draft2.ui.swing.blueprint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.Stroke;

public class Blueprints {
  public static final Blueprint EMPTY = Blueprint.builder().shape(new Polygon()).build();
  public static final Color OCEAN = new Color(0, 78, 150);
  public static final Color SEA = new Color(58, 116, 200);
  public static final Color SHALLOW = new Color(85, 162, 216);
  public static final Color SWAMP = new Color(107, 80, 59);
  public static final Color GRASS = new Color(195, 184, 41);
  public static final Color HILLS = new Color(68, 139, 81);
  public static final Color MOUNTAIN = new Color(152, 191, 158);
  public static final Color PEEKS = new Color(166, 204, 188);
  public static final Color STROKE_COLOR = new Color(20, 21, 22);
  public static final Color HIGHLIGHT_COLOR = new Color(248, 248, 248);
  public static final Color TRANSLUCENT = new Color(0, 0, 0, 0);
  private static final float GRID_STROKE_WIDTH = 0.1F;
  private static final float HIGHLIGHT_STROKE_WIDTH = 4F;

  public static Blueprint highlight(Shape shape) {
    return Blueprint.builder()
        .shape(shape)
        .stroke(getStroke(HIGHLIGHT_STROKE_WIDTH))
        .strokeColor(HIGHLIGHT_COLOR)
        .fillColor(TRANSLUCENT)
        .build();
  }

  private static Stroke getStroke(float width) {
    return new BasicStroke(width, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
  }

  public static Blueprint grid(int size, int gridStrokeWidth) {
    return Blueprint.builder()
        .shape(Hex.sizeOf(size))
        .strokeColor(STROKE_COLOR)
        .stroke(getStroke((gridStrokeWidth + size) * GRID_STROKE_WIDTH))
        .build();
  }

  public static Blueprint terrain(int size, int terrainCode) {
    final var basic = Blueprint.builder().shape(Hex.sizeOf(size, 1));
    switch (terrainCode) {
      case 0 -> basic.fillColor(OCEAN);
      case 1 -> basic.fillColor(SEA);
      case 2 -> basic.fillColor(SHALLOW);
      case 3 -> basic.fillColor(SWAMP);
      case 4 -> basic.fillColor(GRASS);
      case 5 -> basic.fillColor(HILLS);
      case 6 -> basic.fillColor(MOUNTAIN);
      case 7 -> basic.fillColor(PEEKS);
      default -> basic.fillColor(TRANSLUCENT);
    }
    return basic.build();
  }
}
