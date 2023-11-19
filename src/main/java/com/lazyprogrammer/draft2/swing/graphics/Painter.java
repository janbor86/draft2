package com.lazyprogrammer.draft2.swing.graphics;

import com.lazyprogrammer.draft2.swing.data.GameMap;
import com.lazyprogrammer.draft2.swing.map.MapView;

import java.awt.Graphics2D;

public interface Painter {

  void paint(Graphics2D graphics2D, MapView view, GameMap gameMap);
}
