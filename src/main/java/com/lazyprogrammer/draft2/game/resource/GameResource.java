package com.lazyprogrammer.draft2.game.resource;

import lombok.Getter;

@Getter
public abstract class GameResource {

  private int amount;

  public int add(int value) {
    if (value < 0) throw new IllegalArgumentException("Use reduce for subtraction!");
    this.amount += value;
    return this.amount;
  }

  public int reduce(int value) {
    if (value < 0) throw new IllegalArgumentException("Use add for addition!");
    this.amount = Math.max(amount - value, 0);
    return this.amount;
  }
}
