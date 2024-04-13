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
        
        setBackground("userSelectScreen.jpg");
    }
    
    public void act(){
        if(Greenfoot.mouseClicked(startButton)){
            MyWorld game = new MyWorld();
            Greenfoot.setWorld(game);
        }
    }
}
