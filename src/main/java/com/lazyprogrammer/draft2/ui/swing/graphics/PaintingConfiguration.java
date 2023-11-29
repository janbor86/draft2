package com.lazyprogrammer.draft2.ui.swing.graphics;

import java.util.Properties;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public final class PaintingConfiguration {

  private Properties properties;

  public PaintingConfiguration() {
    properties = new Properties();
  }
}
