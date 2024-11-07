package edu.grinnell.csc207.util;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class TempDrawTile {
   private ArrayList tiles; 

   public TempDrawTile(Board b) {
    tiles = populateTiles(b);
   }

   private ArrayList<Tile> populateTiles(Board b) {
    ArrayList<Tile> tileList = new ArrayList<Tile>();
    Random rand = new Random();
    int[] newArr = new int[8];

    for (int k = 0; k < 8; k++) {
        newArr[k] = rand.nextInt(8) + 1;
    }

    int base = new Random();

    for (int i = 0; i < 8; i++) {
        tileList.add(new Tile(base, newArr));
    }
   }
} // class TempDrawTile
