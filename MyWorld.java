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

    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 800, 1); 
<<<<<<< HEAD
        Greenfoot.setSpeed(50);
    }
    
    
    public void act(){
        
=======
        addObject(new Regular(), 512, 400);
>>>>>>> 91db7f7bee59668e90000eb931ba9e5500c2ba07
    }
}
