package com.lazyprogrammer.draft2.configuration;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Objects;

@Configuration
public class Images {

  private static final String POP_PNG = "pop.png";

  @Bean
  @SneakyThrows
  BufferedImage popImage() {
    return ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource(POP_PNG)));
  }
}
