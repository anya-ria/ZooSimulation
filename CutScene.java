import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A cutscene introducing the inciting incident of this simulation.
 * A teacher introduces her students to the zoo, suddenly lightning turns the animals into zombies.
 * 
 * @author Megan Lee
 * @version 04/25/2024
 */
public class CutScene extends World
{
    int actCount;
    private GreenfootSound happyMusic;
    
    public CutScene() {    
        super(1024, 800, 1); 

        //Set default speed of slider as 50
        Greenfoot.setSpeed(50); 
    
        //Skip button can always be pressed, lightning appears above animals  
        setPaintOrder (Icon.class, Effect.class, Lightning.class, Animal.class);
        
        //Adds transition effect, a black screen that fades out
        Effect transition = new Effect(null, new GreenfootImage("blackScreen.png"), 0, 120, 60);
        addObject(transition, 512, 400);
        
        //Adds button for user to skip through cutscene
        SkipButton skipButton = new SkipButton();
        addObject(skipButton,79,739);
        skipButton.setLocation(952,754);
        
        setBackground("zoo.jpg"); 
        
        actCount = 0;      
        happyMusic = new GreenfootSound("happyCutScene.wav");
        
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
            Greenfoot.setWorld(zoo);
        }
        actCount++;
    }

    /**
     * Method that spawns 3 of each type of animals in an initial location within their respective pens
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
     * Method called by the Greenfoot system when the execution has started.
     */
    public void started() {
        //starts or resumes lightning sound when simulation is started
        super.started();
        if(actCount >  180 && actCount < 380){
            happyMusic.play();
        }
        if(getObjects(Lightning.class).size()!=0) {
            Lightning.playSound();
        }
    }

    /**
     * Method called by the Greenfoot system when the execution has stopped.
     */
    public void stopped() {
        //pauses lightning sound when simulation is paused
        super.stopped();
        happyMusic.pause();
        if((getObjects(Lightning.class).size()!=0)) {
            Lightning.pauseSound();
        }
    }
}
