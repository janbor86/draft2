package com.lazyprogrammer.draft2.ui.swing.graphics;

import java.awt.Graphics2D;
import java.util.Properties;
import lombok.Data;

@Data
public final class PaintingConfiguration {
  private Graphics2D graphics2D;
  private Properties properties;

  public PaintingConfiguration() {
    properties = new Properties();
  }
}
