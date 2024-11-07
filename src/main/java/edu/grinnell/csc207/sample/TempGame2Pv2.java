package edu.grinnell.csc207.sample;

import javax.swing.*;
import java.awt.event.*;
import edu.grinnell.csc207.util.*;
import java.util.Random;

public class TempGame2Pv2 {
  public static void main(String[] args) {
    JFrame f=new JFrame("Sayu");
    JTextField x=new JTextField();
    x.setBounds(50,100,40,20);
    JButton b=new JButton("Create with above seed");
    JButton[] fieldGrid = new JButton[52];
    b.setBounds(50,200,95,30);
    b.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        Board game;
        if(x.getText().equals("")){
          game = new Board(new Random());
        } else {
          game = new Board(new Random(Integer.parseInt(x.getText())));
        } // if / else
        b.setVisible(false);
        x.setVisible(false);
        for(int i = 0; i < 49; i++){
          fieldGrid[i] = new JButton();
          fieldGrid[i].setName(i+"");
          fieldGrid[i].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
              System.out.println("Event occured in button: " + ((JButton)e.getSource()).getName());
            } // actionPerformed(ActionEvent)
          }); // addActionListener(ActionListener)
          f.add(fieldGrid[i]);
          fieldGrid[i].setBounds(25 + (i % 7) * 35, 80 + (i / 7) * 35, 35, 35);
        } // for [i]
        fieldGrid[50] = new JButton();
        fieldGrid[50].setName("Rotate Clockwise");
        fieldGrid[50].addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e){
            System.out.println("Event occured in button: " + ((JButton)e.getSource()).getName());
          } // actionPerformed(ActionEvent)
        }); // addActionListener(ActionListener)
        f.add(fieldGrid[50]);
        fieldGrid[50].setBounds(300, 175, 25, 55);
        fieldGrid[51] = new JButton();
        fieldGrid[51].setName("Rotate Counterclockwise");
        fieldGrid[51].addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e){
            System.out.println("Event occured in button: " + ((JButton)e.getSource()).getName());
          } // actionPerformed(ActionEvent)
        }); // addActionListener(ActionListener)
        f.add(fieldGrid[51]);
        fieldGrid[51].setBounds(375, 175, 25, 55);
      } // actionPerformed(ActionEvent)
    }); // addActionListener(ActionListener)
    f.add(b);
    f.add(x);
    f.setSize(500,435);
    f.setLayout(null);
    f.setVisible(true);
    f.addMouseListener(new MouseListener() {
      public void mouseClicked(MouseEvent e) {

      }

      public void mouseEntered(MouseEvent e) {
        
      }

      public void mouseExited(MouseEvent e) {
        
      }

      public void mouseReleased(MouseEvent e) {
        
      }

      public void mousePressed(MouseEvent e) {
        
      }
    });
  }
}
