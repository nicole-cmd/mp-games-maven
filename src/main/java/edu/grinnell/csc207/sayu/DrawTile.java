package edu.grinnell.csc207.util;

/**
 * Draws the next tile to be played in the shape of an octagon with
 * surrounding arrows, the positions of which vary. Made using
 * AsciiBlocks.
 *
 * @author Nicole Gorrell
 * @author Cade Johnston
 */
public class DrawTile implements AsciiBlock {
  // +-----------+------------------------------------------------
  // | Constants |
  // +-----------+

  /** Inner edge length. */
  private static final int IN_L = 6;

  /** Inner edge height. */
  private static final int IN_H = 2;

  /** Red color indicator. */
  private static final String RED = "R ";

  /** Blue color indicator. */
  private static final String BLUE = "B ";

  // +--------+-----------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * Contents of the tile, including color indicator and center
   * arrow. P1 will be RED and P2 will be BLUE.
   */
  boolean color;

  /** Arrows that point away from the tile. */
  int[] outerArrows;

  /** Arrow at the center of the tile. */
  int centerArrow;

  /** Our tile AsciiBlock. */
  AsciiBlock element;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Draw the next tile to be placed.
   *
   * @param t
   *   The tile we derive visual information from.
   */
  public DrawTile(Tile t) {
    if (t == null) {
      this.element = new Boxed(new Padded(new Line(" "), ' ', HAlignment.CENTER, VAlignment.BOTTOM,
          IN_L, IN_H));
    } else {
      this.color = t.getOwner();
      this.outerArrows = t.getOuterDirs();
      this.centerArrow = t.getDir();
      if (this.outerArrows.length == 0) {
        AsciiBlock left = new VComp(HAlignment.LEFT, new AsciiBlock[]{colorBlock(),
            new Line("  ")});
        AsciiBlock center = new Line("  ");
        AsciiBlock right = new VComp(HAlignment.LEFT, new AsciiBlock[]{new Line("  "),
            new Line("  ")});
        this.element = new Boxed(new HComp(VAlignment.BOTTOM,
            new AsciiBlock[]{left, center, right}));
      } else {
        AsciiBlock left = new VComp(HAlignment.LEFT, new AsciiBlock[]{colorBlock(),
            dirBlock(outerArrows[0])});
        AsciiBlock center = dirBlock(outerArrows[1]);
        AsciiBlock right = new VComp(HAlignment.LEFT, new AsciiBlock[]{dirBlock(this.centerArrow),
            dirBlock(outerArrows[2])});
        this.element = new Boxed(new HComp(VAlignment.BOTTOM,
            new AsciiBlock[]{left, center, right}));
      } // if / else
    } // if / else
  } // DrawTile(Tile)

  // +----------------+-----------------------------------------------------
  // | Helper Methods |
  // +----------------+

  /**
   * Derive the color of the tile.
   *
   * @return a Line block with the corresponding tile color
   */
  private Line colorBlock() {
    if (this.color) {
      return new Line(RED);
    } else {
      return new Line(BLUE);
    } // if / else
  } // colorBlock()

  /**
   * Translate an outer arrow's direction into alphabetical form
   * (i.e. "N, E, S, W").
   *
   * @param value
   *  The numeric indication of an arrow's direction.
   * @return a Line block with the arrow's corresponding direction
   */
  private Line dirBlock(int value) {
    switch (value) {
      case 0:
        return new Line("N ");
      case 1:
        return new Line("NE");
      case 2:
        return new Line("E ");
      case 3:
        return new Line("SE");
      case 4:
        return new Line("S ");
      case 5:
        return new Line("SW");
      case 6:
        return new Line("W ");
      case 7:
        return new Line("NW");
      default:
        return new Line("  ");
    } // switch
  } // colorBlock()

  // +--------------------+-----------------------------------------------------
  // | AsciiBlock Methods |
  // +--------------------+

  /**
   * Get one row from the tile block.
   *
   * @param i
   *   The specific row we want to see.
   * @return ith row
   * @exception Exception
   *   If the row is invalid.
   */
  public String row(int i) throws Exception {
    return element.row(i);
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    return IN_H + 2;
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    return IN_L + 2;
  } // width()

  /**
   * Convert the tile block into String format.
   *
   * @return a String representation of the tile.
   */
  public String toString() {
    String output = "";
    for (int i = 0; i < height(); i++) {
      try {
        output += row(i) + "\n";
      } catch (Exception e) {
        return "";
      } // try / catch
    } // for
    return output;
  } // toString()
} // class DrawTile
