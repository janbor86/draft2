package com.lazyprogrammer.draft2.ui.swing;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import org.springframework.stereotype.Component;

@Component
public class EndTurnButton extends JButton {

  private boolean hovered = false;

  public EndTurnButton() {
    super("END TURN");

    setOpaque(false);
    setContentAreaFilled(false);
    setFocusPainted(false);
    setBorderPainted(false);

    addMouseListener(
        new MouseAdapter() {

          @Override
          public void mouseClicked(MouseEvent e) {
          }

          @Override
          public void mouseEntered(MouseEvent e) {
            hovered = true;
            repaint();
          }

          @Override
          public void mouseExited(MouseEvent e) {
            hovered = false;
            repaint();
          }
        });
  }

  @Override
  protected void paintComponent(Graphics g) {
    Graphics2D g2d = (Graphics2D) g.create();

    int width = getWidth();
    int height = getHeight();

    // Gradient background
    if (hovered) {
      g2d.setPaint(new GradientPaint(0, 0, Color.WHITE, 0, height, Color.LIGHT_GRAY));
    } else {
      g2d.setPaint(new GradientPaint(0, 0, Color.LIGHT_GRAY, 0, height, Color.WHITE));
    }
    g2d.fillRoundRect(0, 0, width, height, 10, 10);

    // Rounded border
    g2d.setColor(Color.BLACK);
    g2d.drawRoundRect(0, 0, width , height , 10, 10);

    // Text
    g2d.setColor(Color.BLACK);
    FontMetrics fm = g2d.getFontMetrics();
    int textWidth = fm.stringWidth(getText());
    int textHeight = fm.getHeight();
    int x = (width - textWidth) / 2;
    int y = (height - textHeight) / 2 + fm.getAscent();
    g2d.drawString(getText(), x, y);

    g2d.dispose();
  }
}
