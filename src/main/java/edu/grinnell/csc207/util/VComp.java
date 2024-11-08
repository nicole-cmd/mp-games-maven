package edu.grinnell.csc207.util;

import java.util.Arrays;

/**
 * The vertical composition of blocks.
 *
 * @author Samuel A. Rebelsky
 * @author Harrison Zhu, Tiffany Tang
 */
public class VComp implements AsciiBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /** The blocks. */
  AsciiBlock[] blocks;

  /** How the blocks are aligned. */
  HAlignment align;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a vertical composition of two blocks.
   *
   * @param alignment The way in which the blocks should be aligned.
   * @param topBlock The block on the top.
   * @param bottomBlock The block on the bottom.
   */
  public VComp(HAlignment alignment, AsciiBlock topBlock, AsciiBlock bottomBlock) {
    this.align = alignment;
    this.blocks = new AsciiBlock[] {topBlock, bottomBlock};
  } // VComp(HAlignment, AsciiBlock, AsciiBlock)

  /**
   * Build a vertical composition of multiple blocks.
   *
   * @param alignment The alignment of the blocks.
   * @param blocksToCompose The blocks we will be composing.
   */
  public VComp(HAlignment alignment, AsciiBlock[] blocksToCompose) {
    this.align = alignment;
    this.blocks = Arrays.copyOf(blocksToCompose, blocksToCompose.length);
  } // VComp(HAlignment, AsciiBLOCK[])

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
    int index = 0;
    int maxRow = this.blocks[index].height() - 1;
    while (i > maxRow) {
      maxRow += this.blocks[++index].height();
    } // while

    Padded helper =
        new Padded(
            this.blocks[index],
            ' ',
            this.align,
            VAlignment.TOP,
            this.width(),
            this.blocks[index].height());

    return helper.row(i - maxRow + this.blocks[index].height() - 1);
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    int result = 0;
    for (AsciiBlock element : this.blocks) {
      result += element.height();
    } // for
    return result;
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    int maxWidth = 0;
    for (AsciiBlock element : this.blocks) {
      if (element.width() > maxWidth) {
        maxWidth = element.width();
      } // if
    } // for
    return maxWidth;
  } // width()
} // class VComp
