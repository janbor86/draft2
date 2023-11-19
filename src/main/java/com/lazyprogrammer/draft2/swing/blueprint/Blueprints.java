package com.lazyprogrammer.draft2.swing.blueprint;


import com.lazyprogrammer.draft2.swing.Hex;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.Stroke;

public class Blueprints {
  public static final Blueprint EMPTY = Blueprint.builder()
                                                 .shape(new Polygon())
                                                 .build();
  public static final Color GRASS = new Color(9, 176, 34);
  public static final Color OCEAN = new Color(0, 94, 184);
  public static final Color FOREST = new Color(89, 78, 46);
  public static final Color MOUNTAIN = new Color(152, 152, 156);
  public static final Color STROKE_COLOR = new Color(20, 21, 22);
  public static final Color HIGHLIGHT_COLOR = new Color(248, 248, 248);
  public static final Color TRANSLUCENT = new Color(0, 0, 0, 0);
  private static final float GRID_STROKE_WIDTH = 1F;
  private static final float HIGHLIGHT_STROKE_WIDTH = 4F;


  private static Stroke getStroke(float width) {
    return new BasicStroke(width, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
  }

  public static Blueprint grassHex(Hex hex) {
    return Blueprint.builder()
                    .shape(hex)
                    .stroke(getStroke(GRID_STROKE_WIDTH))
                    .fillColor(GRASS)
                    .strokeColor(STROKE_COLOR)
                    .build();
  }

  public static Blueprint oceanHex(Hex hex) {
    return Blueprint.builder()
                    .shape(hex)
                    .stroke(getStroke(GRID_STROKE_WIDTH))
                    .fillColor(OCEAN)
                    .strokeColor(STROKE_COLOR)
                    .build();
  }

  public static Blueprint forestHex(Hex hex) {
    return Blueprint.builder()
                    .shape(hex)
                    .stroke(getStroke(GRID_STROKE_WIDTH))
                    .fillColor(FOREST)
                    .strokeColor(STROKE_COLOR)
                    .build();
  }

  public static Blueprint mountain(Hex hex) {
    return Blueprint.builder()
                    .shape(hex)
                    .stroke(getStroke(GRID_STROKE_WIDTH))
                    .strokeColor(STROKE_COLOR)
                    .fillColor(MOUNTAIN)
                    .build();
  }

  public static Blueprint highlight(Shape shape) {
    return Blueprint.builder()
                    .shape(shape)
                    .stroke(getStroke(HIGHLIGHT_STROKE_WIDTH))
                    .strokeColor(HIGHLIGHT_COLOR)
                    .fillColor(TRANSLUCENT)
                    .build();
  }

  public static Blueprint grid(int size) {
    return Blueprint.builder()
                    .shape(Hex.sizeOf(size))
                    .strokeColor(STROKE_COLOR)
                    .stroke(getStroke(GRID_STROKE_WIDTH))
                    .build();
  }

  public static Blueprint elevation(int size, int brightness) {
    return Blueprint.builder()
                    .shape(Hex.sizeOf(size))
                    .fillColor(new Color(brightness, brightness, brightness))
                    .build();
  }
}
