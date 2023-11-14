package com.lazyprogrammer.draft2.swing.blueprint;

import lombok.Builder;

import java.awt.Color;
import java.awt.Shape;
import java.awt.Stroke;

@Builder
public record Blueprint(Shape shape, Color fillColor, Stroke stroke, Color strokeColor) {
}
