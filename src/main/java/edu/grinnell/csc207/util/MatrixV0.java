package edu.grinnell.csc207.util;

import java.util.Arrays;

/**
 * An implementation of two-dimensional matrices.
 *
 * @author Your Name Here
 * @author Samuel A. Rebelsky
 *
 * @param <T>
 *   The type of values stored in the matrix.
 */
public class MatrixV0<T> implements Matrix<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /** The contents of this matrix. */
  T[][] contents;

  /** The height of this matrix. */
  int h;

  /** The width of this matrix. */
  int w;

  /** The default value of this matrix. */
  T defOut;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new matrix of the specified width and height with the
   * given value as the default.
   *
   * @param width
   *   The width of the matrix.
   * @param height
   *   The height of the matrix.
   * @param def
   *   The default value, used to fill all the cells.
   *
   * @throws NegativeArraySizeException
   *   If either the width or height are negative.
   */
  @SuppressWarnings({ "unchecked" })
  public MatrixV0(int width, int height, T def) {
    this.w = width;
    this.h = height;
    if (width() < 0) {
      throw new NegativeArraySizeException("Width " + width() + "is negative.");
    } // if
    if (height() < 0) {
      throw new NegativeArraySizeException("Height " + height() + "is negative.");
    } // if
    this.defOut = def;
    this.contents = (T[][]) new Object[height()][];
    for (int i = 0; i < height(); i++) {
      initializeRow(i);
    } // for [i]
  } // MatrixV0(int, int, T)

  /**
   * Create a new matrix of the specified width and height with
   * null as the default value.
   *
   * @param width
   *   The width of the matrix.
   * @param height
   *   The height of the matrix.
   *
   * @throws NegativeArraySizeException
   *   If either the width or height are negative.
   */
  public MatrixV0(int width, int height) {
    this(width, height, null);
  } // MatrixV0

  // +--------------+------------------------------------------------
  // | Core methods |
  // +--------------+

  /**
   * Get the element at the given row and column.
   *
   * @param row
   *   The row of the element.
   * @param col
   *   The column of the element.
   *
   * @return the value at the specified location.
   *
   * @throws IndexOutOfBoundsException
   *   If either the row or column is out of reasonable bounds.
   */
  public T get(int row, int col) {
    if (isIndexException(row, col)) {
      return this.contents[row][col];
    } else {
      throw new IndexOutOfBoundsException("Indecies exceed bounds of array.");
    } // if / else
  } // get(int, int)

  /**
   * Set the element at the given row and column.
   *
   * @param row
   *   The row of the element.
   * @param col
   *   The column of the element.
   * @param val
   *   The value to set.
   *
   * @throws IndexOutOfBoundsException
   *   If either the row or column is out of reasonable bounds.
   */
  public void set(int row, int col, T val) {
    if (isIndexException(row, col)) {
      this.contents[row][col] = val;
    } else {
      throw new IndexOutOfBoundsException("Indecies exceed bounds of array.");
    } // if / else
  } // set(int, int, T)

  /**
   * Determine the number of rows in the matrix.
   *
   * @return the number of rows.
   */
  public int height() {
    return this.h;
  } // height()

  /**
   * Determine the number of columns in the matrix.
   *
   * @return the number of columns.
   */
  public int width() {
    return this.w;
  } // width()

  /**
   * Insert a row filled with the default value.
   *
   * @param row
   *   The number of the row to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than the height.
   */
  public void insertRow(int row) {
    if (isBound(row, 0, height() + 1)) {
      this.h++;
      this.contents = Arrays.copyOf(this.contents, height());
      initializeRow(height() - 1);
      bubbleRow(height() - 1, row);
    } else {
      throw new IndexOutOfBoundsException("Index " + row + " out of bounds for length " + height());
    } // if / else
  } // insertRow(int)

  /**
   * Insert a row filled with the specified values.
   *
   * @param row
   *   The number of the row to insert.
   * @param vals
   *   The values to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than the height.
   * @throws ArraySizeException
   *   If the size of vals is not the same as the width of the matrix.
   */
  public void insertRow(int row, T[] vals) throws ArraySizeException {
    if (vals.length == width()) {
      insertRow(row);
      for (int c = 0; c < width(); c++) {
        set(row, c, vals[c]);
      } // for [c]
    } else {
      throw new ArraySizeException("Length of values does not match width.");
    } // if / else
  } // insertRow(int, T[])

  /**
   * Insert a column filled with the default value.
   *
   * @param col
   *   The number of the column to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than the width.
   */
  public void insertCol(int col) {
    if (isBound(col, 0, width() + 1)) {
      this.w++;
      for (int r = 0; r < height(); r++) {
        this.contents[r] = Arrays.copyOf(this.contents[r], width());
      } // for [r]
      initializeCol(width() - 1);
      bubbleCol(width() - 1, col);
    } else {
      throw new IndexOutOfBoundsException("Index " + col + " out of bounds for length " + width());
    } // if / else
  } // insertCol(int)

  /**
   * Insert a column filled with the specified values.
   *
   * @param col
   *   The number of the column to insert.
   * @param vals
   *   The values to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than the width.
   * @throws ArraySizeException
   *   If the size of vals is not the same as the height of the matrix.
   */
  public void insertCol(int col, T[] vals) throws ArraySizeException {
    if (vals.length == height()) {
      insertCol(col);
      for (int r = 0; r < height(); r++) {
        set(r, col, vals[r]);
      } // for [r]
    } else {
      throw new ArraySizeException("Length of values does not match height.");
    } // if / else
  } // insertCol(int, T[])

  /**
   * Delete a row.
   *
   * @param row
   *   The number of the row to delete.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than or equal to the height.
   */
  public void deleteRow(int row) {
    if (isBound(row, 0, height())) {
      bubbleRow(row, height() - 1);
      this.h--;
      this.contents = Arrays.copyOf(this.contents, height());
    } else {
      throw new IndexOutOfBoundsException("Index " + row + " out of bounds for length " + height());
    } // if / else
  } // deleteRow(int)

  /**
   * Delete a column.
   *
   * @param col
   *   The number of the column to delete.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than or equal to the width.
   */
  public void deleteCol(int col) {
    if (isBound(col, 0, width())) {
      bubbleCol(col, width() - 1);
      this.w--;
      for (int r = 0; r < height(); r++) {
        this.contents[r] = Arrays.copyOf(this.contents[r], width());
      } // for [r]
    } else {
      throw new IndexOutOfBoundsException("Index " + col + " out of bounds for length " + width());
    } // if / else
  } // deleteCol(int)

  /**
   * Resets a row and fills it with the default value.
   *
   * @param row
   */
  @SuppressWarnings({ "unchecked" })
  private void initializeRow(int row) {
    this.contents[row] = (T[]) new Object[width()];
    fillLine(row, 0, 0, 1, height(), width(), this.defOut);
  } // initializeRow(int)

  /**
   * Move a row to destination, shifting all rows between.
   *
   * @param targetRow
   * @param destination
   */
  private void bubbleRow(int targetRow, int destination) {
    T[] tempRow;
    int delta;
    if (destination > targetRow) {
      delta = 1;
    } else {
      delta = -1;
    } // if / else
    for (int r = targetRow; r != destination; r += delta) {
      tempRow = this.contents[r];
      this.contents[r] = this.contents[r + delta];
      this.contents[r + delta] = tempRow;
    } // for [r]
  } // bubbleRow(int, int)

  /**
   * Resets a col and fills it with the default value.
   *
   * @param col
   */
  private void initializeCol(int col) {
    fillLine(0, col, 1, 0, height(), width(), this.defOut);
  } // initializeCol(int)

  /**
   * Move a col to destination, shifting all cols between.
   *
   * @param targetCol
   * @param destination
   */
  private void bubbleCol(int targetCol, int destination) {
    T tempVal;
    int delta;
    if (destination > targetCol) {
      delta = 1;
    } else {
      delta = -1;
    } // if / else
    for (int c = targetCol; c != destination; c += delta) {
      for (int r = 0; r < height(); r++) {
        tempVal = this.contents[r][c];
        this.contents[r][c] = this.contents[r][c + delta];
        this.contents[r][c + delta] = tempVal;
      } // for [r]
    } // for [c]
  } // bubbleCol(int, int)

  /**
   * Fill a rectangular region of the matrix.
   *
   * @param startRow
   *   The top edge / row to start with (inclusive).
   * @param startCol
   *   The left edge / column to start with (inclusive).
   * @param endRow
   *   The bottom edge / row to stop with (exclusive).
   * @param endCol
   *   The right edge / column to stop with (exclusive).
   * @param val
   *   The value to store.
   *
   * @throw IndexOutOfBoundsException
   *   If the rows or columns are inappropriate.
   */
  public void fillRegion(int startRow, int startCol, int endRow, int endCol,
      T val) {
    int deltaR;
    int deltaC;
    if (endRow > startRow) {
      deltaR = 1;
    } else {
      deltaR = -1;
    } // if / else
    if (endCol > startCol) {
      deltaC = 1;
    } else {
      deltaC = -1;
    } // if / else
    if (makeBoundsException(startRow, startCol, endRow, endCol)) {
      for (int r = startRow; r != endRow; r += deltaR) {
        fillLine(r, startCol, 0, deltaC, height(), endCol, val);
      } // for [r]
    } else {
      throw new IndexOutOfBoundsException("Indecies exceed bounds of array.");
    } // if / else
  } // fillRegion(int, int, int, int, T)

  /**
   * Fill a line (horizontal, vertical, diagonal).
   *
   * @param startRow
   *   The row to start with (inclusive).
   * @param startCol
   *   The column to start with (inclusive).
   * @param deltaRow
   *   How much to change the row in each step.
   * @param deltaCol
   *   How much to change the column in each step.
   * @param endRow
   *   The row to stop with (exclusive).
   * @param endCol
   *   The column to stop with (exclusive).
   * @param val
   *   The value to store.
   *
   * @throw IndexOutOfBoundsException
   *   If the rows or columns are inappropriate.
   */
  public void fillLine(int startRow, int startCol, int deltaRow, int deltaCol,
      int endRow, int endCol, T val) {
    int n = 0;
    if ((width() != 0) && (height() != 0)) {
      if (makeBoundsException(startRow, startCol, endRow, endCol)) {
        if ((deltaRow == 0) && (deltaCol == 0)) {
          set(startRow, startCol, val);
        } else {
          while (isBound(startRow + n * deltaRow, startRow, endRow)
                && isBound(startCol + n * deltaCol, startCol, endCol)) {
            set(startRow + n * deltaRow, startCol + n * deltaCol, val);
            n++;
          } // while
        } // if / else
      } else {
        throw new IndexOutOfBoundsException("Indecies exceed bounds of array.");
      } // if / else
    } // if
    // Do nothing if the if fails
  } // fillLine(int, int, int, int, int, int, T)

  /**
   * A make a copy of the matrix. May share references (e.g., if individual
   * elements are mutable, mutating them in one matrix may affect the other
   * matrix) or may not.
   *
   * @return a copy of the matrix.
   */
  public Matrix<T> clone() {
    MatrixV0<T> output = new MatrixV0<T>(width(), height(), this.defOut);
    for (int r = 0; r < height(); r++) {
      for (int c = 0; c < width(); c++) {
        output.set(r, c, get(r, c));
      } // for [c]
    } // for [r]
    return output;
  } // clone()

  /**
   * Determine if this object is equal to another object.
   *
   * @param other
   *   The object to compare.
   *
   * @return true if the other object is a matrix with the same width,
   * height, and equal elements; false otherwise.
   */
  @SuppressWarnings({ "unchecked" })
  public boolean equals(Object other) {
    if (other instanceof Matrix) {
      Matrix<Object> otherAs = (Matrix<Object>) other;
      if ((width() == otherAs.width()) && (height() == otherAs.height())) {
        for (int r = 0; r < height(); r++) {
          for (int c = 0; c < width(); c++) {
            if (!(this.get(r, c).equals(otherAs.get(r, c)))) {
              return false;
            } // if
          } // for [c]
        } // for [r]
        return true;
      } else {
        return false;
      } // if / else
    } else {
      return false;
    } // if / else
  } // equals(Object)

  /**
   * Compute a hash code for this matrix. Included because any object
   * that implements `equals` is expected to implement `hashCode` and
   * ensure that the hash codes for two equal objects are the same.
   *
   * @return the hash code.
   */
  public int hashCode() {
    int multiplier = 7;
    int code = this.width() + multiplier * this.height();
    for (int row = 0; row < this.height(); row++) {
      for (int col = 0; col < this.width(); col++) {
        T val = this.get(row, col);
        if (val != null) {
          // It's okay if the following computation overflows, since
          // it will overflow uniformly.
          code = code * multiplier + val.hashCode();
        } // if
      } // for col
    } // for row
    return code;
  } // hashCode()

  /**
   * Checks if a pair of coordinates makes a valid bounded area
   * within the matrix.
   *
   * @param startRow
   *   The inclusive row
   * @param startCol
   *   The inclusive column
   * @param endRow
   *   The exclusive row
   * @param endCol
   *   The exclusive column
   *
   * @return If the coordinates make a valid bounded area.
   *
   * @throw IndexOutOfBoundsException
   *   If the rows or columns are inappropriate.
   */
  private boolean makeBoundsException(int startRow, int startCol, int endRow, int endCol) {
    int deltaR;
    int deltaC;
    if (endRow > startRow) {
      deltaR = 1;
    } else {
      deltaR = -1;
    } // if / else
    if (endCol > startCol) {
      deltaC = 1;
    } else {
      deltaC = -1;
    } // if / else
    return (isIndexException(startRow, startCol)
            && isIndexException(endRow - deltaR, endCol - deltaC));
  } // makeBounds(int, int, int, int)

  /**
   * Checks if a set of coordinates reference a valid index.
   *
   * @param row
   *   The row part of the index.
   * @param col
   *   The column part of the index.
   *
   * @return If the coordinates reference a valid index.
   *
   * @throw IndexOutOfBoundsException
   *   If the rows or columns are inappropriate.
   */
  private boolean isIndexException(int row, int col) {
    if (isBound(row, 0, height())) {
      if (isBound(col, 0, width())) {
        return true;
      } else {
        throw new IndexOutOfBoundsException("Index " + col
                                            + " out of bounds for length " + width());
      } // if / else
    } else {
      throw new IndexOutOfBoundsException("Index " + row + " out of bounds for length " + height());
    } // if / else
  } // isIndexException(int, int)

  /**
   * Determine if an integer is between two integers, first
   * inclusive, second exclusive.
   *
   * @param arg
   *   The integer to check.
   * @param floor
   *   The inclusive parameter.
   * @param ceil
   *   The exclusive parameter.
   *
   * @return If the arg is between the two other integers.
   */
  private boolean isBound(int arg, int floor, int ceil) {
    if (floor < ceil) {
      return ((arg >= floor) && (arg < ceil));
    } else {
      return ((arg <= floor) && (arg > ceil));
    } // if / else
  } // isBound(int, int, int)
} // class MatrixV0
