package com.lazyprogrammer.draft2.game.pop;

import com.lazyprogrammer.draft2.game.map.Coordinate;
import com.lazyprogrammer.draft2.game.map.GameMap;
import com.lazyprogrammer.draft2.game.map.TileAttribute;
import java.util.*;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PopRepository {

  private final GameMap gameMap;

  public void add(Coordinate coordinate) {
    final var popNo = countPop(coordinate);
    gameMap.write(coordinate, TileAttribute.POP, popNo + 1);
  }

  public int countPop(Coordinate coordinate) {
    return gameMap.read(coordinate, TileAttribute.POP);
  }

  public Map<Coordinate, Integer> findAll() {
    return gameMap.getCoordinates().stream().collect(Collectors.toMap(c -> c, this::countPop));
  }
}
