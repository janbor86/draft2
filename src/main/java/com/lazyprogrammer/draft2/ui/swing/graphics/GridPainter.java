package com.lazyprogrammer.draft2.ui.swing.graphics;

import com.lazyprogrammer.draft2.ui.swing.blueprint.Blueprint;
import com.lazyprogrammer.draft2.ui.swing.blueprint.Blueprints;
import com.lazyprogrammer.draft2.data.Coordinate;
import com.lazyprogrammer.draft2.ui.swing.map.MapView;

import java.awt.Graphics2D;
import java.util.Set;

public class GridPainter implements Painter {
  private final Drawer drawer;
  private Blueprint grid;

  public GridPainter(final Drawer drawer) {
    this.drawer = drawer;
  }

  @Override
  public void paint(Graphics2D graphics2D, MapView view, Set<Coordinate> coordinates) {
    grid = Blueprints.grid(view.getZoomLevel());
    coordinates.forEach(
        coordinate -> {
          final var onScreenLocation = view.calculateCenter(coordinate);
          final var gridImage = drawer.drawHex(grid);
          graphics2D.drawImage(gridImage, onScreenLocation.x, onScreenLocation.y, null);
        });
  }
}
