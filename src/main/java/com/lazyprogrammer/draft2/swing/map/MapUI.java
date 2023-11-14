package com.lazyprogrammer.draft2.swing.map;

import net.lazyprogrammer.hex.swing.blueprint.BlueprintMap;
import net.lazyprogrammer.hex.swing.graphics.Drawer;
import net.lazyprogrammer.hex.swing.graphics.Overlay;

import java.awt.Point;

public class MapUI extends Overlay {

  private Point lastHighLight = new Point();

  public MapUI(BlueprintMap<Integer> blueprints, Drawer drawer, MapMatrix map) {
    super(blueprints, drawer, map);
  }

  public void highlight(Point coordinates) {
    map.set(lastHighLight.x, lastHighLight.y, 0);
    map.set(coordinates.x, coordinates.y, 1);
    lastHighLight = coordinates;
  }

  public Point getHighlighted() {
    return lastHighLight;
  }
}
