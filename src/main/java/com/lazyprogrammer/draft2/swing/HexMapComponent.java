package com.lazyprogrammer.draft2.swing;


import com.lazyprogrammer.draft2.swing.data.GameMap;
import com.lazyprogrammer.draft2.swing.graphics.Painter;
import com.lazyprogrammer.draft2.swing.map.MapView;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.List;

@Slf4j
@ToString
@Getter
@RequiredArgsConstructor
public class HexMapComponent extends JComponent {

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
    painters.forEach(painter -> painter.paint(g2d, mapView, gameMap.getCoordinates()));
  }


  public void pan(int dx, int dy) {
    mapView.translateView(dx, dy);
    repaint();
  }

  public void refreshView() {
    mapView.resize(getWidth(), getHeight());
    repaint();
  }

}


