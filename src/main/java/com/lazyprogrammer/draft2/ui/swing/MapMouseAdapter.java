package com.lazyprogrammer.draft2.ui.swing;

import lombok.RequiredArgsConstructor;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@RequiredArgsConstructor
public class MapMouseAdapter extends MouseAdapter {
  private final HexMapComponent gameMap;
  private int lastX;
  private int lastY;

  @Override
  public void mousePressed(MouseEvent e) {
    lastX = e.getX();
    lastY = e.getY();
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    int dx = e.getX() - lastX;
    int dy = e.getY() - lastY;

    gameMap.pan(dx, dy);

    lastX = e.getX();
    lastY = e.getY();

  }

}
