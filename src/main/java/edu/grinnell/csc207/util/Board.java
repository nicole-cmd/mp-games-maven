package edu.grinnell.csc207.util;

public class Board {
  // +--------+-------------------------------------------------------
  // | Fields |
  // +--------+

  /** The amount of tiles on the board. */
  private int count;

  /** A piece that gets placed onto the board. */
  private Tile[] pieces;

  /** The board we play (place pieces) on. */
  private MatrixV0 gameBoard;

  // +--------------+-------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Construct the game board.
   * 
   * @post void
   *  Produces a game board to play on. 
   */
  public void makeBoard() {
    this.count = 0;
    this.pieces = new Tile[49];
    this.gameBoard = new MatrixV0<>(7 , 7);
  } // makeBoard()

  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Generate all possible tiles.
   * 
   * @pre 
   * 
   * @post void
   *  Assigns all possible tile pieces to this.pieces. Allows for players to pull from this group to use a piece. 
   */
  public void 

} // class Board
