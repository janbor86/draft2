package com.lazyprogrammer.draft2.ui.swing.map;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class MapMouseAdapter extends MouseAdapter {
  private final HexMapComponent gameMap;
  private Point mousePosition;

  @Override
  public void mousePressed(MouseEvent e) {
    mousePosition = e.getPoint();
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    int dx = e.getX() - mousePosition.x;
    int dy = e.getY() - mousePosition.y;

    gameMap.pan(dx, dy);

    mousePosition = e.getPoint();
  }

  @Override
  public void mouseWheelMoved(MouseWheelEvent e) {
    gameMap.zoomAt(e.getWheelRotation());
  }
}
