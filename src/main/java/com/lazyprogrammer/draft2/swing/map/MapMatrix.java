package com.lazyprogrammer.draft2.swing.map;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MapMatrix {

  private final Integer[][] map;

  public int getColumnNo() {
    return map.length;
  }

  public int getRowNo() {
    return map[0].length;
  }
  public int get(int x,int y) {
    return map[x][y];
  }

  public void set(int x, int y, int value) {
    map[x][y] = value;
  }
}
