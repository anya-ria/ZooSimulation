import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.World;
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
    
    private Label time;
    private int timeElasped;
    private HomeButton homeButton = new HomeButton();
    private SimpleTimer gameTimer = new SimpleTimer();
    
    public EndingScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 800, 1); 
        
        time = new Label(timeElasped, 150);
        //addObject(end, getWidth()/2, 200);
        
        homeButton.setLocation(72,754);
        addObject(new Healer(), 600, 200);
        
        setBackground("end1.png");
        
        gameTimer.mark();
        
        timeElasped();
    }
    
    /**
     * Call "Game Over" and stop the game. 
     */
    public void ending1(){
        gameTimer.mark();
        //Greenfoot.stop();
    }
    
    public void ending2(){
        setBackground("end2.png");
        gameTimer.mark();
        //Greenfoot.stop();
    }
    
    public void ending3(){
        setBackground("end3.png");
        gameTimer.mark();
        //Greenfoot.stop();
    }
    
    public void timeElasped(){
        int x = gameTimer.millisElapsed()/1000;
        Label gameOverLabel3 = new Label(x+" second", 48);
        addObject(gameOverLabel3,635,560);
        gameTimer.mark();
    }
}
