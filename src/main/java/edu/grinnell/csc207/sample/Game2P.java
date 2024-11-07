package edu.grinnell.csc207.sample;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
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


    // beginning gameplay
    
   
  } // main(String[])
} // class Game2P
