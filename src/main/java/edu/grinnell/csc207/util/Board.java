package edu.grinnell.csc207.util;

import java.util.Arrays;
import java.util.Random;

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
        for (int j = Math.max(i + 3, k + 1); j < Math.min(i + 7, 8); j++) {
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
      }
    } // for [l]
  } // tileGroup()

  /**
   * Show the users the next piece
   */
  public void preview() {
    Tile toShow = this.pieces[this.count];
    // Don't know IO.
  }

  public void set(int x, int y, int r) {
    this.pieces[count].rotate(r);
    this.gameBoard.set(x, y, this.pieces[this.count]);
  } // set

  public void flip(int x, int y, int r) {
    this.gameBoard.get(x,y).rotate(r);
    this.gameBoard.get(x,y).flip();
  } // flip

  public boolean canFlipSomething(int x, int y) {
    for (int i = 0; i < this.pieces.length; i++) {
      if(this.gameBoard.get(x,y).canFlip(this.gameBoard.get(i % dim(),i / dim()), x, y, i % dim(), i / dim())) {
        return true;
      }
    } // for [i]
    return false;
  }

  public int[] allCanFlip(int x, int y) {
    int[] output = new int[0];
    for (int i = 0; i < this.pieces.length; i++) {
      if(this.gameBoard.get(x,y).canFlip(this.gameBoard.get(i % dim(),i / dim()), x, y, i % dim(), i / dim())) {
        output = Arrays.copyOf(output, output.length + 1);
        output[output.length - 1] = i;
      }
    } // for [i]
    return output;
  }

  public boolean nextPiece() {
    this.count++;
    return (count > this.pieces.length);
  }

  private static int dim() {
    return DIM;
  }

} // class Board
