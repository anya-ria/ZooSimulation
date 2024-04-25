import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The healer likes to help fellow children with some clerical magic
 * 
 * @author <li> Anya Shah | Animations
 * @author <li> Lucas Fu  | Functions
 * @version 04/12/2024
 */
public class Healer extends Child
{
    // Healing sprites
    private GreenfootImage[] healAway = new GreenfootImage[7];
    private GreenfootImage[] healRight = new GreenfootImage[7];
    private GreenfootImage[] healLeft = new GreenfootImage[7];
    private GreenfootImage[] healToward = new GreenfootImage[7];

    // Animation variables
    private int maxHealIndex;
    private boolean healing;

    // healing max cooldowns
    private final int MAX_AOE_COOLDOWN = 200;
    private final int MAX_PROJ_COOLDOWN = 80;

    // healing cooldowns
    private int aoeCooldown = MAX_AOE_COOLDOWN;
    private int projCooldown = MAX_PROJ_COOLDOWN;
    private int stunDuration = 0;

    public Healer(){
        super(300);

        animCounter = 0;
        maxHealIndex = healAway.length;
        maxWalkIndex = walkAway.length;
        initImages();
    }

    public void act(){
        if(!super.update()) return;
        // if this is stunned (because recovering from AOE healing)
        if(stunDuration>0){
            // count down stun duration
            stunDuration--;
            // still call a setLocation for physics to still run
            setLocation(getX(), getY());
            // toggles healing flag for animation
            healing = true;
            // skips the rest of the act method
            return;
        } 
        // if not stunned..
        else {
            // un-toggles the healing flag for animation
            healing = false;
        }
        // finds nearest child
        double[] allyDetails = detectNearestEntity(Child.class, 2000);
        // follow them
        followAlly(allyDetails);
        // heal them
        checkHeal(allyDetails);
    }

    // ********************** IMAGES AND ANIMATIONS ********************** \\
    private void initImages() {
        // Initialize healing images 
        for(int i = 0; i < maxHealIndex; i++) {
            healAway[i] = new GreenfootImage("healAway/healAway" + i + ".png");
        }
        for(int i = 0; i < maxHealIndex; i++) {
            healToward[i] = new GreenfootImage("healToward/healToward" + i + ".png");
        }
        for(int i = 0; i < maxHealIndex; i++) {
            healRight[i] = new GreenfootImage("healRight/healRight" + i + ".png");
        }
        for(int i = 0; i < maxHealIndex; i++) {
            healLeft[i] = new GreenfootImage("healRight/healRight" + i + ".png");
            healLeft[i].mirrorHorizontally();
        }

        // Initialize walking images
        for(int i = 0; i < maxWalkIndex; i++) {
            walkAway[i] = new GreenfootImage("healerWalkAway/healerWalkAway" + i + ".png");
        }
        for(int i = 0; i < maxWalkIndex; i++) {
            walkToward[i] = new GreenfootImage("healerWalkToward/healerWalkToward" + i + ".png");
        }
        for(int i = 0; i < maxWalkIndex; i++) {
            walkRight[i] = new GreenfootImage("healerWalkRight/healerWalkRight" + i + ".png");
        }
        for(int i = 0; i < maxWalkIndex; i++) {
            walkLeft[i] = new GreenfootImage("healerWalkRight/healerWalkRight" + i + ".png");
            walkLeft[i].mirrorHorizontally();
        }
        setImage(walkToward[0]);
        animIndex = 0;
        animDelay = 8;
        animCounter = animDelay;
    }

    protected void animate() {
        if(animCounter == 0) {
            animCounter = animDelay;
            animIndex++;
            if(healing){
                if(animIndex >= maxHealIndex) {
                    animIndex = 0;
                }
                if(right) {
                    setImage(healRight[animIndex]);
                }
                else if(left) {
                    setImage(healLeft[animIndex]);
                }
                else if(away) {
                    setImage(healAway[animIndex]);
                }
                else if(toward) {
                    setImage(healToward[animIndex]);
                }
            } else {
                updateWalking();
            }
        }
        else {
            animCounter--;
        }
    }

    // ************************* HEALING SECTION ************************** \\
    private void followAlly(double[] details){
        // vector is an array comprising of x-velocity and y-velocity
        double[] vector;
        // if target is too far (out of AOE healing range)
        if(details[1] >= 100)
            // move towards the target
            vector = Utility.angleToVector(details[0]);
            
        // if target is too close but still exists (healing ball cannot hit)
        else if(details[1] <= 75 && details[1] != -1)
            // move away from the target
            vector = Utility.angleToVector(details[0]+180);
            
        // if target does not exist
        else
            // do not move
            vector = new double[] {0, 0}; 
           
        // updates location
        setLocation(getX()+vector[0], getY()+vector[1]);
        // update direction variables for animation
        updateDirection(vector);
    }

    private void checkHeal(double[] details){
        // parse the details array
        double direction = details[0];
        double distance = details[1];
        
        // if the target is close enough to heal using aoe heal, and it's off cooldown
        if(distance<=100 && aoeCooldown<=0){
            // creates the aoe healing effect
            getWorld().addObject(new HealingEffect(220, 40), getX(), getY());
            // resets the cooldown
            aoeCooldown = MAX_AOE_COOLDOWN;
            // stuns the healer for 120 acts
            stunDuration = 120;
        }
        
        // if the target is withing throwing range and that's off cooldown
        if(distance>=65 && distance < 500 && projCooldown<=0){
            // launches a healing ball that flies at 8px/s and heals for 10hp
            getWorld().addObject(new HealingBall((int)direction, 8, 10), getX(), getY());
            // resets the cooldown
            projCooldown = MAX_PROJ_COOLDOWN;
        }
        
        // reduces the cooldown at the end each act
        aoeCooldown--; projCooldown--;
    }
}
