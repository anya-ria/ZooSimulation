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
    
    Label end1, end2, end3, returnKey;
    
    public Collections()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 800, 1);  
        
        end1 = new Label("Ending 1", 70);
        addObject(end1, getWidth()/2, 200);
        end2 = new Label("Ending 2", 70);
        addObject(end2, getWidth()/2, 400);
        end3 = new Label("Ending 3", 70);
        addObject(end3, getWidth()/2, 600);
        returnKey = new Label("Home", 50);
        addObject(returnKey, getWidth()-80, 750);
    }
    
    public void act(){
        if(Greenfoot.mouseClicked(returnKey)){
            TitleScreen game = new TitleScreen();
            Greenfoot.setWorld(game);
        }
    }
}
