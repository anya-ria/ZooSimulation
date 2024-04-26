import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Standard projectile thrown by monkeys and traitors, leaves a banana peel when expiring
 * 
 * @author <li> Lucas Fu  | Functions
 * @author <li> Anya Shah | Animations + Sounds
 * @version 04/25/2024
 */
public class Banana extends Projectile
{
    // Animation/Images
    private GreenfootImage[] banana = new GreenfootImage[5];
    private int animCounter, animDelay, animIndex;
    
    // Sounds
    private static GreenfootSound[] bananaSound;
    private static int bananaSoundIndex;
    
    // Damage
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
        getImage().scale(30, 30);
        initImages();
        
        animCounter = 0;
    } 
    public void act(){
        super.act();
        animate();
    }
    
    // **************************** SOUNDS ******************************* \\
    /**
     * Initialize banana sounds
     */
    public static void init() {
        bananaSoundIndex = 0;
        bananaSound = new GreenfootSound[20];
        for(int i = 0; i < bananaSound.length; i++) {
            bananaSound[i] = new GreenfootSound("throwBanana.mp3");
        }
    }
    /**
     * Plays banana sounds
     */
    public static void playBananaSound() {
        bananaSound[bananaSoundIndex].setVolume(50);
        bananaSound[bananaSoundIndex].play();
        bananaSoundIndex++;
        if(bananaSoundIndex >= bananaSound.length) {
            bananaSoundIndex = 0;
        }
    }
    
    // **************************** ANIMATION ***************************** \\
    /**
     * Initialize the banana images
     */
    private void initImages() {
        for(int i = 0; i < banana.length; i++) {
            banana[i] = new GreenfootImage("bananaSprites/banana" + i + ".png");
            banana[i].scale(30, 30);
        }
        
        animIndex = 0;
        animDelay = 5;
        animCounter = animDelay;
    }
    /**
     * Animate the banana
     */
    private void animate() {
        if(animCounter == 0) {
            animCounter = animDelay;
            animIndex++;
            if(animIndex >= banana.length) {
                animIndex = 0;
            }
            setImage(banana[animIndex]);
        }
        else {
            animCounter--;
        }
    }
    
    // **************************** FUNCTION ***************************** \\
    /**
     * Does damage and minor pushing to the child touching this, expiring this
     */
    protected void detectCollision(){
        Child touched = (Child) getOneIntersectingObject(Child.class);
        // if touching a child that is still alive
        if(touched!=null&&touched.isAwake()){
            // that child take damage
            touched.takeDamage(damage);
            // that child gets pushed
            touched.push( vx*0.2 , vy*0.2 );
            // this expires
            expired = true;
            // the Zoo counts a hit
            Zoo.hit();
        }
    }
    /**
     * Creates a peel behind the banana when expiring
     */
    protected void expire(){
        getWorld().addObject(new Peel(), (int)(getX()+vx*10), (int)(getY()+vy*10));
        getWorld().removeObject(this);
    }
}
