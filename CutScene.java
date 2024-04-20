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

    }
    
    public void act(){        
    }
}
