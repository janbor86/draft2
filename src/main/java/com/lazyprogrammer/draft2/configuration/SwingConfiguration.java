package com.lazyprogrammer.draft2.configuration;

import com.lazyprogrammer.draft2.swing.MainPanel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.swing.JPanel;

@Configuration
public class SwingConfiguration {
  @Bean
  JPanel mainPanel() {
    return new MainPanel();
  }
}
