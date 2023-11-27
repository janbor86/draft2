package com.lazyprogrammer.draft2.ui.swing.graphics;

import com.lazyprogrammer.draft2.data.Coordinate;
import com.lazyprogrammer.draft2.ui.swing.blueprint.Blueprint;
import com.lazyprogrammer.draft2.ui.swing.blueprint.Blueprints;
import com.lazyprogrammer.draft2.ui.swing.map.MapView;
import java.util.Set;

public class GridPainter implements Painter {
  public static final String GRID_KEY = "grid_key";
  public static final String ACTIVE_STATUS = "active";
  public static final String INACTIVE_STATUS = "inactive";
  public static final String DEFAULT_STATUS = ACTIVE_STATUS;
  private final Drawer drawer;
  private Blueprint grid;

  public GridPainter(final Drawer drawer) {
    this.drawer = drawer;
  }

  @Override
  public void paint(
      PaintingConfiguration configuration, MapView view, Set<Coordinate> coordinates) {
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
          final var gridImage = drawer.drawHex(grid);
          configuration
              .getGraphics2D()
              .drawImage(gridImage, onScreenLocation.x, onScreenLocation.y, null);
        });
  }
}
