import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TitleScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleScreen extends World
{

    /**
     * Constructor for objects of class TitleScreen.
     * 
     */
    
<<<<<<< Updated upstream
    Label start, label2;
=======
    Label title, start;
>>>>>>> Stashed changes
    
    public TitleScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 800, 1); 
<<<<<<< Updated upstream

        
        //Add imagebuttons NewGame and Tutorial
        start = new Label("Zoo Trip", 230);
        addObject(start, getWidth()/2, 350);
        
        label2 = new Label("Press 'space' to start", 70);
        addObject(label2,getWidth()/2,600);
    }
    
    public void act(){
        if(Greenfoot.isKeyDown("space"))
        {
            MyWorld gameWorld = new MyWorld();
            Greenfoot.setWorld(gameWorld);
=======
        
        title = new Label("Zoo Trip", 250);
        addObject(title, getWidth()/2, 300);
        start = new Label("Press 'space' to start", 80);
        addObject(start, getWidth()/2, 550);
    }
    
    
    public void act(){
        if(Greenfoot.isKeyDown("space")){
            MyWorld world = new MyWorld();
            Greenfoot.setWorld(world);
>>>>>>> Stashed changes
        }
    }
}
