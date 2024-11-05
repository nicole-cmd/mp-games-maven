package edu.grinnell.csc207.util;

public class Tile {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /** The direction of the arrow in the center of the piece. */
  private int baseDir;

  /** The direction of the arrows on the edges of the piece. */
  private int[] outerDir;

  /** Who has contol of the current piece. P1=false, P2=true. */
  private boolean owner;

  /** The change in x expected with direction 'index' */
  private static final int[] DELTASX = new int[]{0,1,1,1,0,-1,-1,-1};

  /** The change in y expected with direction 'index' */
  private static final int[] DELTASY = new int[]{1,1,0,-1,-1,-1,0,1};

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

   public Tile(int base, int[] outer) {
    baseDir = base;
    outerDir = outer;
    owner = false;
   } // Tile(int,int[])

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  public boolean canFlip(Tile other, int thisX, int thisY, int otherX, int otherY) {
    if (this.owner == other.owner) {
      return false;
    } else if (this.baseDir == -1) {
      return false;
    } else if (this.baseDir == other.baseDir) {
      return false;
    } // if / else if / else if
    for (int k = 0; k < 8; k++) {
      if((thisX - otherX == DELTASX[k]) && (thisY - otherY == DELTASY[k])) {
        for (int i = 0; i < this.outerDir.length; i++) {
          if (this.outerDir[i] == k) {
            for (int j = 0; j < other.outerDir.length; j++) {
              if ((other.outerDir[j] + 4) % 8 == k) {
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

  public void rotate(int amount) {
    if (this.baseDir != -1) {
      for (int i = 0; i < this.outerDir.length; i++) {
        this.outerDir[i] = (this.outerDir[i] + amount - this.baseDir + 8) % 8;
      } // for [i]
      this.baseDir = amount ;
    } // if
  } // rotate(int)

  public void flip() {
    this.owner = !(this.owner);
  } // flip()

} // Tile
