package com.lazyprogrammer.draft2.ui.swing;

import com.lazyprogrammer.draft2.ui.swing.map.MapComponent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JLayer;
import javax.swing.JPanel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MainPanel extends JPanel {

  public MainPanel(MapComponent mapComponent, JLayer<MapComponent> infoLayer) {
    setDoubleBuffered(true);
    setBackground(Color.BLACK);
    setLayout(new BorderLayout());
    addComponentListener(createComponentAdapter(mapComponent));
    add(infoLayer, BorderLayout.CENTER);
  }

  private static ComponentAdapter createComponentAdapter(MapComponent mapComponent) {
    return new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent e) {
        mapComponent.refreshView();
      }
    };
  }
}
