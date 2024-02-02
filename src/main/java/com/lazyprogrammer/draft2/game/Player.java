package com.lazyprogrammer.draft2.game;

import com.lazyprogrammer.draft2.game.map.Coordinate;
import com.lazyprogrammer.draft2.game.pop.Pop;
import com.lazyprogrammer.draft2.game.resource.GameResource;
import java.util.List;

public interface Player {

  long getId();

  void createPopAt(Coordinate coordinate, Pop pop);

  List<GameResource> getResources();
}
