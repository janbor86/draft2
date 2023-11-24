package com.lazyprogrammer.draft2.swing;

import lombok.extern.slf4j.Slf4j;

import javax.swing.JLayer;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

@Slf4j
public class MainPanel extends JPanel {

  public MainPanel(HexMapComponent mapComponent, JLayer<HexMapComponent> infoLayer) {
    setDoubleBuffered(true);
    setBackground(Color.BLACK);
    setLayout(new BorderLayout());
    addComponentListener(createComponentAdapter(mapComponent));
    add(infoLayer, BorderLayout.CENTER);
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
