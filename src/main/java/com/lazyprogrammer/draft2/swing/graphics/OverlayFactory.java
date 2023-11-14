package com.lazyprogrammer.draft2.swing.graphics;

import net.lazyprogrammer.hex.swing.Hex;
import net.lazyprogrammer.hex.swing.blueprint.BlueprintMap;
import net.lazyprogrammer.hex.swing.blueprint.Blueprints;
import net.lazyprogrammer.hex.swing.map.HexMapConfig;
import net.lazyprogrammer.hex.swing.map.MapUI;
import net.lazyprogrammer.hex.swing.map.Maps;

public class OverlayFactory {

  static final Drawer DRAWER = new Drawer();

  public static Overlay terrainOverlay(HexMapConfig hexMapConfig) {
    final var mapMatrix = Maps.createLameOne(hexMapConfig);
    return new Overlay(initializeBlueprints(hexMapConfig.hex()), DRAWER, mapMatrix);
  }

  private static BlueprintMap<Integer> initializeBlueprints(Hex hex) {
    return BlueprintMap.<Integer>builder()
                       .blueprint(0, Blueprints.oceanHex(hex))
                       .blueprint(1, Blueprints.grassHex(hex))
                       .blueprint(2, Blueprints.forestHex(hex))
                       .blueprint(3, Blueprints.mountain(hex))
                       .build();
  }

  public static MapUI mapUi(HexMapConfig mapConfig) {
    return new MapUI(BlueprintMap.<Integer>builder()
                                 .blueprint(1, Blueprints.highlight(mapConfig.hex()))
                                 .build(), DRAWER, Maps.emptyOne(mapConfig));
  }
}