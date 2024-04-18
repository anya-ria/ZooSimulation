import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Write a description of class Customization here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Customization extends World
{
    private StartButton startButton;
    ChildParam c1, c2, c3;
    /**
     * Constructor for objects of class Customization.
     * 
     */
    public Customization()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 800, 1); 
        startButton = new StartButton();
        addObject(startButton,getWidth()/2,760);

        setBackground("userSelectScreen.jpg");

        prepare();
    }

    public void act(){
        if(Greenfoot.mouseClicked(startButton)){
            MyWorld game = new MyWorld();
            Greenfoot.setWorld(game);
        }
    }

    public void drawTextBox(){

    }
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        c1 = new ChildParam();
        c1.setIcon(1);
        addObject(c1,getWidth()/2-100,318);
        c2 = new ChildParam();
        c2.setIcon(2);
        addObject(c2,getWidth()/2,318);
        c3 = new ChildParam();
        c3.setIcon(3);
        addObject(c3, getWidth()/2+100,318);
    }
    
    
}
