package com.lazyprogrammer.draft2.swing.graphics;


import com.lazyprogrammer.draft2.swing.blueprint.BlueprintMap;
import com.lazyprogrammer.draft2.swing.map.HexMapConfig;
import com.lazyprogrammer.draft2.swing.map.MapMatrix;
import com.lazyprogrammer.draft2.swing.map.MapView;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.awt.Graphics2D;
import java.awt.Point;

@Slf4j
@RequiredArgsConstructor
public class Overlay {

  private final BlueprintMap<Integer> blueprints;
  private final Drawer drawer;
  @Getter
  protected final MapMatrix map;

  public void draw(Graphics2D g2d, MapView mapView, HexMapConfig hexMapConfig) {
    for (int i = 0; i < map.getColumnNo(); i++) {
      for (int j = 0; j < map.getRowNo(); j++) {
        final var center = calculateCenter(i, j, mapView.getLocation(), hexMapConfig);
        g2d.drawImage(drawer.drawHex(blueprints.get(map.get(i, j))), center.x, center.y, null);
      }
    }
  }

  private Point calculateCenter(int i, int j, Point offset, HexMapConfig hexMapConfig) {
    var x = i * hexMapConfig.horizontalSpacing();
    var verticalOffset = (int) (((i % 2) * hexMapConfig.hexHeight()) / 2D);
    var y = j * hexMapConfig.verticalSpacing() - verticalOffset;
    return new Point(x + offset.x, y + offset.y);
  }

}

