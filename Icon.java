import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Icon abstract class
 * 
 * @author Vanessa Huo 
 * @version 04/25/2024
 */
public abstract class Icon extends Actor
{
    protected boolean isClicked = false;
    protected int index;
    protected int value;
    
    //If the player choose another option, reset the pervious clicked one
    public void reset(){
        value = 0;
    }
    //Get the value set of variable
    public int getValue(){
        return value;
    }
    //Check if the button get clicked
    public boolean getClicked(){
        return isClicked;
    }
    //If a button was clicked, set the boolean to true. vice versa
    public void setClicked(boolean x){
        isClicked = x;
    }
}
