import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class Brick{
  
  static final int NULL = 0, ONEHIT = 1,TWOHIT = 2,ITEM = 3;
  static final int WIDTH = 30, HEIGHT = 10;
  BufferedImage img;
  private int x,y, hp;
  Color col;
  
  public Brick(int x, int y, int type){
    this.x = (WIDTH * x);
    this.y = (HEIGHT * y);
    switch(type){
      case ONEHIT: 
        hp = 1;
        break;
      case TWOHIT:
        hp = 2;
        break;
      default:
        hp = 1;
    }
    try{
      img = ImageIO.read(new File("Graphics/HP"+hp+"Brick.png"));
    }catch(IOException e){e.getMessage(); return;}
  }
  
  public void loseHP(){
    hp--;
    try{
      img = ImageIO.read(new File("Graphics/HP"+hp+"Brick.png"));
    }catch(IOException e){e.getMessage(); return;}
  }
  
  public int getHP(){
    return hp;
  }
  
  public void draw(Graphics g){
    g.drawImage(img,x,y,null);
  }
  
  public int getX(){
    return x;
  }
  public int getY(){
    return y;
  }
  
  public boolean isCollision(int ballX, int ballY){
    if(x <= ballX && x + WIDTH > ballX &&
       y <= ballY && y + HEIGHT > ballY)
      return true;  
    return false;
  }
  
  public boolean hitTopBottom(int ballX, int ballY){
    if((y+HEIGHT == ballY || y == ballY-10 )&& 
       x <= ballX && x + WIDTH > ballX)
      return true;
    return false;
  }
  
  public boolean hitSides(int ballX, int ballY, boolean movingLeft){
     if(((x+WIDTH == ballY && movingLeft)|| (x == ballX+10 && !movingLeft) )&& 
       y < ballY && y + HEIGHT > ballY)
      return true;
    return false;
  }
}