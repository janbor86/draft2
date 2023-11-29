package com.lazyprogrammer.draft2.game.pop.create;

import com.lazyprogrammer.draft2.game.pop.PopRepository;
import javax.swing.JComponent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PopCreatedListener {

  private final PopRepository repository;

  @EventListener
  public void createPop(PopCreatedEvent popCreatedEvent) {
    final var coordinate = popCreatedEvent.getCoordinate();
    log.info("pop created at: {}", coordinate);
    final JComponent source = (JComponent) popCreatedEvent.getSource();
    repository.add(coordinate);
    source.repaint();
  }
}
