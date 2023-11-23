package com.lazyprogrammer.draft2.swing.blueprint;

import lombok.Builder;
import lombok.Singular;

import java.util.Map;
import java.util.function.Function;

@Builder
public class BlueprintMap<K> {

  @Singular("blueprint")
  private final Map<K, Blueprint> map;

  public BlueprintMap(Map<K, Blueprint> map) {
    this.map = map;
  }

  public Blueprint get(K key) {
    return map.getOrDefault(key, Blueprints.EMPTY);
  }

  public void update(K key, Function<? super K, Blueprint> blueprint) {
    map.computeIfAbsent(key, blueprint);
  }
}
