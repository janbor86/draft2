package com.lazyprogrammer.draft2.swing.map;

import com.lazyprogrammer.draft2.swing.data.Coordinate;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.awt.Point;
import java.awt.Rectangle;

@Slf4j
@ToString
public class MapView {

  private final Rectangle viewBoundary;
  @Getter
  private final MapConfig mapConfig;
  @Getter
  private Coordinate focused;

  public MapView(MapConfig mapConfig) {
    this.mapConfig = mapConfig;
    this.viewBoundary = new Rectangle();
  }

  public int getZoomLevel() {
    return mapConfig.hexSize();
  }

  public void translate(int dx, int dy) {
    viewBoundary.translate(dx, dy);
  }

  public Point getLocation() {
    return viewBoundary.getLocation();
  }

  public void resize(int width, int height) {
    log.info("resize to {}x{}", width, height);
    viewBoundary.setSize(width, height);
  }

  public void translateView(int dx, int dy) {
    translate(dx, dy);
    correctLocation();
  }

  public void setView(int x, int y) {
    log.trace("set view: {}x{}", x, y);
    viewBoundary.setLocation(new Point(x, y));
    correctLocation();
    log.info("{}", getLocation());
  }

  private void correctLocation() {
    var minimumX = viewBoundary.width - mapConfig.width();
    var minimumY = viewBoundary.height - mapConfig.height();
    var x = Math.min(0, Math.max(minimumX, getLocation().x));
    var y = Math.min(mapConfig.verticalSpacing() / 2, Math.max(minimumY, getLocation().y));
    viewBoundary.setLocation(new Point(x, y));
  }

  public Coordinate findAt(Point at) {
    log.trace("search at {}", at);
    final var location = getLocation();
    log.trace("TL:{}", location);
    var dx = at.x - location.x;
    var dy = at.y - location.y;
    log.trace("dx:{},dy:{}", dx, dy);
    dx -= mapConfig.hexWidth() / 8;
    int x = dx / mapConfig.horizontalSpacing();
    if (x % 2 == 1) {
      dy += mapConfig.verticalSpacing() / 2;
    }
    int y = dy / mapConfig.verticalSpacing();
    x = Math.min(mapConfig.columnNo() - 1, Math.max(0, x));
    y = Math.min(mapConfig.rowNo() - 1, Math.max(0, y));
    log.trace("is it {}:{}", x, y);
    return new Coordinate(x, y);
  }

  public Point calculateCenter(Coordinate coordinate) {
    final var locationOnMap = calculateLocationOnMap(coordinate);
    Point offset = getLocation();
    locationOnMap.translate(offset.x, offset.y);
    return locationOnMap;
  }

  private Point calculateLocationOnMap(Coordinate coordinate) {
    var x = coordinate.x() * mapConfig.horizontalSpacing();
    var verticalOffset = (int) (((coordinate.x() % 2) * mapConfig.hexHeight()) / 2D);
    var y = coordinate.y() * mapConfig.verticalSpacing() - verticalOffset;
    return new Point(x, y);
  }

  public void focusTo(Coordinate focus) {
    focused = focus;
    final var mapLocation = calculateLocationOnMap(focus);
    log.trace("focus to {} at {}", mapLocation, focus);
    final var horizontalOffset = viewBoundary.width / 2;
    final var verticalOffset = viewBoundary.height / 2;
    setView(-mapLocation.x + horizontalOffset, -mapLocation.y + verticalOffset);
  }
}
