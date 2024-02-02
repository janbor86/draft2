package com.lazyprogrammer.draft2.ui.swing;

import com.lazyprogrammer.draft2.configuration.GraphicsConfiguration;
import com.lazyprogrammer.draft2.configuration.WindowMode;
import com.lazyprogrammer.draft2.ui.swing.map.EndTurnButton;
import com.lazyprogrammer.draft2.ui.swing.menu.MenuComponent;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.annotation.PostConstruct;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApplicationFrame extends JFrame {

  @NonNull private final JPanel mainPanel;
  @NonNull private final GraphicsConfiguration graphicsConfiguration;
  private final ResourceLayerUI resourceLayerUI;
  private final EndTurnButton endTurnButton;
  private final MenuComponent menuComponent;

  @PostConstruct
  private void initialize() {
    // Use invokeLater to ensure Swing components are created on the EDT
    SwingUtilities.invokeLater(this::initializeComponents);
  }

  private void initializeComponents() {
    setTitle("Project Grand Rouge Strategy");
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setMonitor();
    setDecoration();
    final var mapPane = createMapPane();
    add(menuComponent);
    pack();
    setVisible(true);
  }

  private JLayeredPane createMapPane() {
    mainPanel.setBounds(0, 0, getWidth(), getHeight());
    resourceLayerUI.setBounds(0, 0, getWidth(), getHeight());
    endTurnButton.setBounds(getWidth() / 2 + 8, 4, 96, 32);
    final var layeredPane = new JLayeredPane();
    layeredPane.add(mainPanel, Integer.valueOf(1));
    layeredPane.add(resourceLayerUI, JLayeredPane.PALETTE_LAYER);
    layeredPane.add(endTurnButton, JLayeredPane.PALETTE_LAYER);
    return layeredPane;
  }

  private void setDecoration() {
    if (graphicsConfiguration.getMode() == WindowMode.BORDERLESS) {
      setUndecorated(true);
    }
  }

  private void setMonitor() {
    // Get the default screen device
    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    GraphicsDevice[] devices = ge.getScreenDevices();
    final var monitorId = graphicsConfiguration.getMonitorId();
    if (monitorId < devices.length) {
      final var monitor = devices[monitorId];
      setBounds(monitor.getDefaultConfiguration().getBounds());
      setPreferredSize(monitor.getDefaultConfiguration().getBounds().getSize());
    }
  }
}
