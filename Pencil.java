import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The pencil is a projectile that does DOT upon impact
 * 
 * @author Lucas
 * @version 2024/4/12
 */
public class Pencil extends Projectile
{
    private GreenfootImage[] pencil = new GreenfootImage[13];
    private int animCounter, animDelay, animIndex; 
    private int dot; // damage
    private int duration; // how many acts (divide by 30 for number of ticks)
    /**
     * Constructs a new Pencil using angle and speed
     * @param dmg       the damage to be taken every tick (30 acts)
     * @param duration  the duration in terms of acts
     * @param angle     the direction in which to throw
     * @param speed     the speed at which the pencil moves
     */
    public Pencil(int dmg, int duration, int angle, double speed){
        this(dmg, duration,
             Utility.angleToVector(angle)[0]*speed, 
             Utility.angleToVector(angle)[1]*speed);
    }
    /**
     * Constructs a new Pencil using velocities
     * @param dmg       the damage to be taken every tick (30 acts)
     * @param duration  the duration in terms of acts
     * @param vx        the horizontal velocity
     * @param vy        the vertical velocity
     */
    public Pencil(int dmg, int duration, double vx, double vy){
        super(vx, vy);
        dot = dmg;
        this.duration = duration;
        //getImage().scale(20,20);  
        
        animCounter = 0;
        initImages();
    }
    
    // public void act() {
        // animate();
    // }
    
    private void initImages() {
        for(int i = 0; i < pencil.length; i++) {
            pencil[i] = new GreenfootImage("pencilSprites/pencil" + i + ".png");
        }
        
        animIndex = 0;
        animDelay = 3; // smaller value: goes faster || larger value: goes slower
        animCounter = animDelay;
    }
    
    private void animate() {
        if(animCounter == 0) {
            animCounter = animDelay;
            animIndex++;
            if(animIndex >= pencil.length) {
                animIndex = 0;
            }
            setImage(pencil[animIndex]);
        }
        else {
            animCounter--;
        }
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
