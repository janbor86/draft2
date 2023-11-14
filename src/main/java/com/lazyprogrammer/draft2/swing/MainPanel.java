package com.lazyprogrammer.draft2.swing;

import lombok.extern.slf4j.Slf4j;
import net.lazyprogrammer.hex.swing.data.GameMap;
import net.lazyprogrammer.hex.swing.data.TileAttribute;
import net.lazyprogrammer.hex.swing.graphics.Overlay;
import net.lazyprogrammer.hex.swing.graphics.OverlayFactory;
import net.lazyprogrammer.hex.swing.map.HexMapConfig;
import net.lazyprogrammer.hex.swing.map.MapView;

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
    add(new JLayer<>(mapComponent, new InfoLayerUI(mapComponent)), BorderLayout.CENTER);
  }

  private HexMapComponent createHexMap() {
    final var mapConfig = new HexMapConfig(60, 30, Hex.sizeOf(56));
    final var gameMap = new GameMap(mapConfig);
    final var terrain = OverlayFactory.terrainOverlay(mapConfig);
    copyTo(terrain, gameMap);
    final var mapUi = OverlayFactory.mapUi(mapConfig);
    return new HexMapComponent(gameMap, new MapView(getVisibleRect()), terrain, mapUi);
  }

  private static GameMap copyTo(Overlay overlay, GameMap gameMap) {
    for (int i = 0; i < overlay.getMap()
                               .getColumnNo(); i++) {
      for (int j = 0; j < overlay.getMap()
                                 .getRowNo(); j++) {
        gameMap.write(new Point(i, j), TileAttribute.TERRAIN, overlay.getMap()
                                                                     .get(i, j));
      }
    }
    return gameMap;
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
