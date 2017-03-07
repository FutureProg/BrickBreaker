/* 
* Author: Nick Morrison
* Date: March 6, 2017
* Brick Breaker Game
* Brick Class
*/
//import library
import javax.swing.*;
import java.awt.*;

/* Main Class*/
public class Main{
  
  /* Entry point of the program and initlize the game settings */
  public static void main(String[] args){
    JFrame frame = new JFrame("BrickBeaker");
    frame.setSize(300,300);
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);
    GamePanel panel = new GamePanel(frame);
    frame.add(panel);
    frame.setVisible(true);
  }
  
}