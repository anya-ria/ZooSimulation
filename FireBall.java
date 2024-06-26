import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * the Fireball is a projectile shot by the boss, dealing damage to those it touches
 * 
 * @author Lucas Fu
 * @version 04/25/2024
 */
public class FireBall extends Projectile
{
    private int damage = 30;
    private int animCounter, animDelay, animIndex; 
    /**
     * constructs a new FireBall with a speed and direction
     * @param angle the direction in degrees
     * @param speed the magnitude of speed
     */
    public FireBall(int angle, double speed){
        this(Utility.angleToVector(angle)[0]*speed, 
             Utility.angleToVector(angle)[1]*speed);
    }
    /**
     * constructs a new banana directly using vx and vy
     * @param vx the x-speed
     * @param vy the y-speed
     */
    public FireBall(double vx, double vy){
        super(vx,vy);
        getImage().scale(40, 40);
        getImage().rotate((int)Utility.vectorToAngle(vx, vy)+225);
        animIndex = 0;
        animDelay = 5;
        animCounter = animDelay;
    } 
     
    public void act(){
        super.act();
        animate();
    }
    
    private void animate() {
        if(animCounter == 0) {
            animCounter = animDelay;
            animIndex++;
            if(animIndex >= 5) {
                animIndex = 0;
            }
        }
        else {
            animCounter--;
        }
    }
    
    /**
     * Does damage and minor pushing to the child touching this, without expiring
     */
    protected void detectCollision(){
        Child touched = (Child) getOneIntersectingObject(Child.class);
        if(touched!=null&&touched.isAwake()){
            touched.takeDamage(damage);
            touched.push(vx, vy);
        }
    }
    /**
     * Creates a peel when expired
     */
    protected void expire(){
        getWorld().removeObject(this);
    }
}
