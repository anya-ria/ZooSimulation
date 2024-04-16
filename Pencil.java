import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The pencil is a projectile that does DOT upon impact
 * 
 * @author Lucas
 * @version 2024/4/12
 */
public class Pencil extends Projectile
{
    private int dot = 5; // damage
    private int duration = dot*30; // how many acts (divide by 30 for number of ticks)
    /**
     * Constructs a new Pencil using angle and speed
     * @param angle     the direction in which to throw
     * @param speed     the speed at which the pencil moves
     */
    public Pencil(int angle, double speed){
        this(Utility.angleToVector(angle)[0]*speed, 
             Utility.angleToVector(angle)[1]*speed);
    }
    /**
     * Constructs a new Pencil using velocities
     * @param vx        the horizontal velocity
     * @param vy        the vertical velocity
     */
    public Pencil(double vx, double vy){
        super(vx, vy);
        getImage().scale(20,20);  
    }
    protected void detectCollision(){
        Entity touched = (Entity) getOneIntersectingObject(Entity.class);
        if(touched!=null&&touched.isAwake()){
            touched.getWounded(dot, duration);
            expired = true;
        }        
    }
    protected void expire(){
        getWorld().removeObject(this);
    }
}
