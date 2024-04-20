import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A cutscene that introduces the inciting incident; lightning turns zoo animals to zombies.
 * 
 * @author Megan
 * @version April 2024
 */
public class CutScene extends World
{
    int actCount;

    /**
     * Constructor for objects of class CutScene.
     * 
     */
    public CutScene()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 800, 1); 

        setBackground("zoo.jpg");
        setPaintOrder (Effect.class, Animal.class);
        actCount = 0;

        addObject(new Transition(null, null, 0, 120, 60), 512, 400);
        spawnAnimals();
    }

    public void act(){        
        if(actCount == 180){
            addObject(new Text(null, new GreenfootImage("teacherText.png"), 40, 120, 40), 500, 400); 
        }
        actCount++;
    }

    /**

    Spawns 3 of each type of animals in initial location within their respective pens*/
    public void spawnAnimals(){

        addObject(new Monkey(), 80, 100);
        addObject(new Monkey(), 290, 155);
        addObject(new Monkey(), 175, 230);

        addObject(new Hippo(), 720, 170);
        addObject(new Hippo(), 850, 90);
        addObject(new Hippo(), 950, 220);

        addObject(new Penguin(), 750, 580);
        addObject(new Penguin(), 850, 630);
        addObject(new Penguin(), 950, 710);
    }
}
