package com.lazyprogrammer.draft2.ui.swing.graphics;

import com.lazyprogrammer.draft2.ui.swing.blueprint.Blueprint;
import com.lazyprogrammer.draft2.ui.swing.blueprint.BlueprintMap;
import com.lazyprogrammer.draft2.ui.swing.blueprint.Blueprints;
import com.lazyprogrammer.draft2.data.Coordinate;
import com.lazyprogrammer.draft2.data.terrain.TerrainRepository;
import com.lazyprogrammer.draft2.data.terrain.TerrainType;
import com.lazyprogrammer.draft2.ui.swing.map.MapView;
import lombok.RequiredArgsConstructor;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Set;

@RequiredArgsConstructor
public class TilePainter implements Painter {

  private final Drawer drawer;
  private final BlueprintMap<Point> blueprintMap;
  private final TerrainRepository terrainRepository;

  @Override
  public void paint(Graphics2D graphics2D, MapView view, Set<Coordinate> coordinates) {
    coordinates.forEach(coordinate -> {
      var terrain = terrainRepository.findTerrain(coordinate);
      final var blueprint = getBluePrint(view.getZoomLevel(), terrain.type());
      final var gridImage = drawer.drawHex(blueprint);
      final var onScreenLocation = view.calculateCenter(coordinate);
      graphics2D.drawImage(gridImage, onScreenLocation.x, onScreenLocation.y, null);
    });
  }

  private Blueprint getBluePrint(final int size, TerrainType terrainType) {
    final var key = new Point(size, terrainType.ordinal() - 1);
    blueprintMap.update(key, point -> Blueprints.terrain(point.x, point.y));
    return blueprintMap.get(key);
  }
}
