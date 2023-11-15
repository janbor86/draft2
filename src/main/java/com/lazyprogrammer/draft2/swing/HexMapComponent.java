package com.lazyprogrammer.draft2.swing;


import com.lazyprogrammer.draft2.swing.data.GameMap;
import com.lazyprogrammer.draft2.swing.graphics.Painter;
import com.lazyprogrammer.draft2.swing.map.HexMapConfig;
import com.lazyprogrammer.draft2.swing.map.MapView;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.List;

@Slf4j
@ToString
@Getter
@RequiredArgsConstructor
public class HexMapComponent extends JComponent {

  private final HexMapConfig mapConfig;
  private final GameMap gameMap;
  private final MapView mapView;
  private final List<Painter> painters;

  @Override
  protected void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);
    log.trace("paint with {}", mapView);
    final var g2d = (Graphics2D) graphics;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
    painters.forEach(painter -> painter.paint(g2d, mapView, gameMap, mapConfig));
  }


  public void pan(int dx, int dy) {
    mapView.translateView(dx, dy, mapConfig);
    repaint();
  }

  public void refreshView() {
    mapView.resize(getWidth(), getHeight());
    repaint();
  }

  public Point findAt(Point at) {
    log.trace("search at {}", at);
    final var location = mapView.getLocation();
    log.trace("TL:{}", location);
    var dx = at.x - location.x;
    var dy = at.y - location.y;
    log.trace("dx:{},dy:{}", dx, dy);
    dx -= mapConfig.hexWidth() / 8;
    int x = dx / mapConfig.horizontalSpacing();
    if (x % 2 == 1) {
      dy += mapConfig.verticalSpacing() / 2;
    }
    int y = dy / mapConfig.verticalSpacing();
    x = Math.min(mapConfig.columnNo() - 1, Math.max(0, x));
    y = Math.min(mapConfig.rowNo() - 1, Math.max(0, y));
    log.trace("is it {}:{}", x, y);
    return new Point(x, y);
  }

  Point calculateCenter(Point coordinate) {
    return mapConfig.translateCoordinateToScreenLocation(coordinate, mapView.getLocation());
  }
}


