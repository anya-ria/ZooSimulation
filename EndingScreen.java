import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.World;
/**
 * Ending screen
 * 
 * @author Vanessa Huo
 * @version 04/25/2024
 */
public class EndingScreen extends World
{
    //Init button and timer
    private HomeButton homeButton = new HomeButton();
    private SimpleTimer gameTimer = new SimpleTimer();
    private int timeElasped;
    public EndingScreen()
    {    
        // Create a new world with 1024x800 cells with a cell size of 1x1 pixels.
        super(1024, 800, 1); 
        
        addObject(homeButton,79,739);
        homeButton.setLocation(72,754);
        
        gameTimer.mark();
    }
    
    /**
     * Call "Ending 1" and stop the game. 
     */
    public void ending1(){
        setBackground("end1.png");
        timeElasped();
    }
    
    /**
     * Call "Ending 2" and stop the game. 
     */
    public void ending2(){
        setBackground("end2.png");
        timeElasped();
    }
    
    /**
     * Call "Ending 3" and stop the game. 
     */
    public void ending3(){
        setBackground("end3.png");
        timeElasped();
    }
    
    /**
     * Record and show the time used to complete the game
     */
    public void timeElasped(){
        int x = gameTimer.millisElapsed()/1000;
        Label gameOverLabel3 = new Label(x+" second", 48);
        addObject(gameOverLabel3,635,560);
        gameTimer.mark();
    }
}
