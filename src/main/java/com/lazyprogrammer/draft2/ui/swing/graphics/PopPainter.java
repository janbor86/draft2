package com.lazyprogrammer.draft2.ui.swing.graphics;

import com.lazyprogrammer.draft2.game.map.Coordinate;
import com.lazyprogrammer.draft2.game.map.MapConfig;
import com.lazyprogrammer.draft2.game.pop.PopRepository;
import com.lazyprogrammer.draft2.ui.swing.map.MapView;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Set;

@Order(20)
@Component
@RequiredArgsConstructor
public class PopPainter implements Painter {

  private final PopRepository repository;
  private final BufferedImage popImage;
  private final MapConfig mapConfig;

  @Override
  public void paint(Graphics2D g2d, MapView view, Set<Coordinate> coordinates) {
    coordinates.forEach(
        coordinate -> {
          var popNo = repository.countPop(coordinate);
          if (popNo > 0) drawPop(g2d, view.calculateCenter(coordinate), view.getZoomLevel());
        });
  }

  private void drawPop(Graphics2D graphics2D, Point onScreenLocation, int zoomLevel) {
    final var scaledInstance = popImage.getScaledInstance(zoomLevel, zoomLevel, Image.SCALE_SMOOTH);
    onScreenLocation.translate((mapConfig.hexWidth()) / 4, (mapConfig.hexHeight()) / 4);
    //    drawFrame(graphics2D, onScreenLocation, scaledInstance);
    graphics2D.drawImage(scaledInstance, onScreenLocation.x, onScreenLocation.y, null);
  }

  private void drawFrame(Graphics2D graphics2D, Point onScreenLocation, Image image) {
    graphics2D.setColor(Color.WHITE);
    graphics2D.drawRect(
        onScreenLocation.x, onScreenLocation.y, image.getWidth(null), image.getHeight(null));
  }
}
