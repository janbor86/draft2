package com.lazyprogrammer.draft2.swing;

import com.lazyprogrammer.draft2.swing.blueprint.Blueprints;
import com.lazyprogrammer.draft2.swing.data.GameMap;
import com.lazyprogrammer.draft2.swing.data.TileAttribute;
import com.lazyprogrammer.draft2.swing.graphics.Drawer;
import com.lazyprogrammer.draft2.swing.graphics.Overlay;
import com.lazyprogrammer.draft2.swing.graphics.OverlayFactory;
import com.lazyprogrammer.draft2.swing.map.HexMapConfig;
import com.lazyprogrammer.draft2.swing.map.MapView;
import lombok.extern.slf4j.Slf4j;

import javax.swing.JLayer;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

@Slf4j
public class MainPanel extends JPanel {

  public MainPanel() {
    setDoubleBuffered(true);
    setBackground(Color.BLACK);
    setLayout(new BorderLayout());
    final HexMapComponent mapComponent = createHexMap();
    final var mapMouseAdapter = new MapMouseAdapter(mapComponent);
    mapComponent.addMouseListener(mapMouseAdapter);
    mapComponent.addMouseMotionListener(mapMouseAdapter);
    addComponentListener(createComponentAdapter(mapComponent));
    final var highlightImage = new Drawer().drawHex(Blueprints.highlight(mapComponent.getGameMap()
                                                                                     .getMapConfig()
                                                                                     .hex()));
    add(new JLayer<>(mapComponent, new InfoLayerUI(mapComponent, highlightImage)), BorderLayout.CENTER);
  }

  private HexMapComponent createHexMap() {
    final var mapConfig = new HexMapConfig(60, 30, Hex.sizeOf(56));
    final var gameMap = new GameMap(mapConfig);
    final var terrain = OverlayFactory.terrainOverlay(mapConfig);
    copyTo(terrain, gameMap);
    return new HexMapComponent(gameMap, new MapView(), terrain);
  }

  private static void copyTo(Overlay overlay, GameMap gameMap) {
    for (int i = 0; i < overlay.getMap()
                               .getColumnNo(); i++) {
      for (int j = 0; j < overlay.getMap()
                                 .getRowNo(); j++) {
        gameMap.write(new Point(i, j), TileAttribute.TERRAIN, overlay.getMap()
                                                                     .get(i, j));
      }
    }
  }

  private static ComponentAdapter createComponentAdapter(HexMapComponent mapComponent) {
    return new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent e) {
        mapComponent.refreshView();
      }

      @Override
      public void componentMoved(ComponentEvent e) {
        super.componentMoved(e);
      }

      @Override
      public void componentShown(ComponentEvent e) {
        super.componentShown(e);
      }

      @Override
      public void componentHidden(ComponentEvent e) {
        super.componentHidden(e);
      }
    };
  }
}
