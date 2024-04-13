import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

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
    
    Label time;
    int timeElasped;
    HomeButton homeButton = new HomeButton();
    
    public EndingScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 800, 1); 
        
        time = new Label(timeElasped, 150);
        //addObject(end, getWidth()/2, 200);
        
        homeButton.setLocation(72,754);
        addObject(new Healer(), 600, 200);
        
        setBackground("end1.png");
    }
    
    public void setTimeElasped(int x){
        timeElasped = x;
    }
}
