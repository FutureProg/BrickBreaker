import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class BrickReader{
  
  Scanner input;
  ArrayList<Brick> bricks;
  
  public BrickReader(String level){
    try{
      input = new Scanner(new File("Levels/"+level+".dat"));
    }catch(IOException e){System.err.println(e.getMessage());return;}
    bricks = new ArrayList<Brick>();
  }
  
  public ArrayList<Brick> readBricks(){
    int type;
    String got;
    for(int y = 1; input.hasNextLine(); y++){
      got = input.nextLine();
      for(int x = 0; x < got.length(); x ++){
        type = Integer.parseInt(got.charAt(x)+"");
        if(type != 0)
          bricks.add(new Brick(x,y,type));
      }
      System.out.println(y);
    }
    return bricks;
  }
  
  public void setLevel(String level){
   try{
      input = new Scanner(new File("Levels/"+level+".dat"));
    }catch(IOException e){System.err.println(e.getMessage());return;}
    bricks = new ArrayList<Brick>(); 
  }
    
}