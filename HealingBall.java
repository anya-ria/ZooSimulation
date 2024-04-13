import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The healing ball is a projectile shot by the healer to heal children.
 * Creates a healing effect when expiring.
 * 
 * @author Lucas 
 * @version 2024/4/10
 */
public class HealingBall extends Projectile
{
    private int healingValue;
    /**
     * constructs a new HealingBall with a speed, direction, and healing value
     * @param angle    the direction in degrees
     * @param speed    the magnitude of speed
     * @param healing  the healing value
     */
    public HealingBall(int angle, double speed, int healing){
        this(Utility.angleToVector(angle)[0]*speed, 
             Utility.angleToVector(angle)[1]*speed,
             healing);
    }
    /**
     * constructs a new HealingBall directly using vx, vy, and healing
     * @param vx       the x-speed
     * @param vy       the y-speed
     * @param healing  the healing value
     */
    public HealingBall(double vx, double vy, int healing){
        super(vx,vy);
        getImage().scale(20, 20);
        healingValue = healing;
    }
    public void expire(){
        getWorld().addObject(new HealingEffect(50, healingValue), getX(), getY());
        getWorld().removeObject(this);
    }
    public void detectCollision(){
        Child touched = (Child) getOneIntersectingObject(Child.class);
        if(touched!=null&&touched.isAwake()&&!(touched instanceof Traitor)){
            touched.push( vx*0.4 , vy*0.4 );
            expired = true;
        }
    }
}
