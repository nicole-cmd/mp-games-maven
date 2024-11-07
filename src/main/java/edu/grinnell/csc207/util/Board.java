package edu.grinnell.csc207.util;

import java.util.Arrays;
import java.util.Random;

/**
 * An object that represents a game of Sayu.
 * Includes methods to progress the game.
 *
 * @author Cade Johnston
 * @author Nicole Gorrell
 */
public class Board {
  // +--------+-------------------------------------------------------
  // | Fields |
  // +--------+

  /** The amount of tiles on the board. */
  private int count;

  /** A piece that gets placed onto the board. */
  private Tile[] pieces;

  /** The board we play (place pieces) on. */
  private MatrixV0<Tile> gameBoard;

  /** The dimensions of the board. */
  private static final int DIM = 7;

  // +--------------+-------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Construct the game board.
   * 
   * @param rand
   *   A Random object with some seed to use for randomization.
   */
  public Board(Random rand) {
    this.count = 1;
    this.pieces = new Tile[dim() * dim()];
    this.tileGroup(rand);
    this.gameBoard = new MatrixV0<Tile>(dim(), dim());
    this.gameBoard.set(3, 3, this.pieces[0]); // first game piece
  } // makeBoard()

  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Generate all possible tiles. Allows for players to pull from this group to use a piece. 
   */
  private void tileGroup(Random seed) {
    int tile = 1; // keep track of the tile we are on
    this.pieces[0] = new Tile(-1, new int[0]);
    for (int i = 0; i < 5; i++) {
      for (int k = i + 1; k < Math.min(i + 6, 7); k++) {
        for (int j = Math.max(i + 3, k + 1); j < Math.min(k + 6, 8); j++) {
          this.pieces[tile++] = new Tile(0, new int[]{i, k, j});
        } // for
      } // for
    } // for
    Tile temp;
    int shuffleIndex;
    for (int l = 1; l < pieces.length; l++) {
      shuffleIndex = seed.nextInt(dim() * dim() - l);
      temp = this.pieces[shuffleIndex + l];
      this.pieces[shuffleIndex + l] = this.pieces[l];
      this.pieces[l] = temp;
      if((l % 2) == 1) {
        this.pieces[l].flip();
      } // if
    } // for [l]
  } // tileGroup()

  /**
   * Show the users the next piece.
   */
  public void preview() {
    Tile toShow = this.pieces[this.count];
    // Don't know IO.
  }

  /**
   * Set the current tile at (x,y) and set its rotation to r.
   * @param x
   *   The x coordinate to set the tile at.
   * @param y
   *   The y coordinate to set the tile at.
   * @param r
   *   The rotation to set the tile to.
   */
  public void set(int x, int y, int r) {
    this.pieces[count].rotate(r);
    this.gameBoard.set(x, y, this.pieces[this.count]);
  } // set

  /**
   * Flip the tile at (x,y) and set its rotation to r
   * @param x
   *   The x coordinate of the tile to set.
   * @param y
   *   The y coordinate of the tile to set.
   * @param r
   *   The rotation to set the tile to.
   */
  public void flip(int x, int y, int r) {
    this.gameBoard.get(x,y).rotate(r);
    this.gameBoard.get(x,y).flip();
  } // flip

  /**
   * Returns if any tiles can be flipped by the tile at (x,y).
   * @param x
   *   The x coordinate of the tile to check.
   * @param y
   *   The y coordinate of the tile to check.
   * @return
   *   If any tiles can be flipped by the tile at (x,y).
   */
  public boolean canFlipSomething(int x, int y) {
    return (allCanFlip(x, y).length > 0);
  } // canFlipSomething(int, int)

  /**
   * Returns an array of all the tiles the tile at (x,y) can flip.
   * @param x
   *   The x coordinate of the tile to check.
   * @param y
   *   The y coordinate of the tile to check.
   * @return
   *   All of the tiles that can be flipped by the tile at (x,y).
   */
  public int[] allCanFlip(int x, int y) {
    int[] output = new int[0];
    for (int i = 0; i < this.pieces.length; i++) {
      if(this.gameBoard.get(x,y).canFlip(this.gameBoard.get(i % dim(),i / dim()), x, y, i % dim(), i / dim())) {
        output = Arrays.copyOf(output, output.length + 1);
        output[output.length - 1] = i;
      }
    } // for [i]
    return output;
  } // allCanFlip(int, int)

  /**
   * Returns an array of all the locations a new tile can be placed at.
   * @return
   *   All of the locations that a tile can be placed at.
   */
  public int[] allCanPlay() {
    int[] deltas = new int[]{-7,-1,1,7};
    int[] output = new int[0];
    for (int i = 0; i < this.pieces.length; i++) {
      if (this.pieces[i] == null) {
        for (int j = 0; j < deltas.length; j++) {
          if((i + deltas[j] >= 0) && (i + deltas[j] < this.pieces.length) && (this.pieces[i + deltas[j]] != null)) {
            output = Arrays.copyOf(output, output.length + 1);
            output[output.length - 1] = i;
          } // if
        } // for [j]
      } // if
    } // for [i]
    return output;
  } // allCanPlay()

  /**
   * Advance to the next piece.
   * @return
   *   If the game is over.
   */
  public boolean nextPiece() {
    this.count++;
    return (count > this.pieces.length);
  } // nextPiece()

  /**
   * Return the winner of the game.
   * @return
   *   The winner of the game. False=P1, True=P2.
   */
  public boolean getWinner() {
    int points = 0;
    for (int i = 0; i < this.pieces.length; i++) {
      if (this.gameBoard.get(i % dim(),i / dim()).getOwner()) {
        points++;
      } // if
    } // for [i]
    return (points > (dim() * dim() / 2));
  } // getWinner()

  private static int dim() {
    return DIM;
  } // dim()

} // class Board
