import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * The pencil is a projectile that does DOT upon impact
 * 
 * @author Lucas Fu
 * @version 04/25/2024
 */
public class Pencil extends Projectile
{
    // Animation/Images
    private GreenfootImage[] pencil = new GreenfootImage[13];
    private int animCounter, animDelay, animIndex; 
    
    // Sounds
    private static GreenfootSound[] pencilSound;
    private static int pencilSoundIndex;
    
    // Damage
    private int dot; // damage
    private int duration; // how many acts (divide by 30 for number of damage ticks)
    
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
        getImage().scale(20,20);  
        
        animCounter = 0;
        initImages();
    }
    
    public void act() {
        super.act();
        animate();
    }
    
    // ***************************** SOUNDS ******************************* \\
    public static void init() {
        pencilSoundIndex = 0;
        pencilSound = new GreenfootSound[20];
        for(int i = 0; i < pencilSound.length; i++) {
            pencilSound[i] = new GreenfootSound("pencilThrow1.mp3");
        }
    }
    public static void playPencilSound() {
        pencilSound[pencilSoundIndex].setVolume(50);
        pencilSound[pencilSoundIndex].play();
        pencilSoundIndex++;
        if(pencilSoundIndex >= pencilSound.length) {
            pencilSoundIndex = 0;
        }
    }
    
    // *************************** ANIMATION ****************************** \\
    private void initImages() {
        for(int i = 0; i < pencil.length; i++) {
            pencil[i] = new GreenfootImage("pencilSprites/pencil" + i + ".png");
            getImage().scale(20, 20);
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
    
    // *************************** FUNCTIONS ****************************** \\
    /**
     * Wounds the touched entity when touching them, also expires this
     */
    protected void detectCollision(){
        Entity touched = (Entity) getOneIntersectingObject(Entity.class);
        // if touching child is still alive
        if(touched!=null&&touched.isAwake()){
            // that child gets wounded
            touched.getWounded(dot, duration);
            expired = true;
        }        
    }
    protected void expire(){
        getWorld().removeObject(this);
    }
}
