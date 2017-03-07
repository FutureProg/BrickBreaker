/* 
* Author: Nick Morrison
* Date: March 6, 2017
* Brick Breaker Game
* Ball Class
*/

//import libraries
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

/*This class defines a Ball object. This class contains the 
position of the ball, radius, ball speed, etc. This class
provides public methods to retrieve ball information such as
the position of the ball, direction of travel, calculating
the movement of the ball, hit detection with bricks, etc. 
*/
public class Ball{
  
  //Global Variables
  GamePanel parent;
  Platform platform;
  BufferedImage img;
  

  private ArrayList<Brick> bricks;  // List of bricks in the scene  
  //Ball attributes
  private int x, y; //(X,Y) coordinate position of the ball
  private int STARTX = 200, STARTY = 200; 
  static final int RADIUS = 10, SPEED = 5; 
  private boolean attached, moveLeft, moveDown; //determine direction of travel
  
  /* Constructor.
    @param parent GamePanel to display the ball
  */
  public Ball(GamePanel parent){
    this.parent = parent;
    x = STARTX;
    y = STARTY;
    try{
      //retrive ball image
      img = ImageIO.read(new File("Graphics/Ball.png"));
    }catch(IOException e){System.err.println(e.getMessage());return;}
  }
  
  /** Initilize the platform and set the ball at the center of the platform**/
  public void setPlatform(Platform pla){
    platform = pla;
    x = platform.getX()+(platform.getWidth()/2)-(RADIUS/2);
    y = platform.getY()-RADIUS;
    attached = true;
  }
  /** Set up the Bircks in the Scene**/
  public void setBricks(ArrayList<Brick> b){
    this.bricks = b;
  }
  
  /** Displays the Ball **/
  public void draw(Graphics g){
    g.drawImage(img,x,y, null);
    calcMovements();
    move();
  }
  
  /** Calculates the Movement of the ball **/
  public void calcMovements(){
    //If the ball is attached to the platform...
    if(attached){
      //...set the ball at the center of the platform
      x = platform.getX()+(platform.getWidth()/2)-(RADIUS/2);
      y = platform.getY()-RADIUS;
      return;
    }
    
    //If the ball collisdes with an object...
    if(platform.isCollision(x,y)){
      //..set the vertical movement to the opposite of what is was
      moveDown = !moveDown;
    }
    
    /*Check if bircks have been hit. Reduce the brick health and
      move the ball according to where the ball has hit
    */
    for(int i = 0; i < bricks.size();i++){
      Brick b = bricks.get(i);

      if(b.hitTopBottom(x,y)){  //The ball has hit the bottom of a brick
        b.loseHP();
        if(b.getHP() <= 0)  //the health of a brick is less than 0, then destroy it
          bricks.remove(i);
        moveDown = !moveDown; //change vertical direction of travel
        Player.addScore(Player.NORMBRICK);  //modify the score
      }else if(b.isCollision(x,y)){ //The ball has hit the sside of the brick
        b.loseHP();
        if(b.getHP() <= 0)  //the health of a brick is less than 0, then destroy it
          bricks.remove(i);
        moveLeft = !moveLeft;
        Player.addScore(Player.NORMBRICK);  //mdoify the score
      }
    }
    
    //Move the ball according to its attributes
    if(x <= 0 && y < platform.getY()+img.getHeight())
      moveRight();
    if(x >= 300-RADIUS-10 && y < platform.getY()+img.getHeight())
      moveLeft();
    if(y >= 300 - RADIUS)
      loseLife();
    if(y <= 0)
      moveDown();       
  }
  
  /** Moves the ball based on attributes of the ball **/
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
  
  /* Decreases the life of the player and resets the platform/ball */
  public void loseLife(){
    Player.loseLife();
    attached = true;
    platform.restart();
  }
  
  /* Detaches the ball from the platform and launches it*/
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
  
  /* Make the travel direction of the ball up */
  public void moveUp(){
    moveDown = false;
  }

  /* Make the travel direction of the ball down */
  public void moveDown(){
    moveDown = true;
  }

  /* Make the travel direction of the ball left */
  public void moveLeft(){
    moveLeft = true;
  }

  /* Make the travel direction of the ball right */
  public void moveRight(){
    moveLeft = false;
  }
  
  /* Return the X coordinate of the ball */
  public int getX(){
    return x;
  }

  /* Return the Y coordinate of the ball */
  public int getY(){
    return y;
  }

  /* Return the attached state of the ball*/
  public boolean isAttached(){
    return attached;
  }

  /* Return the radius of the ball */
  public int getSize(){
    return RADIUS;
  }
  
  
}