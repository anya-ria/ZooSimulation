import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Icons here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Icon extends Actor
{

    
    /**
     * Act - do whatever the Icons wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    protected boolean isClicked = false;
    protected int index;
    protected int value;
    
    public void reset(){
        value = 0;
    }
    
    public int getValue(){
        return value;
    }

    public boolean getClicked(){
        return isClicked;
    }
    
    public void setClicked(boolean x){
        isClicked = x;
    }
    

}
