import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Healer here.
 * 
 * @author Anya Shah 
 * @version 04/10/2024
 */
public class Healer extends Child
{
    // Healing sprites
    private GreenfootImage[] healAway = new GreenfootImage[7];
    private GreenfootImage[] healRight = new GreenfootImage[7];
    private GreenfootImage[] healToward = new GreenfootImage[7];
    
    // Walking sprites
    
    private int animCounter;
    
    public Healer(){
        super(200);
        
        animCounter = 0;
    }
    
    public void healAway() {
        for(int i = 0; i < 7; i++) {
            healAway[i] = new GreenfootImage("healAway/healAway" + i + ".png");
        }
        setImage(healAway[animCounter++ % 8]);
    }
    
    public void healRight() {
        for(int i = 0; i < 7; i++) {
            healRight[i] = new GreenfootImage("healRight/healRight" + i + ".png");
        }
        setImage(healToward[animCounter++ % 8]);
    }
    
    public void healToward() {
        for(int i = 0; i < 7; i++) {
            healToward[i] = new GreenfootImage("healToward/healToward" + i + ".png");
        }
        setImage(healToward[animCounter++ % 8]);
    }
}
