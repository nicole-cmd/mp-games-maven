package edu.grinnell.csc207.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class TempDrawTile {
   private Tile[] tiles; 

   public TempDrawTile(Board b) {
    tiles = populateTiles(b);
   } // TempDrawTile(Board)

   @Override
   public void actionPerformed(ActionEvent e) {

    repaint();
   }

   @Override
   public void paintComponent(Graphics g) {
    super.paintComponent(g);

    for(Tile t : tiles) {
        t.drawTile(g, this);
    }
   } // paintComponent(Graphics)

   private Tile[] populateTiles(Board b) {
    Tile[] tileList = new Tile[49];
    Random rand = new Random();
    int[] newArr = new int[8];

    for (int k = 0; k < 8; k++) {
        newArr[k] = rand.nextInt(8) + 1;
    } // for

    for (int i = 0; i < 8; i++) {
        tileList[i] = new Tile(rand.nextInt(8) + 1, newArr);
    } // for

    return tileList;
   } // populateTiles(Board)

   private void drawScore(Graphics g) {

   } // drawScore(Graphics)

   private void drawTile(Graphics g) {
    //stud
   } // drawTile(Graphics)

} // class TempDrawTile
