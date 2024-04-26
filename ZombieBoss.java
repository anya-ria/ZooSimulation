import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class Boss here.
 * 
 * @author Vanessa Huo 
 * @version 04/26/2024
 */
public class ZombieBoss extends Entity
{
    private GreenfootImage LImage, RImage;
    
    //Animation variables
    private int animCounter, animDelay, animIndex; 
    private int maxIndex;
    
    //Cooldown variables
    private final int maxThrowCooldown = 20;
    private int throwCooldown = maxThrowCooldown;
    
    //Facing
    private boolean turn;
    
    public ZombieBoss(){
        super(800); //800Hp
        LImage = new GreenfootImage("boss.png");
        setImage(LImage);
        
        animIndex = 0;
        maxIndex = 20;
        animDelay = 60;
        animCounter = animDelay;
    }
    /**
     * Act - do whatever the ZombieBoss wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(!super.update()) return;
        animate();
        throwFireBalls();
    }
    
    /**
     * This method animates the ZombieBoss 
     */
    public void animate(){
        if(animCounter == 0){
            animCounter = animDelay; 
            if(animIndex >= maxIndex){
                animIndex = 0; 
            }
            if (Greenfoot.getRandomNumber(240) < 10)
            {
                turn = !turn;
            }
            if(turn){
                setImage(LImage);
            } 
            else if (!turn){
                getImage().mirrorHorizontally(); 
            }
        } 
        else {
            animCounter--; 
        }
    }
    
    /**
     * The Zombie Boss throws fireballs at the children. 
     */
    private void throwFireBalls(){
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
                    // sometimes throws fireball slower
                    throwFireBall((int)direction, 4);
                    break;
                case 1:
                    // sometimes throws fireball faster
                    throwFireBall((int)direction, 8);
                    break;
            }
            throwCooldown = maxThrowCooldown;
        }
        throwCooldown--;
        if(distance < 10){
            return;
        }
        // movement
        setLocation(getX()+vector[0]*1.5, getY()+vector[1]*1.5);
    }
    
    /**
     * Throws FireBall with a speed and direction
     * @param angle the direction in degrees
     * @param speed the magnitude of speed
     */
    private void throwFireBall(int direction, int speed){
        if(turn){
            getWorld().addObject(new FireBall(direction, speed), getX()-80, getY());
        }
        else if(!turn){
            getWorld().addObject(new FireBall(direction, speed), getX()+80, getY());
        }
    }
}
