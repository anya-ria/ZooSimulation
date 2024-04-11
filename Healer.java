import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The healer likes to help fellow children with some clerical magic.
 * <p>
 * Uses 2 kinds of moves:
 * <li> AOE heal when self is low on hp or ally nearby
 * <li> projectile heal when ally is detected, but far away
 * 
 * @author Lucas 
 * @version 2024/4/9
 */
public class Healer extends Child
{
    private final int maxAoeCooldown = 200;
    private final int maxProjCooldown = 80;
    
    private int aoeCooldown = maxAoeCooldown;
    private int projCooldown = maxProjCooldown;
    private int stunDuration = 0;
    public Healer(){
        super(200);
    }
    public void act(){
        if(!awake) return;
        stunDuration--;
        if(stunDuration>0){
            setLocation(getX(), getY());
            return;
        }
        double[] allyDetails = detectNearestEntity(Child.class, 10000);
        followAlly(allyDetails);
        checkHeal(allyDetails);
    }
    private void followAlly(double[] details){
        double[] vector;
        if(details[1] >= 95) // too far
            vector = Utility.angleToVector(details[0]);
        else if(details[1] <= 25) // too close
            vector = Utility.angleToVector(details[0]+180);
        else
            vector = new double[] {0, 0}; 
        if(stunDuration<=0)
            setLocation(getX()+vector[0], getY()+vector[1]);
    }
    private void checkHeal(double[] details){
        double direction = details[0];
        double distance = details[1];
        if(distance<=100 && aoeCooldown<=0){
            getWorld().addObject(new HealingEffect(200, 40), getX(), getY());
            aoeCooldown = maxAoeCooldown;
            stunDuration = 120;
        }
        if(distance>=25 && distance < 500 && projCooldown<=0){
            getWorld().addObject(new HealingBall((int)direction, 4, 10), getX(), getY());
            projCooldown = maxProjCooldown;
        }
        aoeCooldown--; projCooldown--;
    }
}
