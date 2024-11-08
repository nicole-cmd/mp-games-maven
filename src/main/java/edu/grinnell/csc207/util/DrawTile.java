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

  /**
   * Inner edge length.
   */
  private static final int IN_L = 6;

  /**
   * Inner edge height.
   */
  private static final int IN_H = 2;

	/**
	 * Red color indicator.
	 */
	private static final String RED = "R";

	/**
	 * Blue color indicator.
	 */
	private static final String BLUE = "R";
  
	// +--------+-----------------------------------------------------
  // | Fields |
  // +--------+

	/** Contents of the tile, including color indicator and center arrow.
	 * 	P1 will be RED and P2 will be BLUE.
	 */
	boolean color; 

	/** Arrows that point away from the tile that will surround the tile. */
	int[] outerArrows;

	/** Arrow at the center of the tile. */
	int centerArrow;

	// +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

	/**
	 * Draw the next tile to be placed.
	 * @param t
	 * 	The tile we derive information from.
	 */
	public DrawTile(Tile t) {
		this.color = t.getOwner();
		this.outerArrows = t.getOuterDirs();
		this.centerArrow = t.getDir();

    // new Boxed();
	} // DrawTile(Tile)

	// +----------------+-----------------------------------------------------
  // | Helper Methods |
  // +----------------+

	/**
	 * Prints one instance of an outer arrow.
	 * @param index
	 * 	The index of the arrow we want. 
	 * @return a string instance of an outer arrow.
	 */
	// private String showArrow(int index) {
	// 	String arrow = "";

	// 	switch(index) {
	// 		case 0:
	// 			arrow = UP;
	// 			return SPACE.repeat(this.height() / 2) + arrow + SPACE.repeat(this.height() / 2);
	// 		case 1:
	// 			arrow = LD_DASH;
	// 			return SPACE.repeat(LENGTH + 1) + arrow;
	// 		case 2:
	// 			arrow = RIGHT;
	// 			return SPACE.repeat(this.height() - 1) + arrow;
	// 		case 3:
	// 			arrow = RD_DASH;
	// 			return SPACE.repeat(LENGTH + 1) + arrow;
	// 		case 4:
	// 			arrow = DOWN;
	// 			return SPACE.repeat(this.height() / 2) + arrow + SPACE.repeat(this.height() / 2);
	// 		case 5:
	// 			arrow = LD_DASH;
	// 			return arrow + SPACE.repeat(LENGTH + 1);
	// 		case 6:
	// 			arrow = LEFT;
	// 			return arrow + SPACE.repeat(this.height() - 1);
	// 		case 7:
	// 			arrow = RD_DASH;
	// 			return arrow + SPACE.repeat(LENGTH + 1);
	// 	} // switch

	// 	return arrow;
	// } // showArrow(int)

	/**
	 * Prints the row of the tile that includes its color indicator.
	 * 
	 * @return a tile row that shows its color.
	 */
	// private String showColor() {
	// 	if (this.color == false) {
	// 		return LD_DASH + RED + SPACE.repeat(this.height() - 2) + RD_DASH;
	// 	} // if
		
	// 	return LD_DASH + BLUE + SPACE.repeat(this.height() - 2) + RD_DASH;
	// } // showColor()

  // +--------------------+-----------------------------------------------------
  // | AsciiBlock Methods |
  // +--------------------+

	/**
	 * Get one row from the tile block.
	 * 
	 * @param i
	 * 	The specific row we want to see.
	 * @return ith row
	 * @exception Exception
	 * 	If the row is invalid.
	 */
	public String row(int i) throws Exception {
    String str = "";

    // edges of tile
    if (i == 0 || i == IN_H * 2) {
      new Line("------------"); 
    } else if (i > 1 && i < IN_H) {
      
    }


    return str;

		// if(i == 0 || i == this.height() && this.outerArrows[i] <= this.outerArrows.length) {
		// 	return showArrow(i);
		// } else if (i == 1 || i == this.height() - 2) {
		// 	return H_DASH.repeat(LENGTH);
		// } else if (i == 2 || i == this.height() - 3) {
		// 	this.showColor();
		// } else if (i == LENGTH % 2) {
		// 	return showArrow(this.centerArrow);
		// } else {
		// 	return V_DASH + SPACE.repeat(this.height() - 2) + V_DASH;
		// } // if/else

		// throw new Exception("Unable to print tile.");
	} // row(int)

	/**
   * Determine how many rows/columns are in the block.
   *
   * @return the number of rows/columns
   */
	public int height() {
		// return (int) (LENGTH * (2 * Math.tan(ANGLE)));
	} // height()

  /**
   * Determine how many rows/columns are in the block.
   *
   * @return the number of rows/columns
   */
	public int width() {
		// return (int) (LENGTH * (2 * Math.tan(ANGLE)));
	} // height()

} // class DrawTile
