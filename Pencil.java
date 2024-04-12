import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The pencil is a projectile that does DOT upon impact
 * 
 * @author Lucas
 * @version 2024/4/12
 */
public class Pencil extends Projectile
{
    private int dot = 5;
    private int duration = dot*5; // how many acts
    public Pencil(int angle, double speed){
        this(Utility.angleToVector(angle)[0]*speed, 
             Utility.angleToVector(angle)[1]*speed);
    }
    public Pencil(double vx, double vy){
        super(vx, vy);
        getImage().scale(20,20);
        
    }
    protected void detectCollision(){
        Animal touched = (Animal) getOneIntersectingObject(Child.class);
        if(touched!=null&&touched.isAwake()){
            touched.getWounded(dot, duration);
            expired = true;
        }        
    }
    protected void expire(){
        getWorld().removeObject(this);
    }
}
