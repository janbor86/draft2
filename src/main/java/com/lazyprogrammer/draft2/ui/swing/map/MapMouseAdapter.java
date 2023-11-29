package com.lazyprogrammer.draft2.ui.swing.map;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MapMouseAdapter extends MouseAdapter {
  private final MapView mapView;
  private Point mousePosition;

  @Override
  public void mousePressed(MouseEvent e) {
    mousePosition = e.getPoint();
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    int dx = e.getX() - mousePosition.x;
    int dy = e.getY() - mousePosition.y;

    mapView.translateView(dx, dy);

    mousePosition = e.getPoint();
    e.getComponent().repaint();
  }

  @Override
  public void mouseWheelMoved(MouseWheelEvent e) {
    mapView.changeZoomLevel(-e.getWheelRotation());
    e.getComponent().repaint();
  }
}
