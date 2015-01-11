import javax.swing.*;
import java.awt.*;

public class Main{
  
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