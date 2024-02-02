package com.lazyprogrammer.draft2.game;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SessionHandler {

  private final Player player;
  private GameSession gameSession;

  @EventListener
  public void handle(NewGameInitiated event) {
    gameSession = new SinglePlayerSession(player);
    gameSession.start();
  }
}
