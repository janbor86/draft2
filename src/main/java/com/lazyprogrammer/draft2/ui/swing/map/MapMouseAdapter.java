package com.lazyprogrammer.draft2.ui.swing.map;

import com.lazyprogrammer.draft2.data.Coordinate;
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
  private Coordinate focusedTile;

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
  public void mouseMoved(MouseEvent e) {
    focusedTile = gameMap.getMapView().findAt(e.getPoint());
  }

  @Override
  public void mouseWheelMoved(MouseWheelEvent e) {
    final var wheelRotation = e.getWheelRotation();
    if (focusedTile == null) focusedTile = gameMap.getMapView().findAt(e.getPoint());
    gameMap.zoomAt(focusedTile, wheelRotation);
  }
}
