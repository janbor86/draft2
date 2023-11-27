package com.lazyprogrammer.draft2.ui.swing.graphics;

import com.lazyprogrammer.draft2.data.Coordinate;
import com.lazyprogrammer.draft2.ui.swing.map.MapView;
import java.util.Set;

public interface Painter {

  void paint(PaintingConfiguration configuration, MapView view, Set<Coordinate> coordinates);
}
