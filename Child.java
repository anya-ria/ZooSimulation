import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * A child is an abstact class that represents all the children attending the zoo.
 * 
 * @author Lucas
 * @version 2024/4/4
 */
public abstract class Child extends Entity
{
    public Child(int maxHp){
        super(maxHp);
    }
    public void act(){
        super.act();
    }
}
