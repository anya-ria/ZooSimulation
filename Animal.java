import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Animal here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Animal extends Entity
{
    private boolean isInfected;
    private double speed;
    private double maxSpeed;
    private int direction;
    
    public boolean update(){
        return super.update();
    }
    
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
        speed = 2;
        GreenfootImage zombie = new GreenfootImage("");
    }
}