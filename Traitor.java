import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * The traitor is a child that no longer wants to cooperate with other children.
 * <li> This character will imitate all other characters' attacks.
 * <li> Also has his own special move: Ground Pound
 * 
 * @author <li> Anya Shah | Animations
 * @author <li> Lucas Fu  | Functions
 * @version 04/08/2024
 */
public class Traitor extends Child
{
    // Punching sprites
    private GreenfootImage[] punchAway = new GreenfootImage[6];
    private GreenfootImage[] punchToward = new GreenfootImage[6];
    private GreenfootImage[] punchRight = new GreenfootImage[6];
    private GreenfootImage[] punchLeft = new GreenfootImage[6];
    
    // Animation sprites
    private int animCounter, animDelay, animIndex;
    private int maxPunchIndex, maxWalkIndex;
    private boolean right, left, away, toward, punching;
    
    private final int MAX_THROW_COOLDOWN = 50;
    private final int MAX_HEAL_COOLDOWN = 100;
    private final int MAX_SMASH_COOLDOWN = 500;
    
    private int throwCooldown = MAX_THROW_COOLDOWN;
    private int healCooldown = MAX_HEAL_COOLDOWN;
    private int smashCooldown = 0;
    private int stunDuration = 0;
    
    private int revives = 3;
    
    public Traitor(){
        super(200);
        
        animCounter = 0;
        maxPunchIndex = punchAway.length;
        maxWalkIndex = walkAway.length;
        initImages();
    }
    
    public void act(){
        if(!super.update()) return;
        if(stunDuration>0){ // currently stunned
            stunDuration--;
            animCounter = 10; // make sure this guy isn't animating when stunned
            setLocation(getX(), getY());
            return;
        }
        double[] childDetails = detectNearestEntity(Child.class, 1000);
        chaseChildren(childDetails);
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
    
    protected void animate() {
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
    
    private void chaseChildren(double[] enemyDetails){
        double direction = enemyDetails[0];
        double distance = enemyDetails[1];
        double[] vector = Utility.angleToVector(direction);
        if(distance == -1){
            vector[0] = 0;
            vector[1] = 0;
        }
        if(hp<150 && healCooldown<=0){
            punching = false;
            selfHeal();
            healCooldown = MAX_HEAL_COOLDOWN;
            throwCooldown = MAX_HEAL_COOLDOWN;
        }
        if(distance<500 && distance>50 && throwCooldown<=0){
            switch(rand.nextInt(2)){
                case 0:
                    throwBanana((int)direction, 6);
                    break;
                case 1:
                    throwPencil((int)direction, 6);
                    break;
            }
            punching = true;
            throwCooldown = MAX_THROW_COOLDOWN;
        }
        if(distance>=10 && distance<100 && hp>=100 && smashCooldown<=0){
            smash();
            punching = true;
        }
        throwCooldown--; healCooldown--; smashCooldown--;
        if(distance < 15){
            punch();
            punching = true;
            setLocation(getX(), getY());
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
        Greenfoot.playSound("pencilThrow2.mp3");
    }
    private void throwBanana(int direction, int speed){
        int modif = rand.nextInt(-10,11);
        getWorld().addObject(new Banana(direction+modif, speed), getX(), getY());
        Greenfoot.playSound("throwBanana.mp3");
    }
    private void selfHeal(){
        getWorld().addObject(new HealingEffect(20, 40), getX(), getY());
        Greenfoot.playSound("healup.mp3");
    }
    private void smash(){
        getWorld().addObject(new SmashEffect(200, 99), getX(), getY()); // deal damage
        selfHeal(); selfHeal(); // heal self
        // deal knockback
        for(Entity e: getObjectsInRange(150, Entity.class)){
            double vx = (150-Math.abs(e.getX()-getX()))*Math.signum(e.getX()-getX())/8.0;
            double vy = (150-Math.abs(e.getY()-getY()))*Math.signum(e.getY()-getY())/8.0;
            e.push(vx, vy);
        }
        smashCooldown = MAX_SMASH_COOLDOWN;
        stunDuration = 200;
    }
    private void punch(){
        punching = true;
        double[] enemyDetails = detectNearestEntity(Child.class, 10);
        if(enemyDetails[1] == -1) return;
        Child enemy = getObjectsInRange(10, Child.class).get(0);
        enemy.takeDamage(10);
        enemy.push((int)enemyDetails[0], 10);
        Greenfoot.playSound("swishingPunch.mp3");
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
    protected void die(){
        if(revives>0){
            revives--;
            revive();
        } else {
            super.die();
        }
    }
}
