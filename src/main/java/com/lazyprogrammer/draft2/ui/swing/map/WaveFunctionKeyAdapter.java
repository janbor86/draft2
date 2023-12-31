package com.lazyprogrammer.draft2.ui.swing.map;

import com.lazyprogrammer.draft2.game.map.Coordinate;
import com.lazyprogrammer.draft2.game.map.terrain.TerrainRepository;
import com.lazyprogrammer.draft2.game.map.terrain.TerrainType;
import com.lazyprogrammer.draft2.game.map.terrain.generator.TerrainGenerator;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class WaveFunctionKeyAdapter extends KeyAdapter {

  private final TerrainRepository repository;
  private final TerrainGenerator generator;
  private final MapView mapView;

  @Override
  public void keyPressed(KeyEvent e) {
    if (KeyEvent.VK_DELETE == e.getKeyCode()) deleteTerrain();
    var rounds = 0;
    if (KeyEvent.VK_RIGHT == e.getKeyCode()) rounds = 1;
    if (KeyEvent.VK_SPACE == e.getKeyCode()) rounds = repository.findAll().size();
    if (rounds < 1) return;
    List<Coordinate> generated = generator.generate(rounds);
    if (generated.isEmpty()) return;
    mapView.focusTo(generated.get(generated.size() - 1));
    e.getComponent().repaint();
  }

  private void deleteTerrain() {
    final var mapConfig = mapView.getMapConfig();
    generator.reset(mapConfig);
    final var columnNo = mapConfig.columnNo();
    final var rowNo = mapConfig.rowNo();
    for (int i = 0; i < columnNo; i++) {
      for (int j = 0; j < rowNo; j++) {
        repository.write(new Coordinate(i, j), TerrainType.UNDEFINED);
      }
    }
    for (int i = 0; i < columnNo; i++) {
      repository.write(new Coordinate(i, 0), TerrainType.OCEAN);
      repository.write(new Coordinate(i, 1), TerrainType.OCEAN);
      repository.write(new Coordinate(i, rowNo - 2), TerrainType.OCEAN);
      repository.write(new Coordinate(i, rowNo - 1), TerrainType.OCEAN);
    }
    for (int j = 0; j < rowNo; j++) {
      repository.write(new Coordinate(0, j), TerrainType.OCEAN);
      repository.write(new Coordinate(1, j), TerrainType.OCEAN);
      repository.write(new Coordinate(columnNo - 2, j), TerrainType.OCEAN);
      repository.write(new Coordinate(columnNo - 1, j), TerrainType.OCEAN);
    }
    generator.load();
  }
}
