import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.util.*;
/**
 * Customization screen where plays can set the initial number of Children, Fighers, Healers and Zombies in Zoo
 * Different values may lead to different endings
 * 
 * @author Vanessa Huo | Functions
 * @version 2024/04
 */
public class Customization extends World
{
    //Init instances 
    private StartButton startButton;
    private ChildParam c1, c2, c3; //3 choices
    private ZombieParam z1, z2, z3; 
    private HealerParam h1, h2, h3; //2 choices
    private FighterParam f1, f2, f3;
    
    //Init music
    private GreenfootSound music;
    
    CutScene game = new CutScene();
    public Customization()
    {    
        // Create a new world with 1024x800 cells with a cell size of 1x1 pixels.
        super(1024, 800, 1); 
        
        //Add start button 
        startButton = new StartButton();
        addObject(startButton,getWidth()/2,760);
        
        setBackground("userSelectScreen.jpg");
        
        createParams();
        
        //Preload background music
        music = new GreenfootSound ("backgroundMusic.mp3");
        music.playLoop();
    }

    /**
     * When 'Start' button gets pressed, set initial number of each actor in Zoo
     * Go to CutScene world and play sound effect
     */
    public void act(){
        if(Greenfoot.mouseClicked(startButton)){
            resetVariables();
            setValue();
            music.stop();
            //Go to CutScene
            Greenfoot.setWorld(game);
            Greenfoot.playSound("mouseClick.mp3");
        }
    }
    
    /**
     * A method that checks which button player clicked for each variable. 
     * Set the initial number of Children, Fighter, Healer and Zombie according to player's choice. 
     * If player did not make a choice, then no values are set. The Zoo world will create actors accroding to the default numbers.
     */
    public void setValue(){
        //ArrayList<ChildParam> y = (ArrayList<ChildParam>)getObjects(ChildParam.class);
        for(ChildParam other: getObjects(ChildParam.class))
        {
            if(other.getClicked())
            {
                Zoo.numChildren = other.getValue();//Set number of Children in Zoo according player's choice
            }
        }
        //ArrayList<FighterParam> y1 = (ArrayList<FighterParam>)getObjects(FighterParam.class);
        for(FighterParam other: getObjects(FighterParam.class))
        {
            if(other.getClicked())
            {
                Zoo.numFighter = other.getValue();
            }
        }
        //ArrayList<HealerParam> y2 = (ArrayList<HealerParam>)getObjects(HealerParam.class);
        for(HealerParam other: getObjects(HealerParam.class))
        {
            if(other.getClicked())
            {
                Zoo.numHealer = other.getValue();
            }
        }
        //ArrayList<ZombieParam> y3 = (ArrayList<ZombieParam>)getObjects(ZombieParam.class);
        for(ZombieParam other: getObjects(ZombieParam.class))
        {
            if(other.getClicked())
            {
                Zoo.numZombie = other.getValue();
            }
        }
    }
    
    /**
     * Create choices (buttons) for each variable in the Customization screen
     */
    private void createParams()
    {
        addObject(new ChildParam(1),getWidth()/2-100,318);
        addObject(new ChildParam(2),getWidth()/2,318);
        addObject(new ChildParam(3), getWidth()/2+100,318);

        addObject(new ZombieParam(1),getWidth()/2-100,650);
        addObject(new ZombieParam(2),getWidth()/2,650);
        addObject(new ZombieParam(3), getWidth()/2+100,650);

        addObject(new HealerParam(1),getWidth()/2-280,480);
        addObject(new HealerParam(2),getWidth()/2-180,480);
        addObject(new HealerParam(3),getWidth()/2-80,480);
        
        addObject(new FighterParam(1),getWidth()/2+180,480);
        addObject(new FighterParam(2),getWidth()/2+280,480);
        addObject(new FighterParam(3),getWidth()/2+380,480);
    }
    
    public void resetVariables(){
        Zoo.numChildren = 5;
        Zoo.numHealer = 2;
        Zoo.numFighter = 1;
        Zoo.numZombie = 0;
    }
}
