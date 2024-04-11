import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    
    HomeButton homeButton = new HomeButton();
    
    public MyWorld()
    {    
        // Create a new world with 1024x800 cells with a cell size of 1x1 pixels.
        super(1024, 800, 1); 
        addObject(new Regular(), 512, 400);
        addObject(new Regular(), 112, 400);
        addObject(new Regular(), 212, 400);
        addObject(new Regular(), 312, 400);
        addObject(new Regular(), 412, 400);
        addObject(new Traitor(), 800, 600);
        addObject(homeButton,79,739);
        homeButton.setLocation(72,754);
        addObject(new Healer(), 600, 200);
        
        setBackground("zoo.jpg");
    }

}
