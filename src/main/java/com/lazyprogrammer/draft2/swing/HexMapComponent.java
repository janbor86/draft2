package com.lazyprogrammer.draft2.swing;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import net.lazyprogrammer.hex.swing.data.GameMap;
import net.lazyprogrammer.hex.swing.graphics.Overlay;
import net.lazyprogrammer.hex.swing.map.MapUI;
import net.lazyprogrammer.hex.swing.map.MapView;

import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;

@Slf4j
@ToString
@RequiredArgsConstructor
public class HexMapComponent extends JComponent {

  @Getter
  private final GameMap gameMap;
  private final MapView mapView;
  private final Overlay terrain;
  private final MapUI mapUI;

  @Override
  protected void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);
    log.trace("paint with {}", mapView);
    final var g2d = (Graphics2D) graphics;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
    terrain.draw(g2d, mapView, gameMap.getMapConfig());
    mapUI.draw(g2d, mapView, gameMap.getMapConfig());
  }


  public void pan(int dx, int dy) {
    mapView.translate(dx, dy);
    var minimumX = getWidth() - gameMap.getMapConfig()
                                       .width();
    var minimumY = getHeight() - gameMap.getMapConfig()
                                        .height();
    var x = Math.min(0, Math.max(minimumX, mapView.getLocation().x));
    var y = Math.min(gameMap.getMapConfig()
                            .verticalSpacing() / 2, Math.max(minimumY, mapView.getLocation().y));
    mapView.move(x, y);
    repaint();
  }

  public void refreshView() {
    mapView.resize(getWidth(), getHeight());
    repaint();
  }

  public void focusAt(Point at) {
    mapUI.highlight(findAt(at));
    repaint();
  }

  public Point findAt(Point at) {
    log.trace("search at {}", at);
    final var location = mapView.getLocation();
    log.trace("TL:{}", location);
    var dx = at.x - location.x;
    var dy = at.y - location.y;
    log.trace("dx:{},dy:{}", dx, dy);
    dx -= gameMap.getMapConfig()
                 .hexWidth() / 8;
    int x = dx / gameMap.getMapConfig()
                        .horizontalSpacing();
    if (x % 2 == 1) {
      dy += gameMap.getMapConfig()
                   .verticalSpacing() / 2;
    }
    int y = dy / gameMap.getMapConfig()
                        .verticalSpacing();
    x = Math.min(gameMap.getMapConfig()
                        .columnNo() - 1, Math.max(0, x));
    y = Math.min(gameMap.getMapConfig()
                        .rowNo() - 1, Math.max(0, y));
    log.trace("is it {}:{}", x, y);
    return new Point(x, y);
  }

}
