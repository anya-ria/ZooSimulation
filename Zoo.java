import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Zoo extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    
    HomeButton homeButton = new HomeButton();
    
    public Zoo()
    {    
        // Create a new world with 1024x800 cells with a cell size of 1x1 pixels.
        super(1024, 800, 1); 
        addObject(new Regular(), 512, 400);
        addObject(new Fighter(), 112, 400);
        addObject(new Fighter(), 212, 400);
        addObject(new Fighter(), 312, 400);
        addObject(new Fighter(), 412, 400);
        addObject(new Traitor(), 800, 600);
        addObject(homeButton,79,739);
        homeButton.setLocation(72,754);
        addObject(new Healer(), 600, 200);
        addObject(new Healer(), 400, 200);
        addObject(new Fighter(), 800, 150);
        addObject(new Hippo(), 850, 150);
        addObject(new Hippo(), 750, 250);
        
        setBackground("zoo.jpg");
    }

}
