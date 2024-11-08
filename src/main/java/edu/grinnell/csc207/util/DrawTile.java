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
	private static final String RED = "R ";

	/**
	 * Blue color indicator.
	 */
	private static final String BLUE = "B ";
  
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

	AsciiBlock element;

	// +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

	/**
	 * Draw the next tile to be placed.
	 * @param t
	 * 	The tile we derive information from.
	 */
	public DrawTile(Tile t) {
		if (t == null) {
			this.element = new Boxed(new Padded(new Line(" "), ' ', HAlignment.CENTER, VAlignment.BOTTOM, IN_L, IN_H));
		} else {
			this.color = t.getOwner();
			this.outerArrows = t.getOuterDirs();
			this.centerArrow = t.getDir();
			if (this.outerArrows.length == 0) {
				AsciiBlock left = new VComp(HAlignment.LEFT, new AsciiBlock[]{colorBlock(), new Line("  ")});
				AsciiBlock center = new Line("  ");
				AsciiBlock right = new VComp(HAlignment.LEFT, new AsciiBlock[]{new Line("  "),new Line("  ")});
				this.element = new Boxed(new HComp(VAlignment.BOTTOM,
				new AsciiBlock[]{left, center, right}));
			} else {
				AsciiBlock left = new VComp(HAlignment.LEFT, new AsciiBlock[]{colorBlock(), dirBlock(outerArrows[0])});
				AsciiBlock center = dirBlock(outerArrows[1]);
				AsciiBlock right = new VComp(HAlignment.LEFT, new AsciiBlock[]{dirBlock(this.centerArrow), dirBlock(outerArrows[2])});
				this.element = new Boxed(new HComp(VAlignment.BOTTOM,
				new AsciiBlock[]{left, center, right}));
			}
		}
	} // DrawTile(Tile)

	private Line colorBlock() {
    if (this.color) {
			return new Line(RED);
		} else {
			return new Line(BLUE);
		} // if / else
	} // colorBlock()

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
		}
	}

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
		return element.row(i);

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
		return IN_H + 2;
	} // height()

  /**
   * Determine how many rows/columns are in the block.
   *
   * @return the number of rows/columns
   */
	public int width() {
		return IN_L + 2;
	} // height()

	public String toString() {
    String output = "";
    for(int i = 0; i < height(); i++) {
      try {
      output += row(i) + "\n";
      } catch (Exception e) {
        return "";
      } // try / catch
    } // for
    return output;
  } // toString()
} // class DrawTile
