package edu.grinnell.csc207.util;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
  // +-------------+---------------------------------------------------
  // | Constructor |
  // +-------------+

  /** Construct a panel where we play the game, that will be part of
   *  our game window.
   * 
   * @param b
   *  The game board from which we derive size to display.
   */
  public GamePanel(Board b) {
    this.setPreferredSize(new Dimension(b.getWidth(), b.getHeight()));
    this.setBackground(Color.white);
  } // GamePanel(Board)

} // class GamePanel 
