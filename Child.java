import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * A child is an abstact class that represents all the children attending the zoo.
 * 
 * @author Lucas
 * @version 2024/4/4
 */
public abstract class Child extends Entity
{
    // Walking sprites
    protected GreenfootImage[] walkAway = new GreenfootImage[9];
    protected GreenfootImage[] walkRight = new GreenfootImage[9];
    protected GreenfootImage[] walkLeft = new GreenfootImage[9];
    protected GreenfootImage[] walkToward = new GreenfootImage[9];
    
    // Direction animation variables
    protected boolean left, right, toward, away;
    public Child(int maxHp){
        super(maxHp);
    }
    public boolean update(){
        return super.update();
    }
    protected abstract void animate();
    /**
     * updates the facing direction variables 
     */
    protected void updateDirection(double[] vector){
        // update facing direction
        left = false; right = false; toward = false; away = false;
        if(vector[0]>0 && Math.abs(vector[0])>Math.abs(vector[1])) {
            right = true;
        }
        else if(vector[0]<0 && Math.abs(vector[0])>Math.abs(vector[1])) {
            left = true;
        }
        else if(vector[1]<0 && Math.abs(vector[0])<Math.abs(vector[1])) {
            away = true;
        }
        else if(vector[1]>0 && Math.abs(vector[0])<Math.abs(vector[1])) {
            toward = true; 
        }
    }
}
