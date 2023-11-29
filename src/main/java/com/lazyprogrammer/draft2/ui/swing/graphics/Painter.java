package com.lazyprogrammer.draft2.ui.swing.graphics;

import com.lazyprogrammer.draft2.game.map.Coordinate;
import com.lazyprogrammer.draft2.ui.swing.map.MapView;

import java.awt.Graphics2D;
import java.util.Set;

public interface Painter {

  void paint(Graphics2D graphics2D, MapView view, Set<Coordinate> coordinates);
}
