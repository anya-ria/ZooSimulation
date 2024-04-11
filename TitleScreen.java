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
        setBackground("title.jpg");

        startButton = new StartButton();
        addObject(startButton,400,480);
        
        achieveButton = new AchieveButton();
        addObject(achieveButton,400,580);
        
        EndButton endButton = new EndButton();
        addObject(endButton,400,680);
    }

    public void act(){

        if(Greenfoot.mouseClicked(startButton)){
            Customization game = new Customization();
            Greenfoot.setWorld(game);
        }
    }

}