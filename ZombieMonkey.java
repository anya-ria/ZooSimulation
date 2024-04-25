import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.Random;
/**
 * Write a description of class ZombieMonkey here.
 * 
 * @author <li> Luke Xiao | Movements
 * @author <li> Anya Shah | Animations
 * @author <li> Lucas Fu | ThrowBanana, "chaseChildren" from Traitor class
 * 
 * @version 04/18/2024
 */
public class ZombieMonkey extends Zombie
{
    // Zombie monkey sprites
    private GreenfootImage[] walkRight = new GreenfootImage[3];
    private GreenfootImage[] walkLeft = new GreenfootImage[3];
    private GreenfootImage[] walkToward = new GreenfootImage[3];
    private GreenfootImage[] walkAway = new GreenfootImage[3]; 
    
    // Animation variables
    private int animCounter, animDelay, animIndex; 
    private int maxIndex;
    private boolean right, left, away, toward;
    
    private final int maxThrowCooldown = 50;
    private int throwCooldown = maxThrowCooldown;
    private int stunDuration = 0;
    
    private Random rand = new Random();
    
    public ZombieMonkey() {
        super(100);
        animCounter = 0;
        maxIndex = walkRight.length;
        initImages();
        setImage("zombieMonkeyWalkToward/walkToward1.png");
    }
    
    private void initImages() {
        for(int i = 0; i < maxIndex; i++) {
            walkAway[i] = new GreenfootImage("zombieMonkeyWalkAway/walkAway" + i + ".png");
        }
        for(int i = 0; i < maxIndex; i++) {
            walkToward[i] = new GreenfootImage("zombieMonkeyWalkToward/walkToward" + i + ".png");
        }
        for(int i = 0; i < maxIndex; i++) {
            walkRight[i] = new GreenfootImage("zombieMonkeyWalkRight/walkRight" + i + ".png");
        }
        for(int i = 0; i < maxIndex; i++) {
            walkLeft[i] = new GreenfootImage("zombieMonkeyWalkRight/walkRight" + i + ".png");
            walkLeft[i].mirrorHorizontally();
        }
        animIndex = 0;
        animDelay = 10;
        animCounter = animDelay;
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
        if(distance<500 && distance>50 && throwCooldown<=0){
            switch(rand.nextInt(2)){
                case 0:
                    throwBanana((int)direction, 4);
                    break;
                case 1:
                    throwBanana((int)direction, 8);
                    break;
            }
            throwCooldown = maxThrowCooldown;
        }
        throwCooldown--;
        if(distance < 30){
            return;
        }
        setLocation(getX()+vector[0]*1.2, getY()+vector[1]*1.2);
        direction = Greenfoot.getRandomNumber(361);
        move(1);
        
        // movement
        if (Greenfoot.getRandomNumber(240) < 10)
        {
            setRotation(direction);
            left = false; right = false; away = false; toward = false;
            if (direction >= 315 || direction <= 45) // right
            {
                right = true;
            }
            if (direction > 45 && direction <= 135) // up
            {   
                away = true;
            }
            if (direction > 135 && direction <= 225) // left
            {
                left = true;
            }
            if (direction > 225 && direction <= 315) // down
            {
                toward = true; 
            }
        }
        if (getX() <= 20 || getX() >= 1004)
        {
            turn(180);
        }
        if (getY() <= 20 || getY() >= 780)
        {
            turn(180);
        }
    }
    private void throwBanana(int direction, int speed){
        int modif = rand.nextInt(-10,11);
        getWorld().addObject(new Banana(direction+modif, speed), getX(), getY());
    }
    /**
     * Act - do whatever the ZombieMonkey wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(!super.update()) return;
        animate();
        setLocation(getX(), getY()); // to enable push physics
        chaseChildren();
    }
    
    protected void animate() {
        if(animCounter == 0){
            animCounter = animDelay; 
            animIndex++; 
            if(animIndex >= maxIndex){
                animIndex = 0; 
            }
            if(right){
                setImage(walkRight[animIndex]);
            } 
            else if (left){
                setImage(walkLeft[animIndex]);
            } 
            else if(toward){
                setImage(walkToward[animIndex]); 
            } 
            else if(away){
                setImage(walkAway[animIndex]);
            }
        } 
        else {
            animCounter--; 
        }
    }
}