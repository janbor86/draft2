package com.lazyprogrammer.draft2.swing.graphics;

import com.lazyprogrammer.draft2.swing.blueprint.Blueprint;
import com.lazyprogrammer.draft2.swing.blueprint.Blueprints;
import com.lazyprogrammer.draft2.swing.data.GameMap;
import com.lazyprogrammer.draft2.swing.map.HexMapConfig;
import com.lazyprogrammer.draft2.swing.map.MapView;

import java.awt.Graphics2D;

public class GridPainter implements Painter {
  private final Drawer drawer;
  private Blueprint grid;

  public GridPainter(final Drawer drawer) {
    this.drawer = drawer;
  }

  @Override
  public void paint(Graphics2D graphics2D, MapView view, GameMap gameMap, HexMapConfig mapConfig) {
    gameMap.getCoordinates()
           .forEach(coordinate -> {
             checkBlueprint(mapConfig);
             final var onScreenLocation = mapConfig.translateCoordinateToScreenLocation(coordinate, view.getLocation());
             final var gridImage = drawer.drawHex(grid);
             graphics2D.drawImage(gridImage, onScreenLocation.x, onScreenLocation.y, null);
           });
  }

  private void checkBlueprint(HexMapConfig mapConfig) {
    if (grid == null)
      grid = Blueprints.grid(mapConfig.hexSize());
  }

}
