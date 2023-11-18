package com.lazyprogrammer.draft2.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "graphics")
public class GraphicsConfiguration {

  WindowMode mode;
  @Min(value = 0, message = "Value must be greater than or equal to 0") int monitorId;

}

