import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The banana peel is a non-moving "projectile" that makes characters slip when they step on it
 * 
 * @author Lucas
 * @version 2024/4/16
 */
public class Peel extends Projectile
{
    private GreenfootImage[] peel = new GreenfootImage[14];
    private int animCounter, animDelay, animIndex; 
    
    public Peel(){
        super(0,0);
        
        animCounter = 0;
        initImages();
    }
    
    private void initImages() {
        for(int i = 0; i < peel.length; i++) {
            peel[i] = new GreenfootImage("peelSprites/peel" + i + ".png");
            peel[i].scale(30, 30);
        }
        
        animIndex = 0;
        animDelay = 2;
        animCounter = animDelay;
    }
    
    // public void act() {
        // animate();
    // }
    
    private void animate() {
        if(animCounter == 0) {
            animCounter = animDelay;
            animIndex++;
            if(animIndex >= peel.length) {
                animIndex = 0;
            }
            setImage(peel[animIndex]);
        }
        else {
            animCounter--;
        }
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
