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
    
    private final int maxThrowCooldown = 50;
    private final int maxHealCooldown = 100;
    private final int maxSmashCooldown = 500;
    
    private int throwCooldown = maxThrowCooldown;
    private int healCooldown = maxHealCooldown;
    private int smashCooldown = 0;
    private int stunDuration = 0;
    
    private int revives = 3;
    
    private Random rand = new Random();
    
    public Traitor(){
        super(200);
        
        animCounter = 0;
        maxPunchIndex = punchAway.length;
        maxWalkIndex = walkAway.length;
        initImages();
    }
    
    public void act(){
        if(!awake) return;
        super.act();
        animate();
        if(slippedDuration>0){
            slippedDuration--;
            setLocation(getX(), getY());
            return;
        } else if(slippedDuration==0){
            setRotation(0);
            slippedDuration--; // effectively only makes this code run once
        }
        if(stunDuration>0){ // essentially the same as slippedDuration
            stunDuration--;
            setLocation(getX(), getY());
            return;
        }
        chaseChildren();
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
                if(animIndex >= maxPunchIndex) {
                    animIndex = 0;
                    punching = false;
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
    
    public void chaseChildren(){
        double[] enemyDetails = detectNearestEntity(Child.class, 1000);
        double direction = enemyDetails[0];
        double distance = enemyDetails[1];
        double[] vector = Utility.angleToVector(direction);
        if(distance == -1){
            vector[0] = 0;
            vector[1] = 0;
        }
        if(hp<150 && healCooldown<=0){
            selfHeal();
            healCooldown = maxHealCooldown;
            throwCooldown = maxHealCooldown;
        }
        if(distance<500 && distance>50 && throwCooldown<=0){
            switch(rand.nextInt(2)){
                case 0:
                    throwBanana((int)direction, 4);
                    break;
                case 1:
                    throwPencil((int)direction, 4);
                    break;
            }
            throwCooldown = maxThrowCooldown;
        }
        if(distance>=10 && distance<100 && hp>=100 && smashCooldown<=0){
            smash();
        }
        throwCooldown--; healCooldown--; smashCooldown--;
        if(distance < 10){
            punch();
            return;
        }
        setLocation(getX()+vector[0]*1.2, getY()+vector[1]*1.2);
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
    
    // traitor moves
    private void throwPencil(int direction, int speed){
        int modif = rand.nextInt(-10,11);
        getWorld().addObject(new Pencil(5, 150, direction+modif, speed), getX(), getY());
    }
    private void throwBanana(int direction, int speed){
        int modif = rand.nextInt(-10,11);
        getWorld().addObject(new Banana(direction+modif, speed), getX(), getY());
    }
    private void selfHeal(){
        getWorld().addObject(new HealingEffect(20, 40), getX(), getY());
    }
    private void smash(){
        getWorld().addObject(new SmashEffect(200, 99), getX(), getY());
        selfHeal(); selfHeal();
        smashCooldown = maxSmashCooldown;
        stunDuration = 200;
    }
    private void punch(){
        punching = true;
        double[] enemyDetails = detectNearestEntity(Child.class, 10);
        if(enemyDetails[1] == -1) return;
        Child enemy = getObjectsInRange(10, Child.class).get(0);
        enemy.takeDamage(10);
        enemy.push((int)enemyDetails[0], 10);
    }
    private void revive(){
        wound[0] = 0; wound[1] = 0;
        for(int i=0; i<5; i++){
            selfHeal();
        }
        for(Entity e: getObjectsInRange(150, Entity.class)){
            double vx = (150-Math.abs(e.getX()-getX()))*Math.signum(e.getX()-getX())/8.0;
            double vy = (150-Math.abs(e.getY()-getY()))*Math.signum(e.getY()-getY())/8.0;
            e.push(vx, vy);
        }
        for(int i=0; i<360; i+=10){
            getWorld().addObject(new Banana(i, 8), getX(), getY());
            getWorld().addObject(new Pencil(20, 150, i+5, 8), getX(), getY());
        }
    }
    
    /**
     * @override, gives traitor ability to revive once
     */
    public void die(){
        if(revives>0){
            revives--;
            revive();
        } else {
            super.die();
        }
    }
}
