import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * 
 * Lock Icon 
 * 
 * @author <li> Megan Lee | Art
 * @author <li> Vanessa Huo | Functions
 * @version 04/25/2024
 */
public class Lock extends Icon
{
    /**
     * Act - do whatever the Lock wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private boolean unlock;
    //set new images
    private GreenfootImage locked = new GreenfootImage("locked.png");
    private GreenfootImage unlocked = new GreenfootImage("unlocked.png");
    
    public Lock()
    {
        //if unlock is false, this code sets the image to locked
        unlock = false;
        setImage(locked);
        //locked.scale(70, 70);
    }
    
    public void act(){
        if(unlock){ 
            setImage(unlocked); //if unlock is true, set image to unlocked
            //unlocked.scale(70, 70);
        }
    }
    
    public void setAchieved(){
        unlock = true;
    }
    
    public boolean getUnlock(){
        return unlock;
    }
}
