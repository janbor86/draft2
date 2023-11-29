package com.lazyprogrammer.draft2.game.pop;

import com.lazyprogrammer.draft2.game.resource.GameResource;
import com.lazyprogrammer.draft2.game.resource.NewBorn;

import java.util.Map;

public record Pop(
    String name,
    Map<Class<? extends GameResource>, Integer> producerMap,
    Map<Class<? extends GameResource>, Integer> consumerMap) {

  public int newborns() {
    return producerMap.getOrDefault(NewBorn.class, 0);
  }
}
