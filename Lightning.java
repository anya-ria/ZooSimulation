import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Lightning here.
 * 
 * @author Megan
 * @version April 2024
 */
public class Lightning extends SuperSmoothMover
{
    public static final Color WHITE = new Color (255,255,255);
    private GreenfootImage[] lightningStrike = new GreenfootImage[3];

    private int imageIndex = 0, actCount = 0, duration;
    private GreenfootSound sound;

    /**
     * Creates a new Lightning with specified sound and duration
     */
    public Lightning(GreenfootSound sound, int duration){
        this.duration = duration;
        this.sound = sound;
        for(int i=1; i<4;i++) {
            lightningStrike[i-1] = new GreenfootImage("lightning" + i + ".png");
        }
    }

    public void act(){
        // first 120 acts is the sky turning dark
        if(actCount < 121){
            if(actCount == 0 || actCount == 80) {
                setImage(new GreenfootImage("darkOverlay.png"));
                if(actCount == 80) turnIntoZombie();//
                //((CutScene)getWorld()).spawnAnimals();
            }
            else if(actCount  == 20){
                setImage(new GreenfootImage("darkOverlay.png"));
                GreenfootImage flash = new  GreenfootImage(1024, 800);
                flash.setColor(WHITE);
                flash.fill();
                setImage(flash);
                //((CutScene)getWorld()).removeAnimals(); //ERROR 
            }
        }
        // after those first few acts, lighning strikes every 45 acts, 3 times
        else if(actCount % 45 == 0 && actCount < 300){
            if(imageIndex < 3){
                setImage(lightningStrike[imageIndex]);
                imageIndex++;
            }
            else{
                setImage(new GreenfootImage("darkOverlay.png"));
            }
        }
    
        // after completely finishing acting, removes this effect
        if(actCount == duration){
            getWorld().removeObject(this);
            return;
        }
        actCount++;
    }
    
    public void removeAnimals(){
        
    }
    
    /**
     * When lightning strikes, animals hace a chance to turn into zombies!
     */
    public void turnIntoZombie(){
        for(Animal a: getWorld().getObjects(Animal.class)){
            if(Greenfoot.getRandomNumber(3) == 0){ //1/3 chance of turning into a zombie
                a.zombify();
                Zoo.numZombie++;
                Zoo.numAnimals--;
            }
        }
    }
}
