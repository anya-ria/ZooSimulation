import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TitleScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleScreen extends World
{

    /**
     * Constructor for objects of class TitleScreen.
     * 
     */
    
    Label title, start;
    
    public TitleScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 800, 1); 
        
        title = new Label("Zoo Trip", 250);
        addObject(title, getWidth()/2, 250);
        title = new Label("Press 'space' to start", 100);
        addObject(title, getWidth()/2, 550);
    }
    
    public void act(){
        if(Greenfoot.isKeyDown("space")){
            MyWorld game = new MyWorld();
            Greenfoot.setWorld(game);
        }
    }
}
