package com.lazyprogrammer.draft2.game;

import com.lazyprogrammer.draft2.game.map.Coordinate;
import com.lazyprogrammer.draft2.game.pop.Pop;
import com.lazyprogrammer.draft2.game.pop.create.PopCreatedEvent;
import com.lazyprogrammer.draft2.game.resource.GameResource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class HumanPlayer implements Player {

  private final ApplicationEventPublisher eventPublisher;
  private final List<GameResource> resources;

  @Override
  public void createPopAt(Coordinate coordinate, Pop pop) {
    eventPublisher.publishEvent(new PopCreatedEvent(this, coordinate, pop));
  }

  @Override
  public List<GameResource> getResources() {
    return resources.stream().toList();
  }
}
