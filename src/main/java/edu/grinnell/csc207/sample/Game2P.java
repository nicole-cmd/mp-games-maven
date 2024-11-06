package edu.grinnell.csc207.sample;

import edu.grinnell.csc207.util.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.lang.Integer;

import javax.swing.*;
import java.awt.*;

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
                  """);
    } // intro(PrintWriter)

    /** Display rules at the beginning of a game and if player wishes to see them again.
     * 
     * @param pen
     *  PrintWriter used to print the rules.
    */
    public static void showRules(PrintWriter pen) {
      pen.println("""
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

                  + -s direction tile -- determine where you will set your piece in relation to a specified tile
                  + -f direction -- determine which adjacent opponent piece you want to flip
                  + -r -- rotate?
                  + -p -- preview which piece will be set on the board next
                  + -h -- call for help to reference the rules and list of commands
                  """);
    } // showRules(PrintWriter)

    /** Determine the winner of the game. 
     * 
     * @param pen
     *  PrintWriter that prints who the winner is.
     * @param endBoard
     *  What the board looks like at the end of the game.
    */
    static void whoWon(PrintWriter pen, MatrixV0<Tile> endBoard) {
      //stub
    } // whoWon(PrintWriter, MatrixV0<Tile>)

    /** Opens a new window through which one plays Sayu. */
    private static void newWindow() {
      Random rand = new Random();
      int gameNum = rand.nextInt();
      JFrame window = new JFrame("SAYU Game: " + gameNum);

      // closing the window terminates the program
      window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // make a new board to play on and add it to the game window
      Board board = new Board(new Random());
      window.add(board);

      // mouse movement facilitates gameplay by determining where tiles go
      window.addMouseListener(board);

      // prevents user from resizing the window and fits the window around the jpanel
      window.setResizable(false);
      window.pack();

      // open window in the center of the screen and display it
      window.setLocationRelativeTo(null);
      window.setVisible(true);
    } // newWindow()

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
    BufferedReader eyes = new BufferedReader(new InputStreamReader(System.in));

    // game prologue - redundant 
    intro(pen);
    pen.flush();
    showRules(pen);
    pen.flush();

    // notes about invokeLater() on Lines 31-2 from
    // https://github.com/learncodebygaming/java_2d_game/blob/master/App.java
    // when main runs, this will initiate gameplay in a new window
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        newWindow();
      }
    });

    // beginning gameplay

  } // main(String[])
} // class Game2P
