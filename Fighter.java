import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Fights zombies...
 * 
 * @author Anya Shah | Animations
 * @author Lucas Fu  | Functions
 * @version 2024/4/8
 */
public class Fighter extends Child
{
    // Fighting sprites
    private GreenfootImage[] fightAway = new GreenfootImage[6];
    private GreenfootImage[] fightRight = new GreenfootImage[6];
    private GreenfootImage[] fightLeft = new GreenfootImage[6];
    private GreenfootImage[] fightToward = new GreenfootImage[6];

    // Walking sprites
    private GreenfootImage[] walkAway = new GreenfootImage[9];
    private GreenfootImage[] walkRight = new GreenfootImage[9];
    private GreenfootImage[] walkLeft = new GreenfootImage[9];
    private GreenfootImage[] walkToward = new GreenfootImage[9];

    // Animation variables
    private int animCounter, animDelay, animIndex;
    private int maxFightIndex, maxWalkIndex;
    private boolean right, away, fightingRight, fightingAway;

    public Fighter(){
        super(200);

        animCounter = 0;
        maxFightIndex = fightAway.length;
        maxWalkIndex = walkAway.length;
        initImages();
    }

    private void initImages() {
        // Initialize 4 fighting images
        for(int i = 0; i < maxFightIndex; i++) {
            fightAway[i] = new GreenfootImage("fightAway/fightAway" + i + ".png");
        }
        for(int i = 0; i < maxFightIndex; i++) {
            fightToward[i] = new GreenfootImage("fightToward/fightToward" + i + ".png");
        }
        for(int i = 0; i < maxFightIndex; i++) {
            fightRight[i] = new GreenfootImage("fightRight/fightRight" + i + ".png");
        }
        for(int i = 0; i < maxFightIndex; i++) {
            fightLeft[i] = new GreenfootImage("fightRight/fightRight" + i + ".png");
            fightLeft[i].mirrorHorizontally();
        }

        // Initialize 4 walking images
        for(int i = 0; i < maxWalkIndex; i++) {
            walkAway[i] = new GreenfootImage("fighterWalkAway/fighterWalkAway" + i + ".png");
        }
        for(int i = 0; i < maxWalkIndex; i++) {
            walkToward[i] = new GreenfootImage("fighterWalkToward/fighterWalkToward" + i + ".png");
        }
        for(int i = 0; i < maxWalkIndex; i++) {
            walkRight[i] = new GreenfootImage("fighterWalkRight/fighterWalkRight" + i + ".png");
        }
        for(int i = 0; i < maxWalkIndex; i++) {
            walkLeft[i] = new GreenfootImage("fighterWalkRight/fighterWalkRight" + i + ".png");
        }

        animIndex = 0;
        animDelay = 5;
        animCounter = animDelay;
    }

    private void animateWalking() {
        if(animCounter == 0) {
            animCounter = animDelay;
            animIndex++;
            if(animIndex == maxWalkIndex) {
                animIndex = 0;
            }
            if(right) {
                setImage(walkRight[animIndex]);
            }
            if(!right) {
                setImage(walkLeft[animIndex]);
            }
            if(away) {
                setImage(walkAway[animIndex]);
            }
            if(!away) {
                setImage(walkToward[animIndex]);
            }
        }
        else {
            animCounter--;
        }
    }
    
    private void animateFighting() {
        if(animCounter == 0) {
            animCounter = animDelay;
            animIndex++;
            if(animIndex == maxFightIndex) {
                animIndex = 0;
            }
            if(fightingRight) {
                setImage(fightRight[animIndex]);
            }
            if(!fightingRight) {
                setImage(fightLeft[animIndex]);
            }
            if(fightingAway) {
                setImage(fightAway[animIndex]);
            }
            if(!fightingAway) {
                setImage(fightToward[animIndex]);
            }
        }
        else {
            animCounter--;
        }
    }
}
