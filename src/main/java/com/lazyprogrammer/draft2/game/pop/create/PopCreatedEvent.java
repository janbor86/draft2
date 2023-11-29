package com.lazyprogrammer.draft2.game.pop.create;

import com.lazyprogrammer.draft2.game.map.Coordinate;
import com.lazyprogrammer.draft2.game.pop.Pop;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class PopCreatedEvent extends ApplicationEvent {

  private final Coordinate coordinate;
  private final Pop pop;

  public PopCreatedEvent(Object source, Coordinate at, Pop newPop) {
    super(source);
    coordinate = at;
    pop = newPop;
  }
}
