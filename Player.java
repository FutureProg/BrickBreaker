import java.awt.*;

public class Player{
  
  public static final int NORMBRICK = 20, SCOREITEM = 40;
  
  private static int score = 0, life = 3;
  private static GamePanel parent;
  
  public static void addScore(int num){
    score += num;
    System.err.println(score);
  }
  
  public static void loseLife(){
    if(life > 1)
      life--;
    else 
      parent.gameOver();
  }
  
  public static void setParent(GamePanel p){
    parent = p;
  }
  
  public static int getScore(){
    return score;
  }
  
  public static int getLives(){
    return life;
  }
  
}