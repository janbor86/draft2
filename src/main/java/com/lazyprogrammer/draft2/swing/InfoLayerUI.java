package com.lazyprogrammer.draft2.swing;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.lazyprogrammer.hex.swing.data.TileAttribute;

import javax.swing.JComponent;
import javax.swing.JLayer;
import javax.swing.plaf.LayerUI;
import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

@Slf4j
@RequiredArgsConstructor
public class InfoLayerUI extends LayerUI<HexMapComponent> {

  private static final int TEXT_OFFSET = 24;

  String info = "";
  String coords = "";

  private final HexMapComponent mapComponent;


  @Override
  public void paint(Graphics g, JComponent component) {
    super.paint(g, component);
    final var layerWidth = component.getWidth() / 7;
    final var startX = component.getWidth() - layerWidth;
    final var layerHeight = 9 * component.getHeight() / 20;
    final var startY = component.getHeight() - layerHeight;
    g.setColor(new Color(0, 0, 0, 64));
    g.fillRect(startX, startY, layerWidth, layerHeight);

    Graphics2D g2d = (Graphics2D) g;
    g2d.setColor(Color.WHITE);
    g2d.drawString(info, startX + TEXT_OFFSET, startY + TEXT_OFFSET * 1);
    g2d.drawString(coords, startX + TEXT_OFFSET, startY + TEXT_OFFSET * 2);
  }

  public void installUI(JComponent c) {
    super.installUI(c);
    var l = (JLayer<?>) c;
    l.setLayerEventMask(AWTEvent.MOUSE_MOTION_EVENT_MASK);
  }


  @Override
  protected void processMouseMotionEvent(MouseEvent e, JLayer<? extends HexMapComponent> l) {
    log.trace("mouseMotion: {}", e);
    final var at = mapComponent.findAt(e.getPoint());
    coords = "[x=" + at.x + ",y=" + at.y + "]";
    info = switch (mapComponent.getGameMap()
                               .read(at, TileAttribute.TERRAIN)) {
      case 0 -> "OCEAN";
      case 1 -> "GRASSLAND";
      case 2 -> "FOREST";
      case 3 -> "MOUNTAIN";
      default -> "UNKNOWN";
    };
    mapComponent.repaint();
  }

}
