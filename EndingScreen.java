import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EndingScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EndingScreen extends World
{

    /**
     * Constructor for objects of class EndingScreen.
     * 
     */
    
    private int endingNum;
    
    public EndingScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 800, 1); 
        Label over = new Label("Game Over", 150);
        addObject(over,getWidth()/2,430);
    }
    
    
    public void end1(){
        
    }
    
    public void end2(){
        
    }
    
    public void end3(){
        
    }
    
}
