package com.lazyprogrammer.draft2.game;

import com.lazyprogrammer.draft2.game.map.Coordinate;
import com.lazyprogrammer.draft2.game.pop.Pop;
import com.lazyprogrammer.draft2.game.pop.create.PopCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class HumanPlayer implements Player {

  private final ApplicationEventPublisher eventPublisher;

  @Override
  public void createPopAt(Coordinate coordinate, Pop pop) {
    eventPublisher.publishEvent(new PopCreatedEvent(this, coordinate, pop));
  }
}
