package com.lazyprogrammer.draft2.swing;

import com.lazyprogrammer.draft2.swing.data.Coordinate;
import com.lazyprogrammer.draft2.swing.data.terrain.TerrainRepository;
import com.lazyprogrammer.draft2.swing.data.terrain.generator.TerrainGenerator;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class WaveFunctionKeyAdapter extends KeyAdapter {

  private final TerrainRepository repository;
  private final TerrainGenerator generator;
  private final HexMapComponent mapComponent;

  @Override
  public void keyPressed(KeyEvent e) {
    var rounds = 0;
    if (KeyEvent.VK_RIGHT == e.getKeyCode()) rounds = 1;
    if (KeyEvent.VK_SPACE == e.getKeyCode()) rounds = repository.findAll().size();
    if (rounds < 1) return;
    List<Coordinate> generated = generator.generate(rounds);
    if (generated.isEmpty()) return;
    mapComponent.getMapView().focusTo(generated.get(generated.size() - 1));
    mapComponent.repaint();
  }
}
