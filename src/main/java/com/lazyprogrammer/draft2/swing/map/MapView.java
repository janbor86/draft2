package com.lazyprogrammer.draft2.swing.map;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.awt.Point;
import java.awt.Rectangle;

@Slf4j
@ToString
@RequiredArgsConstructor
public class MapView {

  private final Rectangle viewBoundary;

  public void translate(int dx, int dy) {
    viewBoundary.translate(dx, dy);
  }

  public Point getLocation() {
    return viewBoundary.getLocation();
  }

  public void resize(int width, int height) {
    log.info("resize to {}x{}", width, height);
    viewBoundary.setSize(width, height);
  }

  public void move(int x, int y) {
    viewBoundary.setLocation(x, y);
  }
}
