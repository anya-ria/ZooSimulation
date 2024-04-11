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
    
    Label end, name;
    
    public EndingScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 800, 1); 
        
        end = new Label("Game Over", 150);
        addObject(end, getWidth()/2, 200);
        
        end = new Label("*Ending Name*", 120);
        addObject(end, getWidth()/2, 500);
    }
}
