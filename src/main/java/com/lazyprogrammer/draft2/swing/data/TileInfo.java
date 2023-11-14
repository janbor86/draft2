package com.lazyprogrammer.draft2.swing.data;

import java.util.EnumMap;
import java.util.Map;

public class TileInfo {

  private final Map<TileAttribute, Integer> infoMap;

  public TileInfo() {
    infoMap = new EnumMap<>(TileAttribute.class);
  }

  public Integer get(TileAttribute attribute) {
    return infoMap.get(attribute);
  }

  public void set(TileAttribute attribute, Integer value) {
    infoMap.put(attribute, value);
  }
}
