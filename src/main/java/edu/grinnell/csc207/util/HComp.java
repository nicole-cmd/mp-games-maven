package edu.grinnell.csc207.util;

import java.util.Arrays;

/**
 * The horizontal composition of blocks.
 *
 * @author Samuel A. Rebelsky
 * @author Nicole Gorrell
 * @author Harrison Zhu
 */
public class HComp implements AsciiBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /** The blocks. */
  AsciiBlock[] blocks;

  /** How the blocks are aligned. */
  VAlignment align;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a horizontal composition of two blocks.
   *
   * @param alignment The way in which the blocks should be aligned.
   * @param leftBlock The block on the left.
   * @param rightBlock The block on the right.
   */
  public HComp(VAlignment alignment, AsciiBlock leftBlock, AsciiBlock rightBlock) {
    this.align = alignment;
    this.blocks = new AsciiBlock[] {leftBlock, rightBlock};
  } // HComp(VAlignment, AsciiBlock, AsciiBlock)

  /**
   * Build a horizontal composition of multiple blocks.
   *
   * @param alignment The alignment of the blocks.
   * @param blocksToCompose The blocks we will be composing.
   */
  public HComp(VAlignment alignment, AsciiBlock[] blocksToCompose) {
    this.align = alignment;
    this.blocks = Arrays.copyOf(blocksToCompose, blocksToCompose.length);
  } // HComp(Alignment, AsciiBLOCK[])

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Get one row from the block.
   *
   * @param i the number of the row
   * @return row i.
   * @exception Exception if i is outside the range of valid rows.
   */
  public String row(int i) throws Exception {
    String str = "";
    for (int r = 0; r < this.blocks.length; r++) {
      Padded spaced =
          new Padded(
              this.blocks[r],
              ' ',
              HAlignment.RIGHT,
              this.align,
              this.blocks[r].width(),
              this.height());
      str = str.concat(spaced.row(i));
    } // for each block

    return str;
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    int max = 0;

    for (int i = 0; i < this.blocks.length; i++) {
      if (this.blocks[i].height() > max) {
        max = this.blocks[i].height();
      } // if new max
    } // for

    return max;
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    int sum = 0;

    for (int i = 0; i < this.blocks.length; i++) {
      sum += this.blocks[i].width();
    } // for

    return sum;
  } // width()

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
} // class HComp
