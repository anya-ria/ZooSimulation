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
        // first 40% of the acts
        lightning.playLoop();
        if(actCount < duration*0.4){
            // at time intervals 0 to duration*0.1 and duration*0.2 to duration*0.4
            if(actCount == 0 || actCount == duration*0.2) {
                setImage(new GreenfootImage("darkOverlay.png"));
                if(actCount == duration*0.2) turnIntoZombie();
            }
            // At time intervals duration*0.1 to duration*0.2 screen has an opaque white flash
            else if(actCount  == duration*0.1){
                setImage(new GreenfootImage("darkOverlay.png"));
                GreenfootImage flash = new  GreenfootImage(1024, 800);
                flash.setColor(WHITE);
                flash.fill();

                //Set image as the flash
                setImage(flash);
            }
        }
        // after those first 40% of acts, lightning animation happens
        else if(actCount % (duration/6) == 0 && actCount < duration){
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
     * When lightning strikes, animals hace a chance to turn into zombies!
     */
    public void turnIntoZombie(){
        for(Animal a: getWorld().getObjects(Animal.class)){
            if(!(a instanceof Zombie)&&Greenfoot.getRandomNumber(3) == 0 && a.isAwake()){ //1/3 chance of turning into a zombie
                a.zombify();
                Zoo.numZombie++;
                Zoo.numAnimals--;
            }
        }
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
    /** 
     * Method that pauses lightning sound.
     */
    public static void pauseSound(){
        lightning.pause();
    }
}