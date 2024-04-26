import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * The Zoo:)
 * 
 * @author <li> Vanessa Huo
 * @author <li> Lucas Fu
 * @author <li> Anya Shah
 * @author <li> Megan Lee
 * @author <li> Gennie Won
 * @author <li> Luke Xiao
 * 
 * @version 04/25/2024
 * <p>
 * <b> Credits: </b>
 * <p>
 * Images: 
 * <li> Buttons: Megan Lee
 * <li> Zombie Animals: Gennie Won
 * <li> Human Sprites (Children + Fighter + Healer + Traitor): https://docs.google.com/document/d/1sW2Eh0ZpOTb30mfv7Vz3xfyu8Zw5uIU2y6H37qHEoOk/edit
 * <li> Animal Sprites: https://forums.rpgmakerweb.com/index.php?threads/whtdragons-animals-and-running-horses-now-with-more-dragons.53552/
 * <li> Miscellaneous: https://docs.google.com/document/d/1BXjbhjg57LgRghTjHuzT30icTCHIeSzlp227fLNWW74/edit
 * <p>
 * Sounds:
 * <li> https://docs.google.com/document/d/1rRu4gmr6PWIPnOG4TaqbpQUJdSbmkpW4wQcUj5_YVuo/edit
 * <p>
 * Code: 
 *      author: Jordan Cohen -- (what code here)
 *<p>
 * Description: 
 * This simulation tells the story of a group of children who decided to go on a
 * field trip to the zoo. Unfortunately, disaster struck, and they were forced to
 * confront a traitor amongst them, as well as his undead creations. It is either
 * survival or death. But wait, it seems like something big is about to happen...
 * <p>
 * <b> Features: </b>
 * <li> Smooth pushing effects on all entities
 * <li> Fancy combat mechanics, such as pushing, slipping, and damage over time
 * <li> Children split into classes that focus on specific tasks
 * <li> An evil traitor that uses a combination of attacks to defeat children
 * <li> An utility class that converts an angle into a vector and vice versa
 * <li> Customizations are allowed for four types of variables. 
 * <li> Five achievements and three endings waiting to unlock. 
 * <li> The lightning has a chance to change normal animals into ZOMBIES!
 * <li> Fighter children attack the zombies. Don't lose them. 
 * <p>
 * <b> Known bugs: </b>
 * 
 * 
 */
public class Zoo extends World
{ 
    //Default setting for each param
    public static int numChildren = 5;
    public static int numHealer = 2;
    public static int numFighter = 1;
    public static int numZombie = 0;
    public static int numAnimals = 15; //Total animals

    //Counters use to detect if a achievement is completed
    private static int numHealed; //number of children healed
    private static int numHit; //number of children get hit by banana
    //private static int numDead; //number of children dead

    //Init button and world
    private EndingScreen world = new EndingScreen();
    private HomeButton homeButton = new HomeButton();
    private ZombieBoss boss = new ZombieBoss();

    private int actCount;

    private boolean bossFight; 

    private GreenfootSound musicBG;
    
    public Zoo()
    {    
        // Create a new world with 1024x800 cells with a cell size of 1x1 pixels.
        super(1024, 800, 1); 

        actCount=1;

        addObject(new Traitor(), 600, 600);
        addObject(homeButton,79,739);
        homeButton.setLocation(72,754);

        setPaintOrder (Lightning.class, Banana.class, Pencil.class);

        setBackground("zoo.jpg");
        bossFight = false;

        musicBG = new GreenfootSound("backgroundMusic.mp3");
        // Initialize sounds
        HealingEffect.init();
        Pencil.init();
        Banana.init();
        Traitor.init();
        Fighter.init();
        Hippo.init();
        SmashEffect.init();
    }

    public void act(){
        actCount++;
        spawn();
        checkAchi();
        check();
        checkEnd();
    }
    
    /**
     * A method that spawn animals and children according to preset values in the Customization screen.
     * If there is no preset value, then spawn default number of actors. 
     */
    public void spawn(){
        //Spawn Children according to set values(20 or 25 or 30)
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

        if(numAnimals == 0 && numZombie > 0 && bossFight != false){
            for(Zombie a: getObjects(Zombie.class))
            {
                removeObject(a);
            }
            addObject(boss, getWidth()/2, getHeight()/2);
            bossFight = true;
        }

        if (actCount % 720 == 0){
            Lightning lightning = new Lightning(100);
            addObject(lightning, 512, 400);
        }
    }

    /**
     * If an achievement is completed, called the static method to unlock achievement
     */
    public void checkAchi(){
        if(numHealed >= 15){ //cure 15 children
            Achievement.completeAchi0();
        }
        if(numHit >= 10){ //get hit by 10 bananas
            Achievement.completeAchi1();
        }
        if(numFighter == 0){ //have no fighter children
            Achievement.completeAchi2();
        }
        if(numChildren == 0){ //all children became zombies
            Achievement.completeAchi3();
        }
        if(numAnimals == 0 && numZombie > 0){ //all animals became zombies
            Achievement.completeAchi4();
        }
    }

    /**
     * If an ending is completed, called the static method to unlock ending
     */
    public void checkEnd(){
        if(numChildren == 0){
            Greenfoot.setWorld(world);
            Collections.unlockEnd1();
            world.ending1();
        }
        if(numZombie == 0 && actCount > 1000 && numAnimals > 0){
            Greenfoot.setWorld(world);
            Collections.unlockEnd2();
            world.ending2();
        }
        if(!boss.isAwake()){
            Greenfoot.setWorld(world);
            Collections.unlockEnd3();
            world.ending3();
        }
    }

    /**
     * Keep track of the number of children, fighters and zombies
     */
    public void check(){
        for(Regular other: getObjects(Regular.class))
        {
            if(!other.isAwake() && !other.isCounted())
            {
                numChildren--;
                other.counted();
            }
        }
        for(Fighter other: getObjects(Fighter.class))
        {
            if(!other.isAwake() && !other.isCounted())
            {
                numFighter--;
                other.counted();
            }
        }
        for(Zombie other: getObjects(Zombie.class))
        {
            if(!other.isAwake() && !other.isCounted())
            {
                numZombie--;
                other.counted();
            }
        }
    }

    //Increase the counter
    public static int healed(){ //number of children get healed
        return numHealed++;
    }

    public static int hit(){ //get hit by banana
        return numHit++;
    }

    public int getNumZombie(){
        int x = numZombie;
        return x;
    }

    public int getNumChild(){
        int x = numChildren;
        return x;
    } 

    public int getNumAnimal(){
        int x = numAnimals;
        return x;
    } 

    public static double getDistance (Actor a, Actor b){
        return Math.hypot (a.getX() - b.getX(), a.getY() - b.getY());
    }
    
    public void stopped() {
        musicBG.pause();
        if((getObjects(Lightning.class).size()!=0)) {
            Lightning.pauseSound();
        }
    }
    
    public void started (){
        musicBG.playLoop();
        if((getObjects(Lightning.class).size()!=0)) {
            Lightning.playSound();
        }
    }
}
