package com.lazyprogrammer.draft2.game.pop.create;

import com.lazyprogrammer.draft2.game.Player;
import com.lazyprogrammer.draft2.ui.swing.map.MapView;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreatePopTrigger extends MouseAdapter {

  private final MapView mapView;
  private final Player player;

  @Override
  public void mouseClicked(MouseEvent e) {
    final var selectedCoordinate = mapView.findAt(e.getPoint());
    player.createPopAt(selectedCoordinate);
    e.getComponent().repaint();
  }
}
