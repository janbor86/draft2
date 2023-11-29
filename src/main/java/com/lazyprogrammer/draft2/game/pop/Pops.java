package com.lazyprogrammer.draft2.game.pop;

import com.lazyprogrammer.draft2.game.resource.GameResources;
import java.util.List;

public class Pops {

  public static Pop hunterGatherer() {
    return new Pop("Hunter-Gatherer", List.of(GameResources.newborns(1)), List.of());
  }
}
