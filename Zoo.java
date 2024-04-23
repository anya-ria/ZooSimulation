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

    private GreenfootSound lightningS;
    private GreenfootImage lightningI;
    private int actCount;
    
    private static int numHealed = 0; //number of children healed
    private static int numHit = 0; //number of children get hit by banana
    
    private static int numChildren = 5;
    private static int numHealer = 0;
    private static int numFighter = 3;
    private static int numZombie = 0;
    private static int numAnimals = 25;
    

    HomeButton homeButton = new HomeButton();
    
    public Zoo()
    {    
        // Create a new world with 1024x800 cells with a cell size of 1x1 pixels.
        super(1024, 800, 1); 
        
        actCount=1;
        addObject(new Traitor(), 800, 600);
        
        addObject(homeButton,79,739);
        homeButton.setLocation(72,754);

        addObject(new Healer(), 600, 200);
        addObject(new Healer(), 400, 200);
        addObject(new Fighter(), 800, 150);
        addObject(new Hippo(), 850, 150);
        addObject(new Hippo(), 750, 250);
        addObject(new Penguin(), 750, 600);
        addObject(new Penguin(), 850, 650);
        addObject(new Monkey(), 150, 50);
        addObject(new Monkey(), 250, 250);
        
        // for(Animal a: getObjects(Animal.class)){
            // a.zombify();
        // }

        
        lightningS = new GreenfootSound("lightning.mp3");
        lightningI = new GreenfootImage("darkOverlay.png");
        setBackground("zoo.jpg");
    }
    

    public void act(){
        actCount++;
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
        
        if((getObjects(ZombieHippo.class).size() + getObjects(ZombieMonkey.class).size() + getObjects(ZombiePenguin.class).size())< numZombie){
            int x = Greenfoot.getRandomNumber(3);
            if (x == 0){
                addObject(new ZombieHippo(), Greenfoot.getRandomNumber(400)+100, Greenfoot.getRandomNumber(200)+400);
            } else if (x == 1){
                addObject(new ZombieMonkey(),Greenfoot.getRandomNumber(400)+100, Greenfoot.getRandomNumber(200)+400);
            } else if (x == 2){
                addObject(new ZombiePenguin(), Greenfoot.getRandomNumber(400)+100, Greenfoot.getRandomNumber(200)+400);
            }
        }
        
        if((getObjects(Hippo.class).size() + getObjects(Monkey.class).size() + getObjects(Penguin.class).size())< numAnimals){
            int x = Greenfoot.getRandomNumber(3);
            if (x == 0){
                addObject(new Hippo(), Greenfoot.getRandomNumber(250)+700, Greenfoot.getRandomNumber(250)+30);
            } else if (x == 1){
                addObject(new Monkey(),Greenfoot.getRandomNumber(290)+30, Greenfoot.getRandomNumber(245)+45);
            } else if (x == 2){
                addObject(new Penguin(), Greenfoot.getRandomNumber(280)+695, Greenfoot.getRandomNumber(250)+510);
            }
        }
        
        if (actCount % 600 == 0){
            addObject(new Lightning(lightningS,lightningI,10,10,10), getWidth()/2, getHeight()/2);
        }
    }
    
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
    
    public static int healed(){
        return numHealed++;
    }
    
    public static int hit(){
        return numHit++;
    }
    
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

    public static double getDistance (Actor a, Actor b)
    {
        return Math.hypot (a.getX() - b.getX(), a.getY() - b.getY());

    }
}
