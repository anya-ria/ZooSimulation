import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Customization here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Customization extends World
{
    private StartButton startButton;
    /**
     * Constructor for objects of class Customization.
     * 
     */
    public Customization()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 800, 1); 
        startButton = new StartButton();
        addObject(startButton,getWidth()/2,760);
    }
    
    public void act(){
        if(Greenfoot.mouseClicked(startButton)){
            Zoo game = new Zoo();
            Greenfoot.setWorld(game);
        }
    }
}
