import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Standard projectile thrown by monkeys and traitors, leaves a banana peel when expiring
 * 
 * @author Lucas
 * @version 2024/4/9
 */
public class Banana extends Projectile
{
    private int damage = 15;
    /**
     * constructs a new Banana with a speed and direction
     * @param angle the direction in degrees
     * @param speed the magnitude of speed
     */
    public Banana(int angle, double speed){
        this(Utility.angleToVector(angle)[0]*speed, 
             Utility.angleToVector(angle)[1]*speed);
    }
    /**
     * constructs a new banana directly using vx and vy
     * @param vx the x-speed
     * @param vy the y-speed
     */
    public Banana(double vx, double vy){
        super(vx,vy);
        getImage().scale(20, 20);
    }
    protected void detectCollision(){
        Child touched = (Child) getOneIntersectingObject(Child.class);
        if(touched!=null&&touched.isAwake()){
            touched.takeDamage(damage);
            touched.push( vx*0.2 , vy*0.2 );
            expired = true;
        }
    }
    protected void expire(){
        getWorld().removeObject(this); // to be changed
    }
}
