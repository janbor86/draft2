package com.lazyprogrammer.draft2.game;

import com.lazyprogrammer.draft2.game.map.Coordinate;
import com.lazyprogrammer.draft2.game.pop.Pop;

public interface Player {

  void createPopAt(Coordinate coordinate, Pop pop);
}
