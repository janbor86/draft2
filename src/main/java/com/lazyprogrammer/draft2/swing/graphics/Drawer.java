package com.lazyprogrammer.draft2.swing.graphics;


import com.lazyprogrammer.draft2.swing.blueprint.Blueprint;
import com.lazyprogrammer.draft2.swing.blueprint.Blueprints;
import lombok.extern.slf4j.Slf4j;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class Drawer {
  public Drawer() {
    images = new HashMap<>();
  }


  Map<Blueprint, BufferedImage> images;

  public BufferedImage drawHex(Blueprint blueprint) {
    return images.computeIfAbsent(blueprint, this::drawImage);
  }

  private BufferedImage drawImage(Blueprint blueprint) {
    log.info("draw {}", blueprint);
    if (blueprint == Blueprints.EMPTY) {
      return new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
    }
    return drawShape(blueprint);
  }

  private static BufferedImage drawShape(Blueprint blueprint) {
    final var shape = blueprint.shape();
    final var image = new BufferedImage(shape.getBounds().width, shape.getBounds().height, BufferedImage.TYPE_INT_ARGB);
    final var imageGraphic = (Graphics2D) image.getGraphics();
    imageGraphic.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    imageGraphic.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
    imageGraphic.setColor(blueprint.fillColor());
    imageGraphic.fill(shape);
    imageGraphic.setColor(blueprint.strokeColor());
    imageGraphic.setStroke(blueprint.stroke());
    imageGraphic.draw(shape);
    return image;
  }

}
