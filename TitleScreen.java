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
    private AchieveButton achieveButton;
    private EndButton endButton;
    private StartButton startButton;
    
    public TitleScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 800, 1); 
        setBackground("title2.jpg");

        startButton = new StartButton();
        addObject(startButton,getWidth()/2,450);
        
        achieveButton = new AchieveButton();
        addObject(achieveButton,getWidth()/2,550);
        
        EndButton endButton = new EndButton();
        addObject(endButton,getWidth()/2,650);
    }

    public void act(){

        if(Greenfoot.mouseClicked(endButton)){
            Collections collec1 = new Collections();
            Greenfoot.setWorld(collec1);
        }

        if(Greenfoot.mouseClicked(achieveButton)){
            Achievement achi1 = new Achievement();
            Greenfoot.setWorld(achi1);
        }
        
        if(Greenfoot.mouseClicked(startButton)){
            MyWorld game = new MyWorld();
            Greenfoot.setWorld(game);
        }
    }

}
