import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class ScreenTransition{
  
  static Color col;
  static int colNum = 5;
  static JPanel target, previous;
  static JFrame frame;
  static Graphics g;
  static Timer timer = new Timer(80,new ActionListener(){
    public void actionPerformed(ActionEvent evt){
      colNum--;
      switch(colNum){
        case 0:
          col = Color.WHITE;
          System.out.println(col);
          fade();
          break;
        case 1:
          col = Color.GRAY;
          System.out.println(col);
          fade();
          break;
        case 2:
          col = Color.BLACK;
          System.out.println(col);
          fade();
          break;
        case 3:
          col = Color.BLACK;
          System.out.println(col);
          fade();
          break;
        case 4:
          col = Color.GRAY;
          System.out.println(col);
          fade();
          break;
        case 5:
          col = Color.WHITE;
          System.out.println(col);
          fade();
          break;
      }
    }
  });
  
  public static void fade(JFrame frm, JPanel trg, JPanel prev){
    target = trg;
    previous = prev;
    frame = frm;
    g = frame.getContentPane().getGraphics();
    timer.start();
  }
  
  public static void fade(){
    g.setColor(col);
    g.fillRect(0,0,frame.getWidth(),frame.getHeight());
    System.out.println("fade");
  }
  
}