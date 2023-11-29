package com.lazyprogrammer.draft2.game;

import com.lazyprogrammer.draft2.game.map.Coordinate;
import com.lazyprogrammer.draft2.game.pop.Pop;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class GameState {

  private final Map<Coordinate, List<Pop>> pops;

  public GameState() {
    this.pops = new HashMap<>();
  }

  public void addPop(Coordinate at, Pop pop) {
    pops.compute(
        at,
        (coordinate, popList) -> {
          if (popList == null) popList = new ArrayList<>();
          popList.add(pop);
          return popList;
        });
  }

  public List<Pop> getPop(Coordinate coordinate) {
    return pops.getOrDefault(coordinate, List.of());
  }
}
