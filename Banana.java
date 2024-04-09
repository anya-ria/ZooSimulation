import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Banana here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Banana extends Projectile
{
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
        this.vx = vx;
        this.vy = vy;
        getImage().scale(20, 20);
    }
    protected void detectCollision(){
        Child touched = (Child) getOneIntersectingObject(Child.class);
        if(touched!=null&&touched.isAwake()){
            touched.takeDamage(15);
            touched.push( vx*0.2 , vy*0.2 );
            getWorld().removeObject(this);
        }
    }
    protected void expire(){
        getWorld().removeObject(this); // to be changed
    }
}
