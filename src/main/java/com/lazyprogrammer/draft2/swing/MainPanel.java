package com.lazyprogrammer.draft2.swing;

import com.lazyprogrammer.draft2.swing.blueprint.Blueprints;
import com.lazyprogrammer.draft2.swing.graphics.Drawer;
import lombok.extern.slf4j.Slf4j;

import javax.swing.JLayer;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

@Slf4j
public class MainPanel extends JPanel {

  public MainPanel(HexMapComponent mapComponent) {
    setDoubleBuffered(true);
    setBackground(Color.BLACK);
    setLayout(new BorderLayout());
    final var mapMouseAdapter = new MapMouseAdapter(mapComponent);
    mapComponent.addMouseListener(mapMouseAdapter);
    mapComponent.addMouseMotionListener(mapMouseAdapter);
    addComponentListener(createComponentAdapter(mapComponent));
    final var highlightImage = Drawer.GLOBAL.drawHex(Blueprints.highlight(Hex.sizeOf(mapComponent.getMapView()
                                                                                                 .getZoomLevel())));
    add(new JLayer<>(mapComponent, new InfoLayerUI(mapComponent, highlightImage)), BorderLayout.CENTER);
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
