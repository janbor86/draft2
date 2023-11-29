package com.lazyprogrammer.draft2.game.pop;

import com.lazyprogrammer.draft2.game.resource.GameResource;
import com.lazyprogrammer.draft2.game.resource.NewBorn;
import java.util.List;

public record Pop(String name, List<GameResource> produces, List<GameResource> consumes) {

  public int newborns() {
    return produces.stream()
        .filter(r -> r instanceof NewBorn)
        .map(GameResource::getAmount)
        .findFirst()
        .orElse(0);
  }
}
