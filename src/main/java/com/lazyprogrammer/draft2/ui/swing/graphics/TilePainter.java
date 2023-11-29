package com.lazyprogrammer.draft2.ui.swing.graphics;

import com.lazyprogrammer.draft2.game.map.Coordinate;
import com.lazyprogrammer.draft2.game.map.terrain.TerrainRepository;
import com.lazyprogrammer.draft2.ui.swing.blueprint.Blueprints;
import com.lazyprogrammer.draft2.ui.swing.map.MapView;
import java.awt.Graphics2D;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
@RequiredArgsConstructor
public class TilePainter implements Painter {

  private final Drawer drawer;
  private final TerrainRepository terrainRepository;

  @Override
  public void paint(Graphics2D g2d, MapView view, Set<Coordinate> coordinates) {
    coordinates.forEach(
        coordinate -> {
          var terrain = terrainRepository.findTerrain(coordinate);
          final var blueprint =
              Blueprints.terrain(view.getZoomLevel(), terrain.type().ordinal() - 1);
          final var gridImage = drawer.draw(blueprint);
          final var onScreenLocation = view.calculateCenter(coordinate);
          g2d.drawImage(gridImage, onScreenLocation.x, onScreenLocation.y, null);
        });
  }

}
