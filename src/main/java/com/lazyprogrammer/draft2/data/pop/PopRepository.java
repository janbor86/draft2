package com.lazyprogrammer.draft2.data.pop;

import com.lazyprogrammer.draft2.data.Coordinate;
import com.lazyprogrammer.draft2.data.GameMap;
import com.lazyprogrammer.draft2.data.TileAttribute;
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
