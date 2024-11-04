package edu.grinnell.csc207.util;

public class Tile {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /** The direction of the arrow in the center of the piece. */
  private int baseDir;

  /** The direction of the arrows on the edges of the piece. */
  private int[] outerDir;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

   public Tile(int base, int[] outer) {
    baseDir = base;
    outerDir = outer;
   } // Tile(int,int[])

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  public boolean canFilp(Tile other, int thisX, int thisY, int otherX, int otherY) {
    if (this.baseDir == -1) {
      return false;
    } else if (this.baseDir == other.baseDir) {
      return false;
    } // if / else if
    for (int i = 0; i < this.outerDir.length; i++) {
      for (int j = 0; j < other.outerDir.length; j++) {
        if (this.outerDir[i] == other.outerDir[j]) {
          return false;
        } // if
      } // for [j]
    } // for [i]
    return true;
  } // canFlip(Tile, int, int, int, int)

} // Tile
