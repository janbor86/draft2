package com.lazyprogrammer.draft2.game;

import com.lazyprogrammer.draft2.game.map.Coordinate;
import com.lazyprogrammer.draft2.game.pop.Pop;

import java.util.List;

public interface GameState {
  void addPop(Coordinate at, Pop pop);

  List<Pop> getPop(Coordinate coordinate);

  int newTurn();

  int getTurnCounter();
}
