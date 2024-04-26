import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Animals are the residents of the zoo, wandering aimlessly in their pen
 * 
 * @author Lucas Fu
 * @version 04/25/2024
 */
public abstract class Animal extends Entity
{
    // animation variables
    protected boolean left, right, away, toward;
    protected int direction;
    
    private boolean isZombie = false;
    
    /**
     * When constructed, sets the max hp and the hp
     * @param maxHp   the maximum hp the child can have
     */
    protected Animal(int maxHp){
        super(maxHp);
        enableStaticRotation();
    }
    
    /**
     * This animal has turned into a zombie! removes the previous animal and replace with the zombie form
     */
    public void zombify()
    {
        if(this instanceof Hippo) getWorld().addObject(new ZombieHippo(), getX(), getY()); this.isZombie = true;
        if(this instanceof Monkey) getWorld().addObject(new ZombieMonkey(), getX(), getY()); this.isZombie = true;
        if(this instanceof Penguin) getWorld().addObject(new ZombiePenguin(), getX(), getY()); this.isZombie = true;
        getWorld().removeObject(this);
    }
    
    /**
     * @override
     * When dying, remove the static rotation aspect so that this actually looks dead
     */
    protected void die(){
        disableStaticRotation();
        super.die();
    }
    
    /**
     * Changes the image according to the rotation, and changes rotation to direction value
     */
    protected void adjustDirection(){
        setRotation(direction);
        left = false; right = false; away = false; toward = false;
        if (direction >= 315 || direction <= 45) // right
        {
            right = true;
        }
        if (direction > 45 && direction <= 135) // down
        {   
            toward = true;
        }
        if (direction > 135 && direction <= 225) // left
        {
            left = true;
        }
        if (direction > 225 && direction <= 315) // up
        {
            away = true; 
        }
    }
}