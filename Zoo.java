import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * The Zoo:)
 * 
 * @author Vanessa Huo
 * @author Lucas Fu
 * @author Anya Shah
 * @author Megan Lee
 * @author Gennie Won
 * @author Luke Xiao
 * 
 * @version April 2024
 *  * 
 * Credits: 
 * images: 
 *  children: 
 *      https://
 *      author: 
 * sounds: 
 *  lightning:
 *      https://
 *      author: 
 * Code: 
 *      author: Jordan Cohen
 *
 * Description: 
 * 
 * Known bugs:
 * 
 * 
 */
public class Zoo extends World
{ 
    //Default setting for each param
    private static int numChildren = 5;
    private static int numHealer = 2;
    private static int numFighter = 1;
    private static int numZombie = 0;
    private static int numAnimals = 25; //Total animals
    
    //Counters use to detect if a achievement is completed
    private static int numHealed = 0; //number of children healed
    private static int numHit = 0; //number of children get hit by banana
    private static int numDead = 0;//number of children dead
    
    //Init button and world
    private EndingScreen world = new EndingScreen();
    private HomeButton homeButton = new HomeButton();
    
    private int actCount;

    public Zoo()
    {    
        // Create a new world with 1024x800 cells with a cell size of 1x1 pixels.
        super(1024, 800, 1); 
        
        actCount=1;
        
        addObject(new Traitor(), 800, 600);
        addObject(homeButton,79,739);
        homeButton.setLocation(72,754);

        //addObject(new Healer(), 600, 200);
        //addObject(new Healer(), 400, 200);
        //addObject(new Fighter(), 800, 150);
        //addObject(new Hippo(), 850, 150);
        //addObject(new Hippo(), 750, 250);
        //addObject(new Penguin(), 750, 600);
        //addObject(new Penguin(), 850, 650);
        //addObject(new Monkey(), 150, 50);
        //addObject(new Monkey(), 250, 250);
        
        // for(Animal a: getObjects(Animal.class)){
            // a.zombify();
        // }
        
        setPaintOrder (Lightning.class, Banana.class, Pencil.class);
        
        setBackground("zoo.jpg");
        Greenfoot.playSound("backgroundMusic.mp3");
    }
    
    public void act(){
        actCount++;
        spawn();
        checkAchi();
        checkEnd();
        check();
    }
    
    /**
     * A method that spawn animals and children according to preset values in the Customization screen.
     * If there is no preset value, then spawn default number of actors. 
     */
    public void spawn(){
        //Spawn Chidlren according to set values(20 or 25 or 30)
        if(getObjects(Regular.class).size() < numChildren){
            addObject(new Regular(), Greenfoot.getRandomNumber(600)+100, Greenfoot.getRandomNumber(300)+300);
        }
        //Spawn Healer according to set values(0 or 1)
        if(getObjects(Healer.class).size() < numHealer){
            addObject(new Healer(), Greenfoot.getRandomNumber(600)+100, Greenfoot.getRandomNumber(300)+200);
        }
        //Spawn Fighter according to set values(0 or 15)
        if(getObjects(Fighter.class).size() < numFighter){
            addObject(new Fighter(), Greenfoot.getRandomNumber(600)+100, Greenfoot.getRandomNumber(300)+300);
        }
        //Spawn Zombie animals according to set values(3 or 6 or 9)
        if((getObjects(ZombieHippo.class).size() + getObjects(ZombieMonkey.class).size() + getObjects(ZombiePenguin.class).size())< numZombie){
            int x = Greenfoot.getRandomNumber(3);
            // Spawn random zombie animals
            if (x == 0){
                addObject(new ZombieHippo(), Greenfoot.getRandomNumber(250)+700, Greenfoot.getRandomNumber(250)+30);
            } else if (x == 1){
                addObject(new ZombieMonkey(),Greenfoot.getRandomNumber(290)+30, Greenfoot.getRandomNumber(245)+45);
            } else if (x == 2){
                addObject(new ZombiePenguin(), Greenfoot.getRandomNumber(280)+695, Greenfoot.getRandomNumber(250)+510);
            }
        }
        // Spawn random animals
        if((getObjects(Hippo.class).size() + getObjects(Monkey.class).size() + getObjects(Penguin.class).size())< numAnimals){
            int x = Greenfoot.getRandomNumber(3);
            // Spawn random animals
            if (x == 0){
                addObject(new Hippo(), Greenfoot.getRandomNumber(250)+700, Greenfoot.getRandomNumber(250)+30);
            } else if (x == 1){
                addObject(new Monkey(),Greenfoot.getRandomNumber(290)+30, Greenfoot.getRandomNumber(245)+45);
            } else if (x == 2){
                addObject(new Penguin(), Greenfoot.getRandomNumber(280)+695, Greenfoot.getRandomNumber(250)+510);
            }
        }
        
    
        //Add lightning. Error
        if (actCount % 600 == 0){
            Lightning lightning = new Lightning(300);
            addObject(lightning, 512, 400);
        }

    }
    
    /**
     * If an achievement is completed, called the static method to unlock achievement
     */
    public void checkAchi(){
        if(numHealed >= 15){
            Achievement.completeAchi0();
        }
        if(numHit >= 10){
            Achievement.completeAchi1();
        }
        if(numFighter == 0){
            Achievement.completeAchi2();
        }
        if(numChildren == 0){
            Achievement.completeAchi3();
        }
        if(numAnimals == 0){
            Achievement.completeAchi4();
        }
    }
    
    /**
     * If an ending is completed, called the static method to unlock ending
     */
    public void checkEnd(){
        if(numDead == numChildren){
            Greenfoot.setWorld(world);
            Collections.unlockEnd1();
            world.ending1();
        }
        if(numZombie == 0){
            Greenfoot.setWorld(world);
            Collections.unlockEnd2();
            world.ending2();
        }
        if(numZombie == 0){
            Greenfoot.setWorld(world);
            Collections.unlockEnd3();
            world.ending3();
        }
    }
    
    /**
     * Count the number of dead children
     */
    public void check(){
        ArrayList<Regular> y = (ArrayList<Regular>)getObjects(Regular.class);
        for(Regular other: y)
        {
            if(!other.isAwake() && !other.getCheck())
            {
                numDead++;
                other.setCheck();
            }
        }
    }
    
    //Increase the counter
    public static int healed(){ //number of children get healed
        return numHealed++;
    }
    public static int dead(){ //number of dead children
        return numDead++;
    }
    public static int hit(){ //get hit by banana
        return numHit++;
    }
    
    //Set the static variables 
    public static void setNumChild(int x){
        numChildren = x;
    }
    public static int getNumChild(){
        return numChildren;
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
    
    public static double getDistance (Actor a, Actor b)
    {
        return Math.hypot (a.getX() - b.getX(), a.getY() - b.getY());
    }

}
