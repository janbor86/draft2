package com.lazyprogrammer.draft2.swing.blueprint;

import lombok.Builder;
import lombok.Singular;

import java.util.Map;

@Builder
public class BlueprintMap<K> {

  @Singular("blueprint")
  private final Map<K, Blueprint> map;

  public Blueprint get(K key) {
    return map.getOrDefault(key, Blueprints.EMPTY);
  }
}