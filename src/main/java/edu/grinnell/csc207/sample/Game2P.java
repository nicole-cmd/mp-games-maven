package edu.grinnell.csc207.sample;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.swing.*;
import java.awt.*;

import edu.grinnell.csc207.util.*;
/**
 * Runs a game of Sayu with additional features to facilitate gamplay.
 * 
 * @author Cade Johnston
 * @author Nicole Gorrell
 */
public class Game2P {
  // +-----------+---------------------------------------------------
  // | Constants |
  // +-----------+

  /** 
   * Setting constant width and height of our game window. 
   */
  static final int WINDOW_W = 600;
  static final int WINDOW_H = 400;

  // +----------------+----------------------------------------------
  // | Helper methods |
  // +----------------+

    /** Display a brief introduction upon initiating a new game. 
     * 
     * @param pen
     *  PrintWriter used to print the introduction.
    */
    public static void intro(PrintWriter pen) {
      pen.println("""
                  Welcome to Sayu. This is a game created by Khanat Sadomwattana where players place
                  tiles and flip their opponent's tiles. A player's goal is to have the most tiles with
                  their color on the board by the game's end. Once the tiles run out, the player with the
                  most tiles on the board wins.

                  Here are the rules:
                  
                  1. Place 
                  One player starts by placing a 'blank' tile (the one with no arrow). The second player
                  then must place a tile either up, down, left, or right of this tile--and no more than
                  3 spaces away from it. Players must follow this placement method on each turn. 

                  2. Flip
                  Each player may have a chance to flip their opponent's tiles on their turn. Players may
                  flip one tile that is adjacent to the one they just placed and must flip tiles one at a
                  time. There are additional guidelines to flipping tiles. You cannot flip an opponent's
                  tile if:
                  
                  - Their tile has a color arrow that points back to yours
                  - The white arrow on their tile points in the same direction as yours
                  
                  Once a tile is flipped, it will be rotated so that the white arrow points in the same
                  direction as the tile that was placed at the start of the turn. If no more tiles can be
                  flipped, then the other player will take their turn.

                  3. Combo
                  A flipped tile can be used to flip another. Players can continue flipping until there are
                  no more possible flips, ending their turn.


                  Here are moves you can make to proceed:

                  
                  """);
    } // intro(PrintWriter)

    /** Opens a new window through which one plays Sayu. */
    private static void newGameWindow() {
      Random rand = new Random();
      int gameNum = rand.nextInt();
      JFrame window = new JFrame("SAYU Game: " + gameNum);

      // closing the window terminates the program
      window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // prevents user from resizing the window and fits the window around the JPanel
      window.setSize(WINDOW_W,WINDOW_H);
      window.setResizable(false);
      window.setBackground(Color.orange);
      window.pack();

      // make a new board to play on and add it to the game window
      Board board = new Board(new Random());
      GamePanel gamePanel = new GamePanel(board);
      window.add(gamePanel);

      // // mouse movement facilitates gameplay by determining where tiles go -- must make mouse listener object
      // MouseListener mouse = new MouseListener() {
        
      // };
      // window.addMouseListener(mouse);

      // open window in the center of the screen and display it
      window.setLocationRelativeTo(null);
      window.setVisible(true);
    } // newWindow()

    /** Generate the next game piece based off who is P1/P2.
     *  Assume: P1 -> blue, P2 -> red
     * 
     * @param b
     *  The board we pass in to draw.
     * @param graphic
     */
    public void drawTile(Board board, Graphics g) {
      Tile next = board.preview();

      g.drawPolygon(null);


      if (next.getOwner() == false) {
        g.setColor(Color.blue);
      } else {
        g.setColor(Color.red);
      } // if/else



    } // drawPiece()

  // +------+--------------------------------------------------------
  // | Main |
  // +------+

  /** Run a game.
   * 
   * @param args
   *  Command-line arguments.
   * @throws IOException
   */
  public static void main(String[] args) throws IOException {
    PrintWriter pen = new PrintWriter(System.out, true);

    // game prologue
    intro(pen);
    pen.flush();

    // notes about invokeLater() on Lines 31-2 from
    // https://github.com/learncodebygaming/java_2d_game/blob/master/App.java
    // when main runs, this will initiate gameplay in a new window
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        newGameWindow();
      }
    });

    // beginning gameplay
    // boolean player = false;

    // IOUtils.readCommand(pen, eyes, "Are you P1 or P2?: ", args);

    // if (args[0].contains("P1")) {
    //   player = false;
    // } else {
    //   player = true;
    // } // if/else
   
  } // main(String[])
} // class Game2P
