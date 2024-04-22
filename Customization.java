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
    Zoo game = new Zoo();
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
                game.setNumChild(other.getValue());
            }
        }
        ArrayList<FighterParam> y1 = (ArrayList<FighterParam>)getObjects(FighterParam.class);
        for(FighterParam other: y1)
        {
            if(other.getClicked())
            {
                game.setNumFighter(other.getValue());
            }
        }
        ArrayList<HealerParam> y2 = (ArrayList<HealerParam>)getObjects(HealerParam.class);
        for(HealerParam other: y2)
        {
            if(other.getClicked())
            {
                game.setNumHealer(other.getValue());
            }
        }
        ArrayList<ZombieParam> y3 = (ArrayList<ZombieParam>)getObjects(ZombieParam.class);
        for(ZombieParam other: y3)
        {
            if(other.getClicked())
            {
                game.setNumZombie(other.getValue());
            }
        }
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        c1 = new ChildParam();
        c1.setIcon(1);
        addObject(c1,getWidth()/2-100,318);
        c2 = new ChildParam();
        c2.setIcon(2);
        addObject(c2,getWidth()/2,318);
        c3 = new ChildParam();
        c3.setIcon(3);
        addObject(c3, getWidth()/2+100,318);

        z1 = new ZombieParam();
        z1.setIcon(1);
        addObject(z1,getWidth()/2-100,650);
        z2 = new ZombieParam();
        z2.setIcon(2);
        addObject(z2,getWidth()/2,650);
        z3 = new ZombieParam();
        z3.setIcon(3);
        addObject(z3, getWidth()/2+100,650);

        h1 = new HealerParam();
        h1.setIcon(1);
        addObject(h1,getWidth()/2-280,480);
        h2 = new HealerParam();
        h2.setIcon(2);
        addObject(h2,getWidth()/2-180,480);
        
        f1 = new FighterParam();
        f1.setIcon(1);
        addObject(f1,getWidth()/2+180,480);
        f2 = new FighterParam();
        f2.setIcon(2);
        addObject(f2,getWidth()/2+280,480);
    }
    
}
