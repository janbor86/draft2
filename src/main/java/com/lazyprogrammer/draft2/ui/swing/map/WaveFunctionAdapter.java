package com.lazyprogrammer.draft2.ui.swing.map;

import com.lazyprogrammer.draft2.game.map.terrain.generator.TerrainGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Component
@RequiredArgsConstructor
public class WaveFunctionAdapter extends MouseAdapter {

  private final TerrainGenerator generator;
  private final MapView mapView;

  @Override
  public void mouseClicked(MouseEvent e) {
    final var coordinate = mapView.findAt(e.getPoint());
    generator.generateTerrainAt(coordinate);
    e.getComponent().repaint();
  }
}
