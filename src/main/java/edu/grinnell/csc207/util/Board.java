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

  /** Remaining pieces to place on the board. */
  private Tile[] pieces;

  /** The board we play (place pieces) on. */
  private MatrixV0<Tile> gameBoard;

  /** The rotation of the next tile */
  private int rotation;

  /** What stage the game is in. False: Play, True: Flip */
  private boolean stage;

  /** The dimensions of the board. */
  private static final int DIM = 7;

  /** The 'current' piece. */
  private int currentP;

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
    this.rotation = 0;
    this.stage = false;
    this.gameBoard = new MatrixV0<Tile>(dim(), dim());
    this.currentP = 24;
    this.gameBoard.set(3, 3, this.pieces[0]); // first game piece
  } // makeBoard()

  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+

  public boolean notDone() {
    return (this.count < 49);
  }

  public boolean stage() {
    return stage;
  }

  /**
   * Generate all possible tiles. Allows for players to pull from this group to use a piece. 
   */
  private void tileGroup(Random seed) {
    int tile = 1; // keep track of the tile we are on
    this.pieces[0] = new Tile(-1, new int[0]);
    this.pieces[0].flip();
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
      if((l % 2) == 0) {
        this.pieces[l].flip();
      } // if
    } // for [l]
  } // tileGroup()

  /**
   * Show the users the next piece.
   */
  public Tile preview() {
    return this.pieces[this.count++];
  } // preview()

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
      if(this.gameBoard.get(x, y).canFlip(this.gameBoard.get(i % dim(),i / dim()), x, y, i % dim(), i / dim())) {
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
      if (this.gameBoard.get(i % 7, i / 7) == null) {
        for (int j = 0; j < deltas.length; j++) {
          // System.out.println(i +", " + j + ": " + this.gameBoard.get((i + deltas[j]) % dim(), (i + deltas[j]) / dim()) == null);
          if((i + deltas[j] >= 0) && (i + deltas[j] < this.pieces.length) && (this.gameBoard.get((i + deltas[j]) % dim(), (i + deltas[j]) / dim()) != null)) {
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
    this.pieces[count].rotate(rotation);
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

  /**
   * Turns the gameboard into a string.
   *
   */
  public String toString() {
    String out = "";

    for(int i = 0; i < this.gameBoard.height(); i++) {
      AsciiBlock[] arr = new AsciiBlock[7];

      for(int k = 0; k < this.gameBoard.width(); k++) {
        arr[k] = new DrawTile(this.gameBoard.get(k, i));
      } // for
      AsciiBlock row = new HComp(VAlignment.CENTER, arr);
      out += row.toString();
      out += "\n";
    } // for
    out += "Next Piece Preview:\n";
    this.pieces[count].rotate(rotation);
    out += new DrawTile(this.pieces[count]);
    return out;
  } // toString()

  /**
   * 
   * @param action
   *   An integer representing which action to take
   * @param data
   *   An integer representing the source of the attempt.
   *   May represent other things based on context.
   * @return
   *   If the action was successful or not.
   *   May represent other things based on context.
   */
  public boolean attemptSelection(int type, int data) {
    if (type == 0) {
      // Action: Rotate
      // Button represents if the rotation is clockwise or counter.
      if (!(this.stage)) {
        if (data == 0) {
          this.rotation += 1;
        } else {
          this.rotation -= 1;
        }
        this.rotation = (this.rotation + 8) % 8;
        return true;
      } else {
        return false;
      }
    } else if (type == 1) {
      if (!(this.stage)) {
        // Action: Place
        int[] canPlay = allCanPlay();
        System.out.println(canPlay.length);
        for (int i = 0; i < canPlay.length; i++) {
          if (data == canPlay[i]) {
            this.gameBoard.set(data % 7, data / 7, this.pieces[count]);
            this.stage = true;
            this.currentP = data;
            return true;
          } // if
        } // for [i]
      } // if / else
      return false;
    } else if (type == 2){
      if (this.stage) {
        // Action: Flip
        int[] canFlip = allCanFlip(this.currentP % 7, this.currentP / 7);
        for (int i = 0; i < canFlip.length; i++) {
          if (data == canFlip[i]) {
            this.gameBoard.get(data % 7, data / 7).flip();
            this.gameBoard.get(data % 7, data / 7).rotate(rotation);
            this.currentP = data;
            return true;
          } // if
        } // for [i]
      } // if
      return false;
    } else if (type == 3) {
      // Action: End Turn
      if (this.stage) {
        this.stage = false;
        return true;
      } // if
      return false;
    } // if / else if
    return false;
  } // attemptSelection(int, int);
} // class Board
