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
    private boolean expiring = false;
    public Peel(){
        super(0,0);
        getImage().scale(30, 30);
        animCounter = 0;
        initImages();
    }
    
    public void act(){
        if(expiring){
            animate();
        } else {
            super.act();
        }
    }
    
    private void initImages() {
        for(int i = 0; i < peel.length; i++) {
            peel[i] = new GreenfootImage("peelSprites/peel" + i + ".png");
            peel[i].scale(30, 30);
        }
        
        animIndex = 0;
        animDelay = 1;
        animCounter = animDelay;
    }
    
    private void animate() {
        if(animCounter == 0) {
            animCounter = animDelay;
            animIndex++;
            if(animIndex >= peel.length) {
                animIndex = 0;
                expiring = false;
                expired = true;
            }
            setImage(peel[animIndex]);
        }
        else {
            animCounter--;
        }
    }
    
    /**
     * Makes the child that touched this slip, expiring this
     */
    protected void detectCollision(){
        Child touched = (Child) getOneIntersectingObject(Child.class);
        if(touched!=null&&touched.isAwake()){
            touched.slip();
            expiring = true;
        }        
    }
    protected void expire(){
        getWorld().removeObject(this);
    }
}
