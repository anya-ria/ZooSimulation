import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The healer likes to help fellow children with some clerical magic
 * 
 * @author Anya Shah | Animations
 * @author Lucas Fu  | Functions
 * @version 04/12/2024
 */
public class Healer extends Child
{
    // Healing sprites
    private GreenfootImage[] healAway = new GreenfootImage[7];
    private GreenfootImage[] healRight = new GreenfootImage[7];
    private GreenfootImage[] healLeft = new GreenfootImage[7];
    private GreenfootImage[] healToward = new GreenfootImage[7];

    // Walking sprites
    private GreenfootImage[] walkAway = new GreenfootImage[9];
    private GreenfootImage[] walkRight = new GreenfootImage[9];
    private GreenfootImage[] walkLeft = new GreenfootImage[9];
    private GreenfootImage[] walkToward = new GreenfootImage[9];

    // Animation variables
    private int animCounter, animDelay, animIndex;
    private int maxHealIndex, maxWalkIndex;
    private boolean right, left, away, toward, healing;

    // healing variables
    private final int maxAoeCooldown = 200;
    private final int maxProjCooldown = 80;

    private int aoeCooldown = maxAoeCooldown;
    private int projCooldown = maxProjCooldown;
    private int stunDuration = 0;

    public Healer(){
        super(300);

        animCounter = 0;
        maxHealIndex = healAway.length;
        maxWalkIndex = walkAway.length;
        initImages();
    }

    public void act(){
        if(!awake) return;
        super.act();
        animate();
        if(stunDuration>0){
            stunDuration--;
            setLocation(getX(), getY());
            healing = true; // When healing = true, set animIndex = 0
            return;
        } else {
            healing = false;
        }
        if(slippedDuration>0){
            slippedDuration--;
            setLocation(getX(), getY());
            return;
        } else if(slippedDuration==0){
            setRotation(0);
            slippedDuration--; // effectively only makes this code run once
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

    private void animate() {
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
        if(details[1] >= 95) // too far
            vector = Utility.angleToVector(details[0]);
        else if(details[1] <= 70 && details[1] != -1) // too close
            vector = Utility.angleToVector(details[0]+180);
        else
            vector = new double[] {0, 0}; 
        if(stunDuration<=0){
            setLocation(getX()+vector[0], getY()+vector[1]);
            // update facing direction
            if(vector[0]>0 && Math.abs(vector[0])>Math.abs(vector[1])) {
                right = true;
                left = false; toward = false; away = false;
            }
            else if(vector[0]<0 && Math.abs(vector[0])>Math.abs(vector[1])) {
                left = true;
                right = false; toward = false; away = false;
            }
            else if(vector[1]<0 && Math.abs(vector[0])<Math.abs(vector[1])) {
                away = true;
                left = false; right = false; toward = false;
            }
            else if(vector[1]>0 && Math.abs(vector[0])<Math.abs(vector[1])) {
                toward = true; 
                left = false; right = false; away = false;
            }
        }
    }

    private void checkHeal(double[] details){
        double direction = details[0];
        double distance = details[1];
        if(distance<=100 && aoeCooldown<=0){
            getWorld().addObject(new HealingEffect(200, 40), getX(), getY());
            aoeCooldown = maxAoeCooldown;
            stunDuration = 120;
        }
        if(distance>=65 && distance < 500 && projCooldown<=0){
            getWorld().addObject(new HealingBall((int)direction, 8, 10), getX(), getY());
            projCooldown = maxProjCooldown;
        }
        aoeCooldown--; projCooldown--;
    }
}
