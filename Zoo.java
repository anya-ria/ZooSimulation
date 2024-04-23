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
    private static int numHealed = 0;
    private static int numChildren = 5;
    private static int numHealer = 0;
    private static int numFighter = 3;
    private static int numZombie = 0;
    
    HomeButton homeButton = new HomeButton();
    
    public Zoo()
    {    
        // Create a new world with 1024x800 cells with a cell size of 1x1 pixels.
        super(1024, 800, 1); 
        
        //set default variables
        //numChildren = 5;
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
        checkAchi();
    }
    
    public void spawn(){
        if(getObjects(Regular.class).size() < numChildren){
            addObject(new Regular(), Greenfoot.getRandomNumber(600)+100, Greenfoot.getRandomNumber(300)+300);
        }
        
        if(getObjects(Healer.class).size() < numHealer){
            addObject(new Healer(), Greenfoot.getRandomNumber(600)+100, Greenfoot.getRandomNumber(300)+300);
        }
        
        if(getObjects(Fighter.class).size() < numFighter){
            addObject(new Fighter(), Greenfoot.getRandomNumber(600)+100, Greenfoot.getRandomNumber(300)+300);
        }
        
        if((getObjects(ZombieHippo.class).size() + getObjects(ZombieMonkey.class).size() + getObjects(ZombiePenguin.class).size())< numZombie){ //change zombie class
            addObject(new ZombieHippo(), Greenfoot.getRandomNumber(400)+100, Greenfoot.getRandomNumber(200)+400);
        }
    }
    
    public void checkAchi(){
        if(numFighter == 0){
            Achievement.completeAchi2();
        }
        if(numHealed >= 15){
            Achievement.completeAchi0();
        }
    }
    
    public static int healed(){
        return numHealed++;
    }
    
    public static void setNumChild(int x){
        numChildren = x;
    }
    
    public static void setNumHealer(int x){
        numHealer = x;
    }
    
    public static void setNumFighter(int x){
        numFighter = x;
    }
    
    public static void setNumZombie(int x){
        numZombie = x;
    }

}
