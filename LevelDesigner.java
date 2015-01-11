import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class LevelDesigner{
  
  static JMenuBar menuBar;
  static JMenu fileMenu;
  static JMenuItem saveItem, exitItem;
  
  public static void main(String[] args){
    JFrame frame = new JFrame("Level Designer");
    frame.setSize(300,310);
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);
    frame.setContentPane(new LevelIDE(frame));
    
    menuBar = new JMenuBar();
    fileMenu = new JMenu("File");
    fileMenu.setMnemonic(KeyEvent.VK_F);
    menuBar.add(fileMenu);
    
    saveItem = new JMenuItem("Save");
    saveItem.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_S, ActionEvent.CTRL_MASK));
    fileMenu.add(saveItem);
    fileMenu.addSeparator();
    exitItem = new JMenuItem("Exit");
    fileMenu.add(exitItem);
    exitItem.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent evt){
        frame.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
      }
    });
    
    frame.setJMenuBar(menuBar);
    frame.setVisible(true);
  }
}