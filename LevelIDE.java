import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.util.ArrayList;

public class LevelIDE extends JPanel{
  
  JFrame parent;
  BufferedImage plat, bricks[];
  ArrayList<Brick> brickList;
  
  public LevelIDE(JFrame p){
    parent = p;
    setPreferredSize(new Dimension(300,300));
    setBackground(Color.WHITE);
    bricks = new BufferedImage[3];
    try{
      plat = ImageIO.read(new File("Graphics/Platform.png"));
      bricks[0] = ImageIO.read(new File("Graphics/HP1Brick.png"));
      bricks[1] = ImageIO.read(new File("Graphics/HP2Brick.png"));
      bricks[2] = ImageIO.read(new File("Graphics/EmptyBrick.png"));
    }catch(IOException e){System.err.println(e.getMessage());return;}
    brickList = new ArrayList<Brick>();
    
    addMouseListener(new MouseAdapter(){
      public void mouseClicked(MouseEvent evt){
        click(evt);
      }
    });
  }
  
  public void click(MouseEvent evt){
    switch(evt.getButton()){
      case MouseEvent.BUTTON1:
        addBrick(evt);
        break;
      case MouseEvent.BUTTON2:
        changeBrickType(evt);
        break;
      case MouseEvent.BUTTON3:
        removeBrick(evt);
        break;
    }
  }
  
  public void addBrick(MouseEvent evt){
    int x = evt.getX();
    int y = evt.getY();
    brickList.add(new Brick(x/30,y/10,Brick.ONEHIT));
    repaint();
  }
  
  public void removeBrick(MouseEvent evt){
    int x = evt.getX();
    int y = evt.getY();
    for(int i = 0; i < brickList.size(); i++){
      Brick b = brickList.get(i);
      if(x/30 == b.getX()/30 && y/30 == b.getY()/30)
        brickList.remove(i);
    }
    repaint();
  }
  
  public void changeBrickType(MouseEvent evt){
  }
  
  public void paintComponent(Graphics g){
    super.paintComponent(g); 
    drawGrid(g);
    g.drawImage(plat,120,250,null);
    g.drawString("LEVEL EDITOR",110,180);
    for(Brick b: brickList)
      b.draw(g);
  }
  
  public void drawGrid(Graphics g){
    for(int x = 0; x < 10; x++){
      for(int y = 0; y < 14; y++){
        g.drawImage(bricks[2],x*30,y*10,null);
      }
    }
  }
  
}