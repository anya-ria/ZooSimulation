import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Fights zombies...
 * 
 * @author Lucas Fu, Anya Shah
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
    
    private int animCounter;
    
    public Fighter(){
        super(200);
        
        animCounter = 0;
    }
    
    // 4 Fighting animations
    public void fightAway() {
        for(int i = 0; i < 6; i++) {
            fightAway[i] = new GreenfootImage("fightAway/fightAway" + i + ".png");
            setImage(fightAway[animCounter++ % 6]);
        }
    }
    public void fightToward() {
        for(int i = 0; i < 6; i++) {
            fightToward[i] = new GreenfootImage("fightToward/fightToward" + i + ".png");
            setImage(fightToward[animCounter++ % 6]);
        }
    }
    public void fightRight() {
        for(int i = 0; i < 6; i++) {
            fightRight[i] = new GreenfootImage("fightRight/fightRight" + i + ".png");
            setImage(fightRight[animCounter++ % 6]);
        }
    }
    public void fightLeft() {
        for(int i = 0; i < 6; i++) {
            fightLeft[i] = new GreenfootImage("fightRight/fightRight" + i + ".png");
            fightLeft[i].mirrorHorizontally();
            setImage(fightLeft[animCounter++ % 6]);
        }
    }
    
    // 4 Walking animations
    public void fighterWalkAway() {
        for(int i = 0; i < 9; i++) {
            walkAway[i] = new GreenfootImage("fighterWalkAway/fighterWalkAway" + i + ".png");
            setImage(walkAway[animCounter++ % 6]);
        }
    }
    public void fighterWalkToward() {
        for(int i = 0; i < 9; i++) {
            walkToward[i] = new GreenfootImage("fighterWalkToward/fighterWalkToward" + i + ".png");
            setImage(walkToward[animCounter++ % 6]);
        }
    }
    public void fighterWalkRight() {
        for(int i = 0; i < 9; i++) {
            walkRight[i] = new GreenfootImage("fighterWalkRight/fighterWalkRight" + i + ".png");
            setImage(walkRight[animCounter++ % 6]);
        }
    }
    public void fighterWalkLeft() {
        for(int i = 0; i < 9; i++) {
            walkLeft[i] = new GreenfootImage("fighterWalkRight/fighterWalkRight" + i + ".png");
            setImage(walkLeft[animCounter++ % 6]);
        }
    }
}
