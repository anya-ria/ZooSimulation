import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.Random;
/**
 * The traitor is a child that no longer wants to cooperate with other children.
 * This character will imitate all other characters' attacks
 * 
 * @author Anya Shah | Animations
 * @author Lucas Fu  | Functions
 * @version 2024/4/8
 */
public class Traitor extends Child
{
    // Punching sprites
    private GreenfootImage[] punchAway = new GreenfootImage[6];
    private GreenfootImage[] punchToward = new GreenfootImage[6];
    private GreenfootImage[] punchRight = new GreenfootImage[6];
    private GreenfootImage[] punchLeft = new GreenfootImage[6];
    
    // Walking sprites
    private GreenfootImage[] walkAway = new GreenfootImage[9];
    private GreenfootImage[] walkToward = new GreenfootImage[9];
    private GreenfootImage[] walkRight = new GreenfootImage[9];
    private GreenfootImage[] walkLeft = new GreenfootImage[9];
    
    // Animation sprites
    private int animCounter, animDelay, animIndex;
    private int maxPunchIndex, maxWalkIndex;
    private boolean right, left, away, toward, punching;
    
    private final int throwCooldown = 50;
    private final int healCooldown = 75;
    
    private int cooldown = throwCooldown;
    private Random rand = new Random();
    
    public Traitor(){
        super(100);
        
        animCounter = 0;
        maxPunchIndex = punchAway.length;
        maxWalkIndex = walkAway.length;
        initImages();
    }
    
    public void act(){
        if(!awake) return;
        super.act();
        chaseChildren();
        animate();
        setLocation(getX(), getY());
    }
    
    private void initImages() {
        // Initialize punching images
        for(int i = 0; i < maxPunchIndex; i++) {
            punchAway[i] = new GreenfootImage("traitorPunchAway/punchAway" + i + ".png");
        }
        for(int i = 0; i < maxPunchIndex; i++) {
            punchToward[i] = new GreenfootImage("traitorPunchToward/punchToward" + i + ".png");
        }
        for(int i = 0; i < maxPunchIndex; i++) {
            punchRight[i] = new GreenfootImage("traitorPunchRight/punchRight" + i + ".png");
        }
        for(int i = 0; i < maxPunchIndex; i++) {
            punchLeft[i] = new GreenfootImage("traitorPunchRight/punchRight" + i + ".png");
            punchLeft[i].mirrorHorizontally();
        }
        
        // Initialize walking images
        for(int i = 0; i < maxWalkIndex; i++) {
            walkAway[i] = new GreenfootImage("traitorWalkAway/walkAway" + i + ".png");
        }
        for(int i = 0; i < maxWalkIndex; i++) {
            walkToward[i] = new GreenfootImage("traitorWalkToward/walkToward" + i + ".png");
        }
        for(int i = 0; i < maxWalkIndex; i++) {
            walkRight[i] = new GreenfootImage("traitorWalkRight/walkRight" + i + ".png");
        }
        for(int i = 0; i < maxWalkIndex; i++) {
            walkLeft[i] = new GreenfootImage("traitorWalkRight/walkRight" + i + ".png");
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
            if(punching) {
                if(animIndex == maxPunchIndex) {
                    animIndex = 0;
                }
                if(right) {
                    setImage(punchRight[animIndex]);
                }
                else if(left) {
                    setImage(punchLeft[animIndex]);
                }
                else if(away) {
                    setImage(punchAway[animIndex]);
                }
                else if(toward) {
                    setImage(punchToward[animIndex]);
                }
            }
            else {
                if(animIndex == maxWalkIndex) {
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
                // else {
                    // setImage("traitorWalkToward/walkToward0.png");
                // }
            }
        }
        else {
            animCounter--;
        }
    }
    
    public void chaseChildren(){
        double[] enemyDetails = detectNearestEntity(Child.class, 1000);
        double direction = enemyDetails[0];
        double distance = enemyDetails[1];
        double[] vector = Utility.angleToVector(direction);
        if(distance == -1){
            vector[0] = 0;
            vector[1] = 0;
        }
        if(hp<75 && cooldown<=0){
            selfHeal();
            cooldown = healCooldown;
        }
        if(distance<500 && distance > 10 && cooldown<=0){
            switch(rand.nextInt(2)){
                case 0:
                    throwBanana((int)direction, 4);
                    break;
                case 1:
                    throwPencil((int)direction, 4);
                    break;
            }
            cooldown = throwCooldown;
        }
        cooldown--;
        if(distance < 10){
            punch();
            return;
        }
        setLocation(getX()+vector[0]*1.2, getY()+vector[1]*1.2);
    }
    private void throwPencil(int direction, int speed){
        int modif = rand.nextInt(-10,11);
        getWorld().addObject(new Pencil(5, 150, direction+modif, speed), getX(), getY());
    }
    
    private void throwBanana(int direction, int speed){
        int modif = rand.nextInt(-10,11);
        getWorld().addObject(new Banana(direction+modif, speed), getX(), getY());
    }
    private void selfHeal(){
        getWorld().addObject(new HealingEffect(20, 10), getX(), getY());
    }
    private void punch(){
        double[] enemyDetails = detectNearestEntity(Child.class, 10);
        if(enemyDetails[1] == -1) return;
        Child enemy = getObjectsInRange(10, Child.class).get(0);
        enemy.takeDamage(10);
        enemy.push((int)enemyDetails[0], 10);
    }
}
