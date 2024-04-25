import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class Boss here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ZombieBoss extends Entity
{
    private GreenfootImage LImage, RImage;
    private SuperStatBar hpBar;
    private boolean awake = true;
    private int maxHp;
    private int animCounter, animDelay, animIndex; 
    private int maxIndex;
    private final int maxThrowCooldown = 20;
    private int throwCooldown = maxThrowCooldown;
    private int stunDuration;
    private boolean turn;
    private int movingSpeed = 3;
    public void act()
    {
        if(!super.update()) return;
        animate();
        throwFireBalls();
        stunDuration--;
    }
    
    public ZombieBoss(){
        super(1000);
        LImage = new GreenfootImage("boss.png");
        setImage(LImage);
        maxIndex = 5;
        animCounter = 0;
        
        animIndex = 0;
        animDelay = 30;
        animCounter = animDelay;
    }
    
    public void animate(){
        if(animCounter == 0){
            animCounter = animDelay; 
            if(animIndex >= maxIndex){
                animIndex = 0; 
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
                    throwFireBall((int)direction, 4);
                    break;
                case 1:
                    throwFireBall((int)direction, 8);
                    break;
            }
            throwCooldown = maxThrowCooldown;
        }
        throwCooldown--;
        if(distance < 10){
            return;
        }
        setLocation(getX()+vector[0]*1.5, getY()+vector[1]*1.5);
        if (Greenfoot.getRandomNumber(240) < 10 && stunDuration == 0)
        {
            turn = !turn;
            stunDuration = 60;
        }
    }
    
    private void throwFireBall(int direction, int speed){
        int modif = rand.nextInt(-10,11);
        if(turn){
            getWorld().addObject(new FireBall(direction+modif, speed), getX()-40, getY());
        }
        else if(!turn){
            getWorld().addObject(new FireBall(direction+modif, speed), getX()+40, getY());
        }
    }
    
}
