import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class Platform{
  
  Ball ball;
  GamePanel parent;
  int x, y;
  private BufferedImage img;
  private int STARTX = 110, STARTY = 250;
  private int WIDTH = 50, HEIGHT = 10;
  private int SPEED = 6;
  private boolean moveRight, moveLeft;
  
  public Platform(GamePanel parent, Ball b){
    this.parent = parent;
    x = STARTX;
    y = STARTY;
    this.ball = b;
    moveRight = true;
    moveLeft = true;
    try{
      img = ImageIO.read(new File("Graphics/Platform.png"));
    }catch(IOException e){e.getMessage(); return;}
  }
  
  public void restart(){
    x = STARTX;
    y = STARTY;
    moveRight = true;
    moveLeft = true;
  }
  public void draw(Graphics g){
    g.drawImage(img,x,y,null);
    move();
  }
  
  public void move(){
    if(x <= 0 && moveLeft)
      return;
    if(x+WIDTH+10 >= 300 && moveRight)
      return;
      
    if(moveRight)
      x += SPEED;
    if(moveLeft)
      x -= SPEED;
  }
  
  public void stopLeft(){
    moveLeft = false;
  }  
  public void stopRight(){
    moveRight = false;
  }
  public void moveLeft(){
    moveRight = false;
    moveLeft = true;
  }
  public void moveRight(){
    moveRight = true;
    moveLeft = false;
  }
  
  public int getX(){
    return x;
  }
  public int getY(){
    return y;
  }
  public int getWidth(){
    return WIDTH;
  }
  public int getHeight(){
    return HEIGHT;
  }
  
  public boolean isCollision(int ballX, int ballY){
    if(x <= ballX && x + WIDTH > ballX &&
       y <= ballY && y + HEIGHT > ballY - ball.RADIUS)
      return true;
    return false;
  }
  
}