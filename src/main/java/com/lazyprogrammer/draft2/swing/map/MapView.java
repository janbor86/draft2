package com.lazyprogrammer.draft2.swing.map;

import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.awt.Point;
import java.awt.Rectangle;

@Slf4j
@ToString
@Setter
public class MapView {

  private final Rectangle viewBoundary;

  public MapView() {
    this.viewBoundary = new Rectangle();
  }

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

  public Point correctViewLocation(final HexMapConfig mapConfig) {
    var minimumX = viewBoundary.width - mapConfig.width();
    var minimumY = viewBoundary.height - mapConfig.height();
    var x = Math.min(0, Math.max(minimumX, getLocation().x));
    var y = Math.min(mapConfig.verticalSpacing() / 2, Math.max(minimumY, getLocation().y));
    return new Point(x, y);
  }

  public void translateView(int dx, int dy, final HexMapConfig mapConfig) {
    translate(dx, dy);
    viewBoundary.setLocation(correctViewLocation(mapConfig));
  }
}
