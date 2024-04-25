import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Animals are the residents of the zoo, wandering aimlessly in their pen
 * 
 * @author Lucas
 * @version 2024/4/23
 */
public abstract class Animal extends Entity
{
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
}