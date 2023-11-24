package com.lazyprogrammer.draft2.swing.graphics;

import com.lazyprogrammer.draft2.swing.blueprint.Blueprint;
import com.lazyprogrammer.draft2.swing.blueprint.BlueprintMap;
import com.lazyprogrammer.draft2.swing.blueprint.Blueprints;
import com.lazyprogrammer.draft2.swing.data.Coordinate;
import com.lazyprogrammer.draft2.swing.data.terrain.TerrainRepository;
import com.lazyprogrammer.draft2.swing.data.terrain.TerrainType;
import com.lazyprogrammer.draft2.swing.map.MapView;
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
