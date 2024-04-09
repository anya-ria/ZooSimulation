import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;
/**
 * Write a description of class Achievement here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Achievement extends World
{

    /**
     * Constructor for objects of class Achievement.
     * 
     */

    Label title, achi1, achi2, achi3, achi4, achi5, achi6, returnKey;
    GoldCup[] arr = new GoldCup[6];

    public Achievement()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 800, 1); 

        title = new Label("Achievements", 110);
        addObject(title, getWidth()/2, 90);
        achi1 = new Label("Healer", 70);
        addObject(achi1, 471, 200);
        achi2 = new Label("I'm the Hospital", 70);
        addObject(achi2, getWidth()/2+100, 300);
        achi3 = new Label("Banan-AHHH!", 70);
        addObject(achi3, 581, 400);
        achi4 = new Label("All Affected", 70);
        addObject(achi4, 543, 500);
        achi5 = new Label("Oops...", 70);
        addObject(achi5, 472, 600);
        achi6 = new Label("Zombie Zoo", 70);
        addObject(achi6, 551, 700);
        returnKey = new Label("Home", 50);
        addObject(returnKey, getWidth()-80, 750);
        addCups();
    }
    
    
    public void addCups(){
        for(int i=0; i<arr.length;i++){
            arr[i] = new GoldCup();
            addObject(arr[i], 200, (100*i+200));
        }
        arr[2].setAchieved();
    }

    public void act(){
        if(Greenfoot.mouseClicked(returnKey)){
            TitleScreen game = new TitleScreen();
            Greenfoot.setWorld(game);
        }
    }
}
