package com.lazyprogrammer.draft2.swing.graphics;

import com.lazyprogrammer.draft2.swing.blueprint.Blueprint;
import com.lazyprogrammer.draft2.swing.blueprint.Blueprints;
import com.lazyprogrammer.draft2.swing.data.GameMap;
import com.lazyprogrammer.draft2.swing.map.MapView;

import java.awt.Graphics2D;
import java.awt.Point;

public class GridPainter implements Painter {
  private final Drawer drawer;
  private Blueprint grid;

  public GridPainter(final Drawer drawer) {
    this.drawer = drawer;
  }

  @Override
  public void paint(Graphics2D graphics2D, MapView view, GameMap gameMap) {
    final var mapConfig = view.getMapConfig();
    gameMap.getCoordinates()
           .forEach(coordinate -> {
             checkBlueprint(mapConfig.hexSize());
             Point offset = view.getLocation();
             var x = coordinate.x * mapConfig.horizontalSpacing();
             var verticalOffset = (int) (((coordinate.x % 2) * mapConfig.hexHeight()) / 2D);
             var y = coordinate.y * mapConfig.verticalSpacing() - verticalOffset;
             final var onScreenLocation = new Point(x + offset.x, y + offset.y);
             final var gridImage = drawer.drawHex(grid);
             graphics2D.drawImage(gridImage, onScreenLocation.x, onScreenLocation.y, null);
           });
  }

  private void checkBlueprint(final int size) {
    if (grid == null)
      grid = Blueprints.grid(size);
  }

}
