import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class Ball{
  
  GamePanel parent;
  Platform platform;
  BufferedImage img;
  
  private ArrayList<Brick> bricks;
  private int x, y;
  private int STARTX = 200, STARTY = 200;
  static final int RADIUS = 10, SPEED = 5;
  private boolean attached, moveLeft, moveDown;
  
  public Ball(GamePanel parent){
    this.parent = parent;
    x = STARTX;
    y = STARTY;
    try{
      img = ImageIO.read(new File("Graphics/Ball.png"));
    }catch(IOException e){System.err.println(e.getMessage());return;}
  }
  
  public void setPlatform(Platform pla){
    platform = pla;
    x = platform.getX()+(platform.getWidth()/2)-(RADIUS/2);
    y = platform.getY()-RADIUS;
    attached = true;
  }
  
  public void setBricks(ArrayList<Brick> b){
    this.bricks = b;
  }
  
  public void draw(Graphics g){
    g.drawImage(img,x,y, null);
    calcMovements();
    move();
  }
  
  public void calcMovements(){
    if(attached){
      x = platform.getX()+(platform.getWidth()/2)-(RADIUS/2);
      y = platform.getY()-RADIUS;
      return;
    }
    
    if(platform.isCollision(x,y)){
      moveDown = !moveDown;
    }
    
    for(int i = 0; i < bricks.size();i++){
      Brick b = bricks.get(i);
      if(b.hitTopBottom(x,y)){
        b.loseHP();
        if(b.getHP() <= 0)
          bricks.remove(i);
        moveDown = !moveDown;
        Player.addScore(Player.NORMBRICK);
        System.out.println("TB");
      }else if(b.isCollision(x,y)){
        b.loseHP();
        if(b.getHP() <= 0)
          bricks.remove(i);
        moveLeft = !moveLeft;
        System.out.println("SIDES");
        Player.addScore(Player.NORMBRICK);
      }
    }
    
    if(x <= 0 && y < platform.getY()+img.getHeight())
      moveRight();
    if(x >= 300-RADIUS-10 && y < platform.getY()+img.getHeight())
      moveLeft();
    if(y >= 300 - RADIUS)
      loseLife();
    if(y <= 0)
      moveDown();       
  }
  
  public void move(){
    if(attached)
      return;
    
    if(moveLeft)
      x-=SPEED;
    else
      x+=SPEED;
    
    if(moveDown)
      y+=SPEED;
    else
      y-=SPEED;
  }
  
  public void loseLife(){
    Player.loseLife();
    attached = true;
    platform.restart();
  }
  
  public void release(){
    if(attached == false)
      return;
    attached = false;
    moveUp();
    if(x > 150)
      moveRight();
    else
      moveLeft();
  }
  
  public void moveUp(){
    moveDown = false;
  }
  public void moveDown(){
    moveDown = true;
  }
  public void moveLeft(){
    moveLeft = true;
  }
  public void moveRight(){
    moveLeft = false;
  }
  
  public int getX(){
    return x;
  }
  public int getY(){
    return y;
  }
  public boolean isAttached(){
    return attached;
  }
  public int getSize(){
    return RADIUS;
  }
  
  
}