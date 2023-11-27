package com.lazyprogrammer.draft2.ui.swing.map;

import com.lazyprogrammer.draft2.ui.swing.graphics.GridPainter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class MapPaintKeyAdapter extends KeyAdapter {

  private final HexMapComponent mapComponent;

  @Override
  public void keyPressed(KeyEvent e) {
    handleGridStatus(e);
    handleGridStrokeWidth(e);
    mapComponent.repaint();
  }

  private void handleGridStrokeWidth(KeyEvent e) {
    final var properties = mapComponent.getPaintingConfiguration().getProperties();
    final var key = "grid_stroke_width";
    final int gridStrokeWidth = Integer.parseInt(properties.getProperty(key, "0"));
    if (KeyEvent.VK_PAGE_UP == e.getKeyCode()) {
      final var newValue = String.valueOf(gridStrokeWidth + 1);
      log.debug("{} increased to {}", key, newValue);
      properties.setProperty(key, newValue);
    }
    if (KeyEvent.VK_PAGE_DOWN == e.getKeyCode()) {
      final var newValue = String.valueOf(gridStrokeWidth - 1);
      log.debug("{} decreased to {}", key, newValue);
      properties.setProperty(key, newValue);
    }
  }

  private void handleGridStatus(KeyEvent e) {
    if (KeyEvent.VK_G == e.getKeyCode()) {
      final var properties = mapComponent.getPaintingConfiguration().getProperties();
      final var gridStatus =
          properties.getProperty(GridPainter.GRID_KEY, GridPainter.DEFAULT_STATUS);
      if (!GridPainter.ACTIVE_STATUS.equals(gridStatus))
        properties.setProperty(GridPainter.GRID_KEY, GridPainter.ACTIVE_STATUS);
      else properties.setProperty(GridPainter.GRID_KEY, GridPainter.INACTIVE_STATUS);
    }
  }
}
