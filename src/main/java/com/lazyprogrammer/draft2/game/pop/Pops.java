package com.lazyprogrammer.draft2.game.pop;

import com.lazyprogrammer.draft2.game.resource.NewBorn;

import java.util.Map;

public class Pops {

  public static Pop hunterGatherer() {
    return new Pop("Hunter-Gatherer", Map.of(NewBorn.class, 1), Map.of());
  }
}
