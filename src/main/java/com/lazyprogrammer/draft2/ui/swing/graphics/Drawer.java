package com.lazyprogrammer.draft2.ui.swing.graphics;

import com.lazyprogrammer.draft2.ui.swing.blueprint.Blueprint;
import com.lazyprogrammer.draft2.ui.swing.blueprint.Blueprints;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Drawer {

  public Drawer() {
    images = new HashMap<>();
  }

  Map<Blueprint, BufferedImage> images;

  public BufferedImage draw(Blueprint blueprint) {
    return images.computeIfAbsent(blueprint, this::drawImage);
  }

  private BufferedImage drawImage(Blueprint blueprint) {
    log.info("draw {}", blueprint);
    if (blueprint == Blueprints.EMPTY) return new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);

    return drawShape(blueprint);
  }

  private static BufferedImage drawShape(Blueprint blueprint) {
    final var shape = blueprint.shape();
    final var image =
        new BufferedImage(
            shape.getBounds().width, shape.getBounds().height, BufferedImage.TYPE_INT_ARGB);
    final var imageGraphic = prepareGraphics(image);
    if (blueprint.hasColor()) fillShape(blueprint, imageGraphic, shape);
    if (blueprint.hasContour()) drawContour(blueprint, imageGraphic, shape);
    return image;
  }

  private static void drawContour(Blueprint blueprint, Graphics2D imageGraphic, Shape shape) {
    imageGraphic.setColor(blueprint.strokeColor());
    imageGraphic.setStroke(blueprint.stroke());
    imageGraphic.draw(shape);
  }

  private static void fillShape(Blueprint blueprint, Graphics2D imageGraphic, Shape shape) {
    imageGraphic.setColor(blueprint.fillColor());
    imageGraphic.fill(shape);
  }

  private static Graphics2D prepareGraphics(BufferedImage image) {
    final var imageGraphic = (Graphics2D) image.getGraphics();
    imageGraphic.setRenderingHint(
        RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    imageGraphic.setRenderingHint(
        RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
    return imageGraphic;
  }
}
