import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Show a list of endings
 * Unlocked - completed
 * Locked - not completed yet
 * 
 * @author Megan Lee | Art & Sound
 * @author Vanessa Huo | Functions
 * @version 04/25/2024
 */
public class Collections extends World
{
    //Booleans that detect if an ending is completed 
    private static boolean end1 = false; //all children were killed
    private static boolean end2 = false; //zombie animals were defeated 
    private static boolean end3 = false; //boss level was defeated 
    
    private HomeButton returnKey; //home button
    private Lock[] arr = new Lock[3]; //lock for each ending to indicate completion
    private GreenfootSound musicBG; //background music
    
    /**
     * A constructor for the simulation's collections screen to set up the world and initalize objects.
     */
    public Collections()
    {    
        // Create a new world with 1024x800 cells with a cell size of 1x1 pixels.
        super(1024, 800, 1);  
        setBackground("collectionsScreen.png");
        
        //Add return button
        returnKey = new HomeButton();
        addObject(returnKey, getWidth()-80, 750);
        
        addLocks();
        
        musicBG = new GreenfootSound ("backgroundMusic.mp3");
        musicBG.setVolume(70);
        musicBG.playLoop();
    }

    /**
     * If an endng is completed, shows "unlock" icon 
     */
    public void act(){
        if(end1){
            arr[0].setAchieved();
        }
        if(end2){
            arr[1].setAchieved();
        }
        if(end3){
            arr[2].setAchieved();
        }
    }
    
    /**
     * Adds the locks to the Collections screen
     */
    private void addLocks(){
        for(int i=0; i<arr.length;i++){
            arr[i] = new Lock();
            addObject(arr[i], 260, (140*i+290));
        }
    }
    
    /**
     * Static method called in the Zoo to set ending 1 to true.
     */
    public static void unlockEnd1(){
        end1 = true;
    }
    
    /**
     * Static method called in the Zoo to set ending 2 to true.
     */
    public static void unlockEnd2(){
        end2 = true;
    }
    
    /**
     * Static method called in the Zoo to set ending 3 to true.
     */
    public static void unlockEnd3(){
        end3 = true;
    }
    
    /**
     * Pauses longer sounds that were being played.
     * A method called by the Greenfoot system when the execution has stopped/paused.
     */
    public void stopped() {
        musicBG.stop();
    }
    
    /**
     * Resumes longer sounds that were being played.
     * A method called by the Greenfoot system when the execution has stopped/paused.
     */
    public void started (){
        musicBG.playLoop();
    }
}
