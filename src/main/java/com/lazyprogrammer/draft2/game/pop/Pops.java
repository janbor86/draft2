package com.lazyprogrammer.draft2.game.pop;

import com.lazyprogrammer.draft2.game.resource.GameResources;
import java.util.List;

public class Pops {

  public static Pop forager() {
    return new Pop("Forager", List.of(GameResources.newborns(1)), List.of());
  }
}
