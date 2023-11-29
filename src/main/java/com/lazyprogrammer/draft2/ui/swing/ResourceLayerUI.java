package com.lazyprogrammer.draft2.ui.swing;

import com.lazyprogrammer.draft2.ui.swing.map.MapComponent;

import javax.swing.JComponent;
import javax.swing.plaf.LayerUI;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

public class ResourceLayerUI extends LayerUI<MapComponent> {

  private BufferedImage backGroundImage;

  @Override
  public void paint(Graphics g, JComponent mapComponent) {
    super.paint(g, mapComponent);
    Graphics2D g2d = (Graphics2D) g;
    final var bounds = mapComponent.getBounds();
    drawBackGround(bounds, g2d);
  }

  private void drawBackGround(Rectangle bounds, Graphics2D g2d) {
    final var backGroundImage = lookupBackGroundImage(bounds);
    g2d.drawImage(backGroundImage, 0, 0, null);
  }

  private BufferedImage lookupBackGroundImage(Rectangle bounds) {
    if (backGroundImage != null) return backGroundImage;
    var strokeWidth = 10f;
    Shape backGroundShape =
        new RoundRectangle2D.Double(
            -strokeWidth / 2d,
            -strokeWidth / 2d,
            bounds.width / 2D,
            bounds.getHeight() / 20d,
            10d,
            10d);
    final var backGroundImage =
        new BufferedImage(
            (int) (backGroundShape.getBounds().width + strokeWidth),
            (int) (backGroundShape.getBounds().height + strokeWidth),
            BufferedImage.TYPE_INT_ARGB);
    final var graphics = (Graphics2D) backGroundImage.getGraphics();
    graphics.translate(strokeWidth / 2, strokeWidth / 2);
    graphics.setColor(new Color(73, 45, 37));
    graphics.fill(backGroundShape);
    graphics.setColor(new Color(114, 73, 53));
    graphics.setStroke(new BasicStroke(strokeWidth));
    graphics.draw(backGroundShape);
    graphics.setColor(new Color(255, 215, 0));
    graphics.setStroke(new BasicStroke(strokeWidth / 10f));
    graphics.draw(backGroundShape);
    return backGroundImage;
  }
}
