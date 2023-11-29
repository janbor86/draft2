package com.lazyprogrammer.draft2.game.pop;

import com.lazyprogrammer.draft2.game.GameState;
import com.lazyprogrammer.draft2.game.map.Coordinate;
import java.util.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PopRepository {

  private final GameState gameState;

  public void add(Coordinate coordinate, Pop pop) {
    gameState.addPop(coordinate, pop);
  }

  public List<Pop> lookup(Coordinate coordinate) {
    return gameState.getPop(coordinate);
  }
}
