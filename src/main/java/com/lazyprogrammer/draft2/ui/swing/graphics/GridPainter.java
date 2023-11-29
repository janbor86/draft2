package com.lazyprogrammer.draft2.ui.swing.graphics;

import com.lazyprogrammer.draft2.game.map.Coordinate;
import com.lazyprogrammer.draft2.ui.swing.blueprint.Blueprint;
import com.lazyprogrammer.draft2.ui.swing.blueprint.Blueprints;
import com.lazyprogrammer.draft2.ui.swing.map.MapView;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.util.Set;

@Order(10)
@Component
@RequiredArgsConstructor
public class GridPainter implements Painter {
  public static final String GRID_KEY = "grid_key";
  public static final String ACTIVE_STATUS = "active";
  public static final String INACTIVE_STATUS = "inactive";
  public static final String DEFAULT_STATUS = ACTIVE_STATUS;
  private final Drawer drawer;
  private final PaintingConfiguration configuration;
  private Blueprint grid;

  @Override
  public void paint(Graphics2D g2d, MapView view, Set<Coordinate> coordinates) {
    if (!ACTIVE_STATUS.equals(configuration.getProperties().getProperty(GRID_KEY, DEFAULT_STATUS)))
      return;
    final var zoomLevel = view.getZoomLevel();
    if (zoomLevel < 20) return;
    final int gridStrokeWidth =
        Integer.parseInt(configuration.getProperties().getProperty("grid_stroke_width", "-10"));
    grid = Blueprints.grid(zoomLevel, gridStrokeWidth);
    coordinates.forEach(
        coordinate -> {
          final var onScreenLocation = view.calculateCenter(coordinate);
          final var gridImage = drawer.draw(grid);
          //          drawFrame(configuration.getGraphics2D(), onScreenLocation, gridImage);
          g2d.drawImage(gridImage, onScreenLocation.x, onScreenLocation.y, null);
        });
  }

  private void drawFrame(Graphics2D graphics2D, Point onScreenLocation, Image image) {
    graphics2D.setColor(Color.BLUE);
    graphics2D.drawRect(
        onScreenLocation.x, onScreenLocation.y, image.getWidth(null), image.getHeight(null));
  }
}
