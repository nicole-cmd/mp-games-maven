package edu.grinnell.csc207.sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    BufferedReader eyes = new BufferedReader(new InputStreamReader(System.in));

    // game prologue
    intro(pen);
    pen.flush();

    Random rand = new Random();
    int game = rand.nextInt();

    // Process the command line
    for (int i = 0; i < args.length; i++) {
      switch (args[i]) {
        case "-s":
          try {
            game = Integer.parseInt(args[++i]);
          } catch (Exception e) {
            System.err.printf("Invalid game number: %s\n", args[i]);
            return;
          } // try/catch
          break;

        default:
          System.err.printf("Invalid command-line flag: %s\n", args[i]);
          return;
      } // switch
    } // for
    // beginning gameplay

    Board boardGame = new Board(new Random(game));

    pen.println("Game setup number " + game);
    pen.println();

    String[] commands = new String[] {"Rotate", "PLACE", "FLIP", "END_TURN"};
    while(boardGame.notDone()) {
      pen.println(boardGame.toString());
      String command = IOUtils.readCommand(pen, eyes, "Action: ", commands);
      switch (command.toUpperCase()) {
        case "Rotate":
          if (!(boardGame.stage())) {
            int dir =
              IOUtils.readInt(pen, eyes, "Direction to rotate (0: Clockwise, 1: Counter): ", 0, 1);
            if (boardGame.attemptSelection(0, dir)) {
              
            } // if
          }
          break;
        case "PLACE":
          int x =
              IOUtils.readInt(pen, eyes, "X: ", 0, 6);
          int y =
              IOUtils.readInt(pen, eyes, "Y: ", 0, 6);

          if (! (boardGame.attemptSelection(1, x + y * 7))) {
            pen.println("Invalid Coordinate, canceling action.");
          } // if
          break;
        case "FLIP":
          int x2 =
                IOUtils.readInt(pen, eyes, "X: ", 0, 6);
          int y2 =
                IOUtils.readInt(pen, eyes, "Y: ", 0, 6);
          if (! (boardGame.attemptSelection(2, x2 + y2 * 7))) {
            pen.println("Invalid Coordinate, canceling action.");
          } // if
          break;
        case "END_TURN":
          if (boardGame.attemptSelection(3, 0)) {
            boardGame.nextPiece();
          } // if
          break;
        default:
          pen.printf("Unexpected command: '%s'. Please try again.\n", command);
          break;
      } // switch
    } // while
    pen.printf("The winner of the game is: ");
    if(boardGame.getWinner()) {
      pen.print("Player 1");
    } else {
      pen.print("Player 2");
    }
  } // main(String[])
} // class Game2P
