import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The banana peel is a non-moving "projectile" that makes characters slip when they step on it
 * 
 * @author Lucas
 * @version 2024/4/16
 */
public class Peel extends Projectile
{
    public Peel(){
        super(0,0);
    }
    protected void detectCollision(){
        Child touched = (Child) getOneIntersectingObject(Child.class);
        if(touched!=null&&touched.isAwake()){
            touched.slip();
            expired = true;
        }        
    }
    protected void expire(){
        getWorld().removeObject(this);
    }
}
