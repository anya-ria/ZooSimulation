import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;
/**
 * Show a list of achievements
 * Gold Trophy - completed
 * Grey Trophy - not completed yet
 *
 * @author Vanessa Huo | Functions
 * @author Megan Lee | Art, Sounds, Cleanup
 * @version 04/25/2024
 */
public class Achievement extends World
{
    //Booleans that detect if an achievement is completed 
    private static boolean achi0 = false; //heal 15 children
    private static boolean achi1 = false; //get hit by 10 bananas
    private static boolean achi2 = false; //no fighers
    private static boolean achi3 = false; //all children became zombie
    private static boolean achi4 = false; //all animals became zombie
    
    private Trophy[] arr = new Trophy[5]; //trophy for each achievement to indicate completion
    private HomeButton returnKey; //home button
    private GreenfootSound musicBG; //background music
    
    /**
     * A constructor for the simulation's achievements scene to set up the world and initalize objects.
     */
    public Achievement()
    {    
        // Create a new world with 1024x800 cells with a cell size of 1x1 pixels.
        super(1024, 800, 1); 
        setBackground("AchievementsScreen.jpg");
        
        //Add return button
        returnKey = new HomeButton();
        addObject(returnKey, getWidth()-80, 750);
        
        addCups();
        
        musicBG = new GreenfootSound ("backgroundMusic.mp3");
        musicBG.setVolume(70);
    }
    
    /**
     * Adds the achievement cups to the Achievement screen
     */
    private void addCups(){
        for(int i=0; i<arr.length;i++){
            arr[i] = new Trophy();
            addObject(arr[i], 285, (92*i+260));
        }
    }
    
    /**
     * If an achievement is completed, display a gold trophy instead.
     */
    public void act(){
        if(achi0){
            arr[0].setAchieved();
        }
        if(achi1){
            arr[1].setAchieved();
        }
        if(achi2){
            arr[2].setAchieved();
        }
        if(achi3){
            arr[3].setAchieved();
        }
        if(achi4){
            arr[4].setAchieved();
        }
    }
    
    /**
     * Static method called in the Zoo to set achievement 1 to true.
     */
    public static void completeAchi0(){
        achi0 = true;
    }
    
    /**
     * Static method called in the Zoo to set achievement 2 to true.
     */
    public static void completeAchi1(){
        achi1 = true;
    }
    
    /**
     * Static method called in the Zoo to set achievement 3 to true.
     */
    public static void completeAchi2(){
        achi2 = true;
    }
    
    /**
     * Static method called in the Zoo to set achievement 4 to true.
     */
    public static void completeAchi3(){
        achi3 = true;
    }
    
    /**
     * Static method called in the Zoo to set achievement 5 to true.
     */
    public static void completeAchi4(){
        achi4 = true;
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