package com.lazyprogrammer.draft2.swing.blueprint;

import lombok.Builder;
import lombok.NonNull;

import java.awt.Color;
import java.awt.Shape;
import java.awt.Stroke;

@Builder
public record Blueprint(@NonNull Shape shape, Color fillColor, Stroke stroke, Color strokeColor) {
  public boolean hasColor() {
    return fillColor != null;
  }

  public boolean hasContour() {
    return stroke != null && strokeColor != null;
  }
}
