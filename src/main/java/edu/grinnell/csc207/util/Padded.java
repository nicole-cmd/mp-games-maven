package edu.grinnell.csc207.util;

/**
 * A padded ASCII block.
 *
 * @author Samuel A. Rebelsky
 * @author Harrison Zhu
 */
public class Padded implements AsciiBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /** The original block. */
  AsciiBlock block;

  /** The character used for padding. */
  String pad;

  /** How is the original block horizontally aligned within the padding? */
  HAlignment halign;

  /** How is the original block vertically aligned within the padding? */
  VAlignment valign;

  /** How wide is the padded block? */
  int width;

  /** How tall is the padded block. */
  int height;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new block with the specified contents.
   *
   * @param original The original block.
   * @param ch The character we use for padding.
   * @param horiz How the original block is horizontally aligned within the padding.
   * @param vert How the original block is vertically aligned within the padding.
   * @param paddedWidth The width of the padded block.
   * @param paddedHeight The height of the padded block.
   */
  public Padded(
      AsciiBlock original,
      char ch,
      HAlignment horiz,
      VAlignment vert,
      int paddedWidth,
      int paddedHeight) {
    this.block = original;
    this.pad = new String(new char[] {ch});
    this.halign = horiz;
    this.valign = vert;
    this.width = paddedWidth < this.block.width() ? this.block.width() : paddedWidth;
    this.height = paddedHeight < this.block.height() ? this.block.height() : paddedHeight;
  } // Padded(AsciiBlock, char, HAlignment, VAlignment, int, int)

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Get one row from the block.
   *
   * @param i the number of the row
   * @return row i.
   * @exception Exception If the row is invalid.
   */
  public String row(int i) throws Exception {

    int left = 0;
    if (this.halign == HAlignment.CENTER) {
      left = (this.width() - this.block.width()) / 2;
    } else if (this.halign == HAlignment.RIGHT) {
      left = this.width() - this.block.width();
    } // Horizontal offset
    int right = this.width() - left - this.block.width();

    int top = 0;
    if (this.valign == VAlignment.CENTER) {
      top = (this.height() - this.block.height()) / 2;
    } else if (this.valign == VAlignment.BOTTOM) {
      top = this.height() - this.block.height();
    } // Vertical offset

    if (i < top) {
      return this.pad.repeat(this.width());
    } else if (top <= i && i < top + this.block.height()) {
      return this.pad.repeat(left) + this.block.row(i - top) + this.pad.repeat(right);
    } else {
      return this.pad.repeat(this.width());
    } // if
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    return this.height;
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    return this.width;
  } // width()
} // class Padded
