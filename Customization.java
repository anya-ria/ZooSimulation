import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.util.*;
/**
 * Write a description of class Customization here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Customization extends World
{
    private StartButton startButton;
    private ChildParam c1, c2, c3;
    private ZombieParam z1, z2, z3;
    private HealerParam h1, h2;
    private FighterParam f1, f2;
    CutScene game = new CutScene();
    /**
     * Constructor for objects of class Customization.
     * 
     */
    public Customization()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 800, 1); 
        startButton = new StartButton();
        addObject(startButton,getWidth()/2,760);
        setBackground("userSelectScreen.jpg");
        prepare();
    }

    public void act(){
        if(Greenfoot.mouseClicked(startButton)){
            setValue();
            Greenfoot.setWorld(game);
            Greenfoot.playSound("mouseClick.mp3");
        }
    }
    
    public void setValue(){
        ArrayList<ChildParam> y = (ArrayList<ChildParam>)getObjects(ChildParam.class);
        for(ChildParam other: y)
        {
            if(other.getClicked())
            {
                Zoo.setNumChild(other.getValue());
            }
        }
        ArrayList<FighterParam> y1 = (ArrayList<FighterParam>)getObjects(FighterParam.class);
        for(FighterParam other: y1)
        {
            if(other.getClicked())
            {
                Zoo.setNumFighter(other.getValue());
            }
        }
        ArrayList<HealerParam> y2 = (ArrayList<HealerParam>)getObjects(HealerParam.class);
        for(HealerParam other: y2)
        {
            if(other.getClicked())
            {
                Zoo.setNumHealer(other.getValue());
            }
        }
        ArrayList<ZombieParam> y3 = (ArrayList<ZombieParam>)getObjects(ZombieParam.class);
        for(ZombieParam other: y3)
        {
            if(other.getClicked())
            {
                Zoo.setNumZombie(other.getValue());
            }
        }
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        addObject(new ChildParam(1),getWidth()/2-100,318);
        addObject(new ChildParam(2),getWidth()/2,318);
        addObject(new ChildParam(3), getWidth()/2+100,318);

        addObject(new ZombieParam(1),getWidth()/2-100,650);
        addObject(new ZombieParam(2),getWidth()/2,650);
        addObject(new ZombieParam(3), getWidth()/2+100,650);

        addObject(new HealerParam(1),getWidth()/2-280,480);
        addObject(new HealerParam(2),getWidth()/2-180,480);
        
        addObject(new FighterParam(1),getWidth()/2+180,480);
        addObject(new FighterParam(2),getWidth()/2+280,480);
    }
    
}
