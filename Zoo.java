import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Zoo extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    
    HomeButton homeButton = new HomeButton();
    private int numChildren, numHealer, numFighter, numZombie;
    private int x;
    
    public Zoo()
    {    
        // Create a new world with 1024x800 cells with a cell size of 1x1 pixels.
        super(1024, 800, 1); 
        
        //set default variables
        numChildren = 5;
        numHealer = 0;
        numFighter = 3;
        numZombie = 0;

        addObject(new Traitor(), 800, 600);
        addObject(new Hippo(), 850, 150);
        addObject(new Hippo(), 750, 250);
        
        addObject(homeButton,79,739);
        homeButton.setLocation(72,754);
        
        setBackground("zoo.jpg");
    }
    
    public void act(){
        spawn();
    }
    
    public void spawn(){
        if(getObjects(Regular.class).size() < numChildren){
            addObject(new Regular(), Greenfoot.getRandomNumber(600)+100, Greenfoot.getRandomNumber(300)+300);
        }
        
        if(getObjects(Healer.class).size() < numHealer){
            addObject(new Healer(), Greenfoot.getRandomNumber(600)+100, Greenfoot.getRandomNumber(300)+300);
        }
        
        if(getObjects(Regular.class).size() < numFighter){
            addObject(new Fighter(), Greenfoot.getRandomNumber(600)+100, Greenfoot.getRandomNumber(300)+300);
        }
        
        if(getObjects(ZombieHippo.class).size() < numZombie){ //change zombie class
            addObject(new ZombieHippo(), Greenfoot.getRandomNumber(400)+100, Greenfoot.getRandomNumber(200)+400);
        }
    }
    
    public void setNumChild(int x){
        numChildren = x;
    }
    
    public void setNumHealer(int x){
        numHealer = x;
    }
    
    public void setNumFighter(int x){
        numFighter = x;
    }
    
    public void setNumZombie(int x){
        numZombie = x;
    }

}
