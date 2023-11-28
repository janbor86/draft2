package com.lazyprogrammer.draft2.ui.swing.map;

import com.lazyprogrammer.draft2.action.pop.PopCreatedEvent;
import com.lazyprogrammer.draft2.data.Coordinate;
import com.lazyprogrammer.draft2.ui.swing.graphics.Painter;
import com.lazyprogrammer.draft2.ui.swing.graphics.PaintingConfiguration;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.List;
import java.util.Set;
import javax.swing.JComponent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;

@Slf4j
@ToString
@Getter
@RequiredArgsConstructor
public class HexMapComponent extends JComponent {

  @ToString.Exclude private final Set<Coordinate> validCoordinates;
  private final MapView mapView;
  private final List<Painter> painters;
  private final PaintingConfiguration paintingConfiguration;
  private final ApplicationEventPublisher publisher;

  @Override
  protected void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);
    log.trace("paint with {}", mapView);
    final var g2d = (Graphics2D) graphics;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
    paintingConfiguration.setGraphics2D(g2d);
    painters.forEach(painter -> painter.paint(paintingConfiguration, mapView, validCoordinates));
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

  public void addMouseAdapter(MapMouseAdapter mapMouseAdapter) {
    addMouseListener(mapMouseAdapter);
    addMouseMotionListener(mapMouseAdapter);
    addMouseWheelListener(mapMouseAdapter);
  }

  public void createPopAt(Coordinate clickedOn) {
    publisher.publishEvent(new PopCreatedEvent(this, clickedOn));
  }
}
