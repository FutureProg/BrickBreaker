import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class GameOverPanel extends JPanel{
  JFrame parent;
  BufferedImage img;
  
  public GameOverPanel(JFrame p){
   this.parent = p;
   setPreferredSize(parent.getSize());
   setBackground(Color.WHITE);
   try{
     img = ImageIO.read(new File("Graphics/GameOver.png"));
   }catch(IOException e){System.err.println(e.getMessage());return;}
   System.out.println("GAME OVER");
   parent.pack();
  }
  
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    g.drawImage(img,0,0,null);
    repaint();
  }
}


