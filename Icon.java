import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Icon abstract class
 * 
 * @author Vanessa Huo 
 * @version 2024/04
 */
public abstract class Icon extends Actor
{
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
