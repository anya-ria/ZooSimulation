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
    
    Label start, achi, collec;
    
    public TitleScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 800, 1); 
        

        achi = new Label("Achievements", 50);
        addObject(achi, getWidth()/2, 550);
        collec = new Label("Endings", 50);
        addObject(collec, getWidth()/2, 650);
    }
    
    public void act(){
        if(Greenfoot.isKeyDown("space")){
            MyWorld game = new MyWorld();
            Greenfoot.setWorld(game);
        }
        
        if(Greenfoot.mouseClicked(achi)){
            Achievement achi1 = new Achievement();
            Greenfoot.setWorld(achi1);
        }
        
        if(Greenfoot.mouseClicked(collec)){
            Collections collec1 = new Collections();
            Greenfoot.setWorld(collec1);
        }
    }
}
