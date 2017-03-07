/* 
* Author: Nick Morrison
* Date: March 6, 2017
* Brick Breaker Game
* Brick Class
*/

//import libraries
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

/* This class defines the attributes and methods of bricks such as the 
brick width, height, health. Also provides public methods for retriving
the (x,y) coordinates, the health, and hit detection.
*/
public class Brick{
  
  //Global Variables
  static final int NULL = 0, ONEHIT = 1,TWOHIT = 2,ITEM = 3;
  static final int WIDTH = 30, HEIGHT = 10;
  BufferedImage img;
  private int x,y, hp;
  Color col;
  
  /* Construcor.
	@param x The X coordinate of the brick
	@param y The y coordinate of the brick
	@param type The type of the brick (determines health/color of brick)

  */
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
      img = ImageIO.read(new File("Graphics/HP"+hp+"Brick.png"));	//retrie ball graphics
    }catch(IOException e){e.getMessage(); return;}
  }
  
  /* Decreases the health of the brick */
  public void loseHP(){
    hp--;
    try{
      img = ImageIO.read(new File("Graphics/HP"+hp+"Brick.png"));
    }catch(IOException e){e.getMessage(); return;}
  }
  
  /* Return the health of the brick*/
  public int getHP(){
    return hp;
  }
  
  /* Render of the brick*/
  public void draw(Graphics g){
    g.drawImage(img,x,y,null);
  }
  
  /*Return the X coordinate of the brick */
  public int getX(){
    return x;
  }

  /* Return the Y coordinate of the brick*/
  public int getY(){
    return y;
  }
  
  /* Check for coliision with the ball */
  public boolean isCollision(int ballX, int ballY){
  	//Determine if the ball has crosses the boundary of the brick
    if(x <= ballX && x + WIDTH > ballX &&
       y <= ballY && y + HEIGHT > ballY)
      return true;  
    return false;
  }
  
  /* Return if the ball has hit the bottom of a brick*/
  public boolean hitTopBottom(int ballX, int ballY){
    if((y+HEIGHT == ballY || y == ballY-10 )&& 
       x <= ballX && x + WIDTH > ballX)
      return true;
    return false;
  }
  
  /* Return if the ball has hit the side of the brick*/
  public boolean hitSides(int ballX, int ballY, boolean movingLeft){
     if(((x+WIDTH == ballY && movingLeft)|| (x == ballX+10 && !movingLeft) )&& 
       y < ballY && y + HEIGHT > ballY)
      return true;
    return false;
  }
}