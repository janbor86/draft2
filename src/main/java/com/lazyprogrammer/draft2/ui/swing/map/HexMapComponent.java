package com.lazyprogrammer.draft2.ui.swing.map;

import com.lazyprogrammer.draft2.game.map.Coordinate;
import com.lazyprogrammer.draft2.ui.swing.graphics.Painter;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.util.List;
import java.util.Set;
import javax.swing.JComponent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ToString
@Getter
@RequiredArgsConstructor
public class HexMapComponent extends JComponent {

  @ToString.Exclude private final Set<Coordinate> validCoordinates;
  private final MapView mapView;
  private final List<Painter> painters;

  @Override
  protected void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);
    log.trace("paint with {}", mapView);
    final var g2d = (Graphics2D) graphics;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
    painters.forEach(painter -> painter.paint(g2d, mapView, validCoordinates));
  }

  public void pan(int dx, int dy) {
    mapView.translateView(dx, dy);
    repaint();
  }

  public void refreshView() {
    mapView.resize(getWidth(), getHeight());
    repaint();
  }

  public void zoomAt(int wheelRotation) {
    mapView.changeZoomLevel(-wheelRotation);
    repaint();
  }

  public void addMouseAdapter(MouseAdapter mouseAdapter) {
    addMouseListener(mouseAdapter);
    addMouseMotionListener(mouseAdapter);
    addMouseWheelListener(mouseAdapter);
  }

}
