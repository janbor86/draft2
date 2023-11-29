package com.lazyprogrammer.draft2.game.resource;

public class GameResources {
  public static GameResource newborns(int i) {
    final var newBorn = new NewBorn();
    newBorn.add(i);
    return newBorn;
  }
}
