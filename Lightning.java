import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Lightning is a visual effect that simulates a thunderstorm. 
 * It causes the scene to darken and then flashes white, followed by two lightning strikes. 
 * The minimum recommended duration for the entire intended animation is 300 acts.
 *
 * @author Megan
 * @version April 2024
 */
public class Lightning extends SuperSmoothMover
{
    //Colour constant, an opaque white
    public static final Color WHITE = new Color (255,255,255);
    
    //Class variables
    private static boolean isStorming = false;
    private static GreenfootSound lightning;
    
    //Instance variables
    private int imageIndex, actCount, duration;
    
    //Array to store images for lightning animation
    private GreenfootImage[] lightningStrike = new GreenfootImage[3];

    /**
     * Creates a lightning effect on screen.   
     * @param duration      time length in # of acts
     */
    public Lightning(int duration){
        this.duration = duration;
        
        imageIndex = 0; //Tracks the current index of the lightning animation array to use
        actCount = 0;
        
        //Set sound as lightning
        lightning = new GreenfootSound("lightning.wav");

        //Store lightning sprites into array
        for(int i=1; i<4;i++) {
            lightningStrike[i-1] = new GreenfootImage("lightning" + i + ".png");
        }
    }

    public void act(){
        lightning.playLoop();
        
        //For the first 120 acts
        if(actCount < 121){
            //At time intervals, 0-20 & 80-135, screen has a dark transparent overlay
            if(actCount == 0 || actCount == 80) {
                //Set image as an already made dark transparent overlay
                setImage(new GreenfootImage("darkOverlay.png"));
            }
            //At time interval, 20-80, screen has an opaque white flash
            else if(actCount  == 20){
                //Creates flash, a GreenfootImage with dimensions of the world
                GreenfootImage flash = new GreenfootImage(1024, 800);
            
                //Fill flash as opaque white
                flash.setColor(WHITE);
                flash.fill();
                
                //Set image as the flash
                setImage(flash);
            }
        }
        //After the first 120 acts, at time intervals of 45 seconds
        //First time it runs is at 135 acts (135 % 45 == 0)
        else if(actCount % 45 == 0){
            //Set image as appropriate lightning animation (3 images)
            if(imageIndex < 3){
                setImage(lightningStrike[imageIndex]);
                imageIndex++;
            }
            //Set image as an already made dark transparent overlay
            else{
                setImage(new GreenfootImage("darkOverlay.png"));
            }
        }
    
        //Remove itself from world when it reaches its duration 
        if(actCount == duration){
            lightning.stop();
            getWorld().removeObject(this);
            return;
        }
        actCount++;
    }
    
    /**
     * Method that pauses lightning sound.
     */
    public static void pauseSound(){
        lightning.pause();
    }
    
    /**
     * Method that plays/resumes lightning sound.
     */
    public static void playSound(){
        lightning.play();
    }
}
