package com.lazyprogrammer.draft2.swing;


import com.lazyprogrammer.draft2.configuration.GraphicsConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

@Component
@RequiredArgsConstructor
public class ApplicationFrame extends JFrame {

  private final JPanel mainPanel;
  private final GraphicsConfiguration graphicsConfiguration;

  @PostConstruct
  private void initialize() {
    // Use invokeLater to ensure Swing components are created on the EDT
    SwingUtilities.invokeLater(this::initializeComponents);
  }

  private void initializeComponents() {
    setTitle("Project Grand Rouge Strategy");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setMonitor();
    setDecoration();
    setContentPane(mainPanel);
    pack();
    setVisible(true);
  }

  private void setDecoration() {
    switch (graphicsConfiguration.getMode()) {
      case WINDOWED -> setUndecorated(false);
      case BORDERLESS -> setUndecorated(true);
    }
  }

  private void setMonitor() {
    // Get the default screen device
    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    GraphicsDevice[] devices = ge.getScreenDevices();
    final var monitorId = graphicsConfiguration.getMonitorId();
    if (monitorId < devices.length) {
      final var monitor = devices[monitorId];
      setBounds(monitor.getDefaultConfiguration()
                       .getBounds());
      setPreferredSize(monitor.getDefaultConfiguration()
                              .getBounds()
                              .getSize());
    }
  }


}