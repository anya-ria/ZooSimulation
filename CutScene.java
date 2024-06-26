import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A cutscene introducing the inciting incident of this simulation.
 * A teacher introduces her students to the zoo where lightning suddenly turns the animals into zombies.
 * 
 * @author Megan Lee
 * @version 04/25/2024
 */
public class CutScene extends World
{
    private int actCount; //track # of acts passed
    private SkipButton skipKey; //home button
    private GreenfootSound happyMusic = new GreenfootSound("happyCutScene.wav"); //happy music played before lightning
    
    /**
     * A constructor for the simulation's cutscene to set up the world and initalize objects.
     */
    public CutScene() {    
        super(1024, 800, 1); 
    
        //Set background to zoo
        setBackground("zoo.jpg"); 
        
        //Skip button can always be pressed, lightning appears above animals  
        setPaintOrder (Icon.class, Effect.class, Lightning.class, Animal.class);
        
        //Adds transition effect, a black screen that fades out
        Effect transition = new Effect(null, new GreenfootImage("blackScreen.png"), 0, 120, 60);
        addObject(transition, 512, 400);
        
        //Adds button for user to skip through cutscene in bottom left
        skipKey = new SkipButton();
        addObject(skipKey,79,739);
        skipKey.setLocation(952,754);
        
        actCount = 0;      
        happyMusic = new GreenfootSound("happyCutScene.wav");
        // Initialize sounds
        Hippo.init();
        Monkey.init();
        Penguin.init();
        
        spawnAnimals();
    }

    public void act(){
        if(actCount == 100) happyMusic.play();
        //After transition effect, add first teacher dialogue
        if(actCount == 180){
            Effect teacherText1 = new Effect(null, new GreenfootImage("teacherText1.png"), 20, 180, 20);
            addObject(teacherText1, 512, 400); 
        }
        //After first dialogue from teacher, add lightning effect
        if(actCount == 380){
            Lightning lightning = new Lightning(300);
            addObject(lightning, 512, 400); 
        }
        //During lightning effect, add second teacher dialogue
        if(actCount == 460){
            Effect teacherText2 = new Effect(null, new GreenfootImage("teacherText2.png"), 20, 180, 20);
            addObject(teacherText2, 512, 400); 
        }
        //After 680 acts, begin actual simulation in main world (zoo)
        if(actCount == 680){
            Lightning.stopSound();
            Zoo zoo = new Zoo();
            zoo.started();
            Greenfoot.setWorld(zoo);
        }
        actCount++;
    }

    /**
     * Spawns 3 of each type of animals in an initial location within their respective pens
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
     * Resumes longer sounds that were being played.
     * A method called by the Greenfoot system when the execution has stopped/paused.
     */
    public void started() {
        super.started();
        //From acts 180-380, if simulation was started: either resume previously played/start sound
        if(actCount >  180 && actCount < 380){
            happyMusic.play();
        }
        //if lightning effect is currently in world, resume sound
        if(getObjects(Lightning.class).size()!=0) {
            Lightning.playSound();
        }
    }

    /**
     * Pauses longer sounds that were being played.
     * A method called by the Greenfoot system when the execution has stopped/paused.
     */
    public void stopped() {
        super.stopped();
        happyMusic.pause();
        //if lightning effect is currently in world, resume sound
        if((getObjects(Lightning.class).size()!=0)) {
            Lightning.pauseSound();
        }
    }
}
