import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Healer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Healer extends Child
{
    // Healing sprites
    private GreenfootImage[] healAway = new GreenfootImage[7];
    private GreenfootImage[] healRight = new GreenfootImage[7];
    private GreenfootImage[] healToward = new GreenfootImage[7];
    
    // Walking sprites
    
    public Healer(){
        super(200);
    }
}
