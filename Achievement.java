import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;
/**
 * Write a description of class Achievement here.
 * 
 * @author Vanessa
 * @version 2024/4/19
 */
public class Achievement extends World
{

    /**
     * Constructor for objects of class Achievement.
     * 
     */

    private Label title, achi1, achi2, achi3, achi4, achi5, achi6;
    private Trophy[] arr = new Trophy[5];
    private HomeButton returnKey;

    public Achievement()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 800, 1); 
        setBackground("AchievementsScreen.jpg");

        
        returnKey = new HomeButton();
        addObject(returnKey, getWidth()-80, 750);
        addCups();
    }
    
    
    public void addCups(){
        for(int i=0; i<arr.length;i++){
            arr[i] = new Trophy();
            addObject(arr[i], 285, (92*i+260));
        }
        arr[0].setAchieved();
        arr[1].setAchieved();
    }


}