import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.World;
/**
 * Title Screen
 * The player can:
 *      Start the game
 *      View Game Achievements
 *      View Game Endings
 * 
 * @author Megan Lee | Art & Sound
 * @author Vanessa Huo | Functions
 * @version 04/25/2024
 */
public class TitleScreen extends World
{
    private AchieveButton achieveButton; //button to achievement screen
    private EndButton endButton; //button to collections screen
    private StartButton startButton; //button to user select screen
    
    private GreenfootSound musicBG; //background music
    
    /**
     * A constructor for the simulation's title screen to set up the world and initalize objects.
     */
    public TitleScreen()
    {    
        // Create a new world with 1024x800 cells with a cell size of 1x1 pixels.
        super(1024, 800, 1); 
        setBackground("title.jpg");

        //Add buttons
        startButton = new StartButton();
        addObject(startButton,512,430);
        achieveButton = new AchieveButton();
        addObject(achieveButton,662,430);
        EndButton endButton = new EndButton();
        addObject(endButton,362,430);
        
        //Preload background music
        musicBG = new GreenfootSound ("backgroundMusic.mp3");
        musicBG.setVolume(70);
    }
    
    /**
     * When 'Start' button gets pressed, go to Customization screen and play the sound effect
     */
    public void act(){
        if(Greenfoot.mouseClicked(startButton)){
            musicBG.stop();
            Customization game = new Customization();
            Greenfoot.setWorld(game);
            Greenfoot.playSound("mouseClick.mp3");
        }
    }
    
    /**
     * Pauses longer sounds that were being played.
     * A method called by the Greenfoot system when the execution has stopped/paused.
     */
    public void stopped() {
        musicBG.pause();
    }
    
    /**
     * Resumes longer sounds that were being played.
     * A method called by the Greenfoot system when the execution has stopped/paused.
     */
    public void started (){
        musicBG.playLoop();
    }
}
