import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Black Overlay that fades overtime.
 * 
 * @author Megan Lee
 * @version April 2024
 */
public class Transition extends Effect {
    int actCount, timeLeft;
    public Transition(GreenfootSound sound, GreenfootImage image, int fadeIn, int duration, int fadeOut){  
        super(null, new GreenfootImage("blackScreen.png"), fadeIn, duration, fadeOut);
        timeLeft = fadeOut;
        actCount = 0;
    }
    
    /**
     * Fade  - do whatever the Transition wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act(){
        if(actCount > allActs[1] && timeLeft >-1){
            double percent = timeLeft/ (double)allActs[2]; //time left, fade time
        
            //range of 0 (invisible) to 255 (opaque)
            int transparency = (int)(percent * 255); 
            getImage().setTransparency(transparency);       
            timeLeft--;
        }
        
        if(actCount == (allActs[1] + allActs[2])){
            getWorld().removeObject(this);
        }
        actCount++;
    }
}
