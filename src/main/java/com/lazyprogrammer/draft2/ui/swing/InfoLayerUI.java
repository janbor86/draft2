package com.lazyprogrammer.draft2.ui.swing;

import com.lazyprogrammer.draft2.data.Coordinate;
import com.lazyprogrammer.draft2.data.terrain.TerrainRepository;
import com.lazyprogrammer.draft2.ui.swing.map.HexMapComponent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.swing.JComponent;
import javax.swing.JLayer;
import javax.swing.plaf.LayerUI;
import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

@Slf4j
@RequiredArgsConstructor
public class InfoLayerUI extends LayerUI<HexMapComponent> {

  private static final int TEXT_OFFSET = 24;
  private static final Color INFO_AREA_COLOR = new Color(0, 0, 0, 64);

  String info = "";
  Coordinate coordinate;

  private final HexMapComponent mapComponent;
  private final BufferedImage highlightShape;
  private final TerrainRepository terrainRepository;

  @Override
  public void paint(Graphics g, JComponent component) {
    super.paint(g, component);
    final var infoArea = createInfoArea(component);
    Graphics2D g2d = (Graphics2D) g;
    paintInfoArea(g2d, infoArea);
    if (coordinate != null) {
      paintHighlightShape(g2d);
      paintInfoText(g2d, infoArea, 1, "[x=" + coordinate.x() + ",y=" + coordinate.y() + "]");
    }
    paintInfoText(g2d, infoArea, 2, info);
  }

  private void paintHighlightShape(Graphics2D g2d) {
    final var center = mapComponent.getMapView()
                                   .calculateCenter(coordinate);
    g2d.drawImage(highlightShape, center.x, center.y, null);
  }

  private void paintInfoText(Graphics2D g2d, Rectangle infoArea, int lineNo, String info) {
    g2d.setColor(Color.WHITE);
    g2d.drawString(info, infoArea.x + TEXT_OFFSET, infoArea.y + TEXT_OFFSET * lineNo);
  }

  private static void paintInfoArea(Graphics2D g2d, Rectangle infoArea) {
    g2d.setColor(INFO_AREA_COLOR);
    g2d.fill(infoArea);
  }

  private static Rectangle createInfoArea(JComponent component) {
    final var layerWidth = component.getWidth() / 7;
    final var startX = component.getWidth() - layerWidth;
    final var layerHeight = 9 * component.getHeight() / 20;
    final var startY = component.getHeight() - layerHeight;
    return new Rectangle(startX, startY, layerWidth, layerHeight);
  }

  @Override
  public void installUI(JComponent c) {
    super.installUI(c);
    var l = (JLayer<?>) c;
    l.setLayerEventMask(AWTEvent.MOUSE_MOTION_EVENT_MASK | AWTEvent.KEY_EVENT_MASK);
  }


  @Override
  protected void processMouseMotionEvent(MouseEvent e, JLayer<? extends HexMapComponent> l) {
    log.trace("mouseMotion: {}", e);
    coordinate = mapComponent.getMapView()
                             .findAt(e.getPoint());
    info = terrainRepository.findTerrain(coordinate)
                            .name();
    mapComponent.repaint();
  }

  @Override
  protected void processKeyEvent(KeyEvent e, JLayer<? extends HexMapComponent> l) {
    final var mapView = mapComponent.getMapView();
    coordinate = mapView.getFocused();
    if (coordinate != null)
      info = terrainRepository.findTerrain(coordinate)
                              .name();
    mapComponent.repaint();
  }
}
