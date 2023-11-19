package com.lazyprogrammer.draft2.swing;

import com.lazyprogrammer.draft2.swing.blueprint.Blueprints;
import com.lazyprogrammer.draft2.swing.data.GameMap;
import com.lazyprogrammer.draft2.swing.data.TileAttribute;
import com.lazyprogrammer.draft2.swing.graphics.Drawer;
import com.lazyprogrammer.draft2.swing.graphics.GridPainter;
import com.lazyprogrammer.draft2.swing.map.MapConfig;
import com.lazyprogrammer.draft2.swing.map.MapMatrix;
import com.lazyprogrammer.draft2.swing.map.MapView;
import com.lazyprogrammer.draft2.swing.map.Maps;
import lombok.extern.slf4j.Slf4j;

import javax.swing.JLayer;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

@Slf4j
public class MainPanel extends JPanel {

  public MainPanel() {
    setDoubleBuffered(true);
    setBackground(Color.BLACK);
    setLayout(new BorderLayout());
    final var baseHex = Hex.sizeOf(56);
    final HexMapComponent mapComponent = createHexMap(baseHex);
    final var mapMouseAdapter = new MapMouseAdapter(mapComponent);
    mapComponent.addMouseListener(mapMouseAdapter);
    mapComponent.addMouseMotionListener(mapMouseAdapter);
    addComponentListener(createComponentAdapter(mapComponent));
    final var highlightImage = Drawer.GLOBAL.drawHex(Blueprints.highlight(baseHex));
    add(new JLayer<>(mapComponent, new InfoLayerUI(mapComponent, highlightImage)), BorderLayout.CENTER);
  }

  private HexMapComponent createHexMap(final Hex hex) {
    final var mapConfig = new MapConfig(60, 30, hex);
    final var gameMap = new GameMap(mapConfig);
    copyTo(Maps.emptyOne(mapConfig), gameMap);
    return new HexMapComponent(gameMap, new MapView(mapConfig), List.of(new GridPainter(Drawer.GLOBAL)));
  }

  private static void copyTo(MapMatrix mapMatrix, GameMap gameMap) {
    for (int i = 0; i < mapMatrix.getColumnNo(); i++) {
      for (int j = 0; j < mapMatrix.getRowNo(); j++) {
        gameMap.write(new Point(i, j), TileAttribute.TERRAIN, mapMatrix.get(i, j));
      }
    }
  }

  private static ComponentAdapter createComponentAdapter(HexMapComponent mapComponent) {
    return new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent e) {
        mapComponent.refreshView();
      }

    };
  }
}
