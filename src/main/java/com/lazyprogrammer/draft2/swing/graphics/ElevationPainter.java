package com.lazyprogrammer.draft2.swing.graphics;

import com.lazyprogrammer.draft2.swing.blueprint.Blueprint;
import com.lazyprogrammer.draft2.swing.blueprint.BlueprintMap;
import com.lazyprogrammer.draft2.swing.blueprint.Blueprints;
import com.lazyprogrammer.draft2.swing.data.GameMap;
import com.lazyprogrammer.draft2.swing.data.TileAttribute;
import com.lazyprogrammer.draft2.swing.map.MapView;
import lombok.RequiredArgsConstructor;

import java.awt.Graphics2D;
import java.awt.Point;

@RequiredArgsConstructor
public class ElevationPainter implements Painter {

  private final Drawer drawer;
  private final BlueprintMap<Point> blueprintMap;

  @Override
  public void paint(Graphics2D graphics2D, MapView view, GameMap gameMap) {
    gameMap.getCoordinates()
           .forEach(coordinate -> {
             final var onScreenLocation = view.calculateCenter(coordinate);
             final var blueprint = getBluePrint(view.getZoomLevel(), gameMap.read(coordinate, TileAttribute.ELEVATION));
             final var gridImage = drawer.drawHex(blueprint);
             graphics2D.drawImage(gridImage, onScreenLocation.x, onScreenLocation.y, null);
           });
  }

  private Blueprint getBluePrint(final int size, int elevation) {
    final var brightness = elevation / 100;
    final var key = new Point(size, brightness);
    blueprintMap.update(key, point -> Blueprints.elevation(point.x, point.y));
    return blueprintMap.get(key);
  }

}
