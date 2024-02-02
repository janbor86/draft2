package com.lazyprogrammer.draft2.ui.swing.menu;

import java.awt.Graphics;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MenuComponent extends JComponent {

  public MenuComponent(NewGameButton newGameButton) {
    final var child = createChildContainer(newGameButton);
    setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    child.setAlignmentX(CENTER_ALIGNMENT);
    add(child);
  }

  private JComponent createChildContainer(NewGameButton newGameButton) {
    final var child = new JComponent() {};
    child.setLayout(new BoxLayout(child, BoxLayout.Y_AXIS));
    newGameButton.setAlignmentY(CENTER_ALIGNMENT);
    child.add(newGameButton);
    return child;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
  }
}
