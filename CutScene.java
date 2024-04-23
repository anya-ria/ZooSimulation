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

        Greenfoot.setSpeed(50);
        
        setBackground("zoo.jpg");
        setPaintOrder (Icon.class, Effect.class, Animal.class);
        actCount = 0;

        addObject(new Effect(null, new GreenfootImage("blackScreen.png"), 0, 120, 60), 512, 400);
        spawnAnimals();
        
        SkipButton skipButton = new SkipButton();
        addObject(skipButton,79,739);
        skipButton.setLocation(952,754);
    }

    public void act(){        
        if(actCount == 180){
            Effect teacherText1 = new Effect(null, new GreenfootImage("teacherText1.png"), 20, 180, 20);
            addObject(teacherText1, 512, 400); 
        }
        if(actCount == 380){
            Lightning lightning = new Lightning(new GreenfootSound ("lightning.mp3"), 300);
            addObject(lightning, 512, 400); 
        }
        if(actCount == 460){
            Effect teacherText2 = new Effect(null, new GreenfootImage("teacherText2.png"), 20, 180, 20);
            addObject(teacherText2, 512, 400); 
        }
        if(actCount == 680){
            Zoo zoo = new Zoo();
            Greenfoot.setWorld(zoo);
        }
        actCount++;
    }

    /**
     * Spawns 3 of each type of animals in initial location within their respective pens
    */
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
    /**
     * Removes all animals from screen to disappear during special effects
     */
    public void removeAnimals(){
        for(Animal a: getObjects(Animal.class)){
            removeObject(a);
        }
    }
}
