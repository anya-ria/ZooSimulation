import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * A child is an abstact class that represents all the children attending the zoo.
 * 
 * @author Lucas
 * @version 2024/4/4
 */
public abstract class Child extends Entity
{
    // Walking sprites
    protected GreenfootImage[] walkAway = new GreenfootImage[9];
    protected GreenfootImage[] walkRight = new GreenfootImage[9];
    protected GreenfootImage[] walkLeft = new GreenfootImage[9];
    protected GreenfootImage[] walkToward = new GreenfootImage[9];
    public Child(int maxHp){
        super(maxHp);
    }
    public boolean update(){
        return super.update();
    }
    protected abstract void animate();
}
