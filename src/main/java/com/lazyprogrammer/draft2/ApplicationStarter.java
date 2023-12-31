package com.lazyprogrammer.draft2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class ApplicationStarter {

  public static void main(String[] args) {
    SpringApplication.run(ApplicationStarter.class, args);
  }

}
