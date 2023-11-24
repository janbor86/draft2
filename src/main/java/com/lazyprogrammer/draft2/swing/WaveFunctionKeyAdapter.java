package com.lazyprogrammer.draft2.swing;

import com.lazyprogrammer.draft2.swing.data.terrain.TerrainGenerator;
import com.lazyprogrammer.draft2.swing.data.terrain.TerrainRepository;
import com.lazyprogrammer.draft2.swing.data.terrain.TerrainType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

@Slf4j
@RequiredArgsConstructor
public class WaveFunctionKeyAdapter extends KeyAdapter {

  private final TerrainRepository repository;
  private final TerrainGenerator generator;
  private final HexMapComponent mapComponent;

  @Override
  public void keyPressed(KeyEvent e) {
    var rounds = 0;
    if (KeyEvent.VK_RIGHT == e.getKeyCode())
      rounds = 1;
    if (KeyEvent.VK_END == e.getKeyCode())
      rounds = repository.findAll()
                         .size();
    while (rounds-- > 0) {
      final var allUndefined = new ArrayList<>(repository.findAll(TerrainType.UNDEFINED));
      if (allUndefined.isEmpty())
        return;

//      Collections.shuffle(allUndefined);
      final var next = allUndefined.get(0);
      generator.generateTerrainAt(next);
      mapComponent.getMapView()
                  .focusTo(next);
    }
    mapComponent.repaint();
  }
}
