import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Collections here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Collections extends World
{

    /**
     * Constructor for objects of class Collections.
     * 
     */

    private HomeButton returnKey;
    
    public Collections()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 800, 1);  
        setBackground("collectionScreen.png");
    
        returnKey = new HomeButton();
        addObject(returnKey, getWidth()-80, 750);
    }
    
    public void act(){
        if(Greenfoot.mouseClicked(returnKey)){
            TitleScreen game = new TitleScreen();
            Greenfoot.setWorld(game);
            Greenfoot.playSound("mouseClick.mp3");
        }
    }
}
