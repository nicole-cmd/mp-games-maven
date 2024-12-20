package edu.grinnell.csc207.sayu;

/**
 * An object that represents a tile in Sayu.
 * Includes methods to flip, rotate, and get qualities.
 *
 * @author Cade Johnston
 * @author Nicole Gorrell
 */
public class Tile {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /** The direction of the white arrow in the center of the piece. */
  private int baseDir;

  /** The direction of the color arrows on the edges of the piece. */
  private int[] outerDir;

  /** Who has contol of the current piece: P1=false, P2=true. */
  private boolean owner;

  /** The change in x expected with direction 'index'. */
  private static final int[] DELTASX = new int[]{0, 1, 1, 1, 0, -1, -1, -1};

  /** The change in y expected with direction 'index'. */
  private static final int[] DELTASY = new int[]{1, 1, 0, -1, -1, -1, 0, 1};

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a Tile object.
   *
   * @param base
   *  The direction of the white arrow of the tile.
   * @param outer
   *  The directions of the color arrows of the tile.
   */
  public Tile(int base, int[] outer) {
    this.baseDir = base;
    this.outerDir = outer;
    this.owner = false;
  } // Tile(int, int[])

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Determines if we can flip the tile adjacent to that we placed.
   *
   * @param other
   *  The tile we want to flip.
   * @param thisX
   *  The x-coordinate of the tile we placed.
   * @param thisY
   *  The y-coordinate of the tile we placed.
   * @param otherX
   *  The x-coordinate of the tile we want to flip.
   * @param otherY
   *  The y-coordinate of the tile we want to flip.
   * @return a boolean to determine whether we can flip the desired tile.
   */
  public boolean canFlip(Tile other, int thisX, int thisY, int otherX, int otherY) {
    if (other == null) {
      return false;
    } else if (this.owner == other.getOwner()) {
      return false;
    } else if (this.baseDir == -1) {
      return false;
    } else if (this.baseDir == other.getDir()) {
      return false;
    } // if / else if / else if
    for (int k = 0; k < 8; k++) {
      if ((otherX - thisX == DELTASX[k]) && (thisY - otherY == DELTASY[k])) {
        for (int i = 0; i < this.getOuterDirs().length; i++) {
          if (this.getOuterDirs()[i] == k) {
            for (int j = 0; j < other.getOuterDirs().length; j++) {
              if ((other.getOuterDirs()[j] + 4) % 8 == k) {
                return false;
              } // if
            } // for [j]
            return true;
          } // if
        } // for [i]
        return false;
      } // for [i]
    } // for [k]
    return false;
  } // canFlip(Tile, int, int, int, int)

  /**
   * Rotate this Sayu tile.
   *
   * @param amount
   *  The direction to set the white arrow of this tile to.
   */
  public void rotate(int amount) {
    if (this.baseDir != -1) {
      for (int i = 0; i < this.outerDir.length; i++) {
        this.outerDir[i] = (this.outerDir[i] + amount - this.baseDir + 8) % 8;
      } // for [i]
      this.baseDir = amount;
    } // if
  } // rotate(int)

  /**
   * Return the owner of this Sayu tile.
   *
   * @return the owner of this Sayu tile
   */
  public boolean getOwner() {
    return this.owner;
  } // getOwner()

  /**
   * Change the owner of this Sayu tile.
   */
  public void flip() {
    this.owner = !(this.owner);
  } // flip()

  /**
   * Gets an array representing the white arrow of this Sayu tile.
   *
   * @return
   *  An int representing the white arrow of this Sayu tile.
   */
  public int getDir() {
    return this.baseDir;
  } // getDir()

  /**
   * Gets an array representing the color arrows of this Sayu tile.
   * @return
   *  An int array representing the color arrows of this Sayu tile.
   */
  public int[] getOuterDirs() {
    return this.outerDir;
  } // getOuterDirs()

} // Tile
