package com.lazyprogrammer.draft2.swing;

import com.lazyprogrammer.draft2.swing.data.terrain.TerrainGenerator;
import lombok.RequiredArgsConstructor;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@RequiredArgsConstructor
public class WaveFunctionAdapter extends MouseAdapter {

  private final TerrainGenerator generator;
  private final HexMapComponent mapComponent;

  @Override
  public void mouseClicked(MouseEvent e) {
    final var coordinate = mapComponent.getMapView()
                                       .findAt(e.getPoint());
    generator.generateTerrainAt(coordinate);
    mapComponent.repaint();
  }
}
