package com.lazyprogrammer.draft2.ui.swing.blueprint;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Circle implements Shape {

  private final Ellipse2D.Double circle;

  public Circle(int diameter) {
    circle = new Ellipse2D.Double(0, 0, diameter, diameter);
  }

  public static Shape radius(int r) {
    return new Circle(2 * r);
  }

  @Override
  public Rectangle getBounds() {
    return circle.getBounds();
  }

  @Override
  public Rectangle2D getBounds2D() {
    return circle.getBounds2D();
  }

  @Override
  public boolean contains(double x, double y) {
    return circle.contains(x, y);
  }

  @Override
  public boolean contains(Point2D p) {
    return circle.contains(p);
  }

  @Override
  public boolean intersects(double x, double y, double w, double h) {
    return circle.intersects(x, y, w, h);
  }

  @Override
  public boolean intersects(Rectangle2D r) {
    return circle.intersects(r);
  }

  @Override
  public boolean contains(double x, double y, double w, double h) {
    return circle.contains(x, y, w, h);
  }

  @Override
  public boolean contains(Rectangle2D r) {
    return circle.contains(r);
  }

  @Override
  public PathIterator getPathIterator(AffineTransform at) {
    return circle.getPathIterator(at);
  }

  @Override
  public PathIterator getPathIterator(AffineTransform at, double flatness) {
    return circle.getPathIterator(at, flatness);
  }
}
