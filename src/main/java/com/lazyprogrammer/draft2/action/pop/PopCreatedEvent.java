package com.lazyprogrammer.draft2.action.pop;

import com.lazyprogrammer.draft2.data.Coordinate;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class PopCreatedEvent extends ApplicationEvent {

  private final Coordinate coordinate;

  public PopCreatedEvent(Object source, Coordinate at) {
    super(source);
    coordinate = at;
  }
}
