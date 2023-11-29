package com.lazyprogrammer.draft2.game;

import com.lazyprogrammer.draft2.game.map.Coordinate;
import com.lazyprogrammer.draft2.game.pop.Pop;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class GameStateV1 implements GameState {

  private final Map<Coordinate, List<Pop>> pops;
  @Getter private int turnCounter;

  public GameStateV1() {
    this.pops = new HashMap<>();
    turnCounter = 1;
  }

  @Override
  public void addPop(Coordinate at, Pop pop) {
    pops.compute(
        at,
        (coordinate, popList) -> {
          if (popList == null) popList = new ArrayList<>();
          popList.add(pop);
          return popList;
        });
  }

  @Override
  public List<Pop> getPop(Coordinate coordinate) {
    return pops.getOrDefault(coordinate, List.of());
  }

  @Override
  public int newTurn() {
    return ++turnCounter;
  }
}
