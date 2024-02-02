package com.lazyprogrammer.draft2.game;

import org.springframework.context.ApplicationEvent;

public class NewGameInitiated extends ApplicationEvent {
  public NewGameInitiated(Object source) {
    super(source);
  }
}
