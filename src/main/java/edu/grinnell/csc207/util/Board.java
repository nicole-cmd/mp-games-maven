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
  private MatrixV0<Tile> gameBoard;

  // +--------------+-------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Construct the game board.
   */
  public Board() {
    this.count = 1;
    this.pieces = new Tile[49];
    this.tileGroup();
    this.gameBoard = new MatrixV0<Tile>(7 , 7);
    this.gameBoard.set(3, 3, this.pieces[0]); // first game piece
  } // makeBoard()

  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Generate all possible tiles. Allows for players to pull from this group to use a piece. 
   */
  private void tileGroup() {
    int tile = 1; // keep track of the tile we are on
    this.pieces[0] = new Tile(-1, new int[0]);

    for(int i = 0; i < 5; i++) {
      for(int k = i + 1; k < Math.min(i + 6, 7); k++) {
        for(int j = Math.max(i + 3, k + 1); j < Math.min(i + 7, 8); j++) {
          this.pieces[tile++] = new Tile(0, new int[] {i, k, j});
        } // for
      } // for
    } // for
  } // tileGroup()


} // class Board
