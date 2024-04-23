import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Animal here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Animal extends Entity
{
    protected int direction;

    /**
     * When constructed, sets the max hp and the hp
     * @param maxHp   the maximum hp the child can have
     */
    protected Animal(int maxHp){
        super(maxHp);
        enableStaticRotation();
    }
    
    public void zombify()
    {
        if(this instanceof Hippo) getWorld().addObject(new ZombieHippo(), getX(), getY());
        if(this instanceof Monkey) getWorld().addObject(new ZombieMonkey(), getX(), getY());
        if(this instanceof Penguin) getWorld().addObject(new ZombiePenguin(), getX(), getY());
        getWorld().removeObject(this);
    }
    
    protected void die(){
        disableStaticRotation();
        super.die();
    }
}