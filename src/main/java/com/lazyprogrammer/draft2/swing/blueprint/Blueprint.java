package com.lazyprogrammer.draft2.swing.blueprint;

import lombok.Builder;
import lombok.Value;

import java.awt.Color;
import java.awt.Shape;
import java.awt.Stroke;

@Builder
@Value
public class Blueprint {

  Shape shape;
  Color fillColor;
  Stroke stroke;
  Color strokeColor;
}
