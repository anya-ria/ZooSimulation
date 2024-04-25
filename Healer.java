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
    private int animCounter, animDelay, animIndex;
    private int maxHealIndex, maxWalkIndex;
    private boolean right, left, away, toward, healing;

    // healing variables
    private final int MAX_AOE_COOLDOWN = 200;
    private final int MAX_PROJ_COOLDOWN = 80;

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
        if(stunDuration>0){
            stunDuration--;
            setLocation(getX(), getY());
            healing = true;
            return;
        } else {
            healing = false;
        }
        double[] allyDetails = detectNearestEntity(Child.class, 2000);
        followAlly(allyDetails);
        checkHeal(allyDetails);
    }

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
                if(animIndex >= maxWalkIndex) {
                    animIndex = 0;
                }
                if(right) {
                    setImage(walkRight[animIndex]);
                }
                else if(left) {
                    setImage(walkLeft[animIndex]);
                }
                else if(away) {
                    setImage(walkAway[animIndex]);
                }
                else if(toward) {
                    setImage(walkToward[animIndex]);
                }
            }
        }
        else {
            animCounter--;
        }
    }

    private void followAlly(double[] details){
        double[] vector;
        if(details[1] >= 100) // too far
            vector = Utility.angleToVector(details[0]);
        else if(details[1] <= 75 && details[1] != -1) // too close
            vector = Utility.angleToVector(details[0]+180);
        else
            vector = new double[] {0, 0}; 
        if(stunDuration<=0){
            setLocation(getX()+vector[0], getY()+vector[1]);
            // update direction variables
            updateDirection(vector);
        }
    }

    private void checkHeal(double[] details){
        double direction = details[0];
        double distance = details[1];
        if(distance<=100 && aoeCooldown<=0){
            getWorld().addObject(new HealingEffect(220, 40), getX(), getY());
            aoeCooldown = MAX_AOE_COOLDOWN;
            stunDuration = 120;
        }
        if(distance>=65 && distance < 500 && projCooldown<=0){
            getWorld().addObject(new HealingBall((int)direction, 8, 10), getX(), getY());
            projCooldown = MAX_PROJ_COOLDOWN;
        }
        aoeCooldown--; projCooldown--;
    }
}
