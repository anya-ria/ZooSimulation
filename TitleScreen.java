import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.World;
/**
 * Title Screen
 * The player can:
 *      Start the game
 *      View Game Achievements
 *      View Game Endings
 * 
 * @author Vanessa Huo
 * @version 2024/04
 */
public class TitleScreen extends World
{
    //Init buttons
    private AchieveButton achieveButton;
    private EndButton endButton;
    private StartButton startButton;

    //Init music
    private GreenfootSound music;
        
    public TitleScreen()
    {    
        // Create a new world with 1024x800 cells with a cell size of 1x1 pixels.
        super(1024, 800, 1); 
        setBackground("title.jpg");

        //Add buttons
        startButton = new StartButton();
        addObject(startButton,400,480);
        achieveButton = new AchieveButton();
        addObject(achieveButton,400,580);
        EndButton endButton = new EndButton();
        addObject(endButton,400,680);
        
        //Preload background music
        music = new GreenfootSound ("backgroundMusic.mp3");
        music.setVolume(70);
    }
    
<<<<<<< Updated upstream
    /**
     * When 'Start' button gets pressed, go to Customization screen and play the sound effect
     */
=======
>>>>>>> Stashed changes
    public void act(){
        if(Greenfoot.mouseClicked(startButton)){
            Customization game = new Customization();
            Greenfoot.setWorld(game);
            Greenfoot.playSound("mouseClick.mp3");
            music.playLoop();
        }
    }
    
    // public void getStarted (){
        // music.playLoop();
    // }
    
    public void stopped() {
        music.pause();
    }
}