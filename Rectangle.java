import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Rectangle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Rectangle extends Icon
{
    /**
     * Act - do whatever the Rectangle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private String input, inputx;
    private boolean x;
    //private String[] allowedKeys = {"1","2","3","4","5","6","7","8","9"};
    
    public void act()
    {
        // Add your action code here.
        if(Greenfoot.mouseClicked(this)){
            input = Greenfoot.ask("Please input a value");
        }
        
        if(input!=null){
            setImage(new GreenfootImage(input, 40, Color.BLACK, new Color(0, 0, 0, 0)));
        }
    }
    
    public Rectangle(){
        GreenfootImage image = new GreenfootImage(100, 40);
        image.setColor(new Color(255,255,255));
        image.fill();
        setImage(image);
    }
    
    /**
     * Prints text using Text(). Adds new Text object if the spot is empty, otherwise changes object's image.
     * 
     * @param text
     *      Text to be written.
     *      
     * @param x, y
     *      Coordinates at which to place the text.
     *      
     * @param size
     *      Size of the text.
     */
    public void placeText(String text, int x, int y, int size)
    {
        World world = getWorld(); 
        world.addObject(new Label(text, size), x,y);
    }
    
}
