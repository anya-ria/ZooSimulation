import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Lightning here.
 * 
 * @author Megan  
 * @version April 2024
 */
public class Lightning extends Effect
{
    public static final Color WHITE = new Color (255,255,255);
    private GreenfootImage[] lightningStrike = new GreenfootImage[3];
    private int imageIndex = 0, actCount = 0;

    public Lightning(GreenfootSound sound, GreenfootImage image, int fadeIn, int duration, int fadeOut){
        super(sound, image, fadeIn, duration, fadeOut);

        for(int i=1; i<4;i++) {
            lightningStrike[i-1] = new GreenfootImage("lightning" + i + ".png");
        }
    }

    public void act(){
        if(actCount < 121){
            if(actCount == 0 || actCount == 80) {
                setImage(new GreenfootImage("darkOverlay.png"));
            }
            else if(actCount  == 20){
                setImage(new GreenfootImage("darkOverlay.png"));
                GreenfootImage flash = new  GreenfootImage(1024, 800);
                flash.setColor(WHITE);
                flash.fill();
                setImage(flash);
            }
        }
        else if(actCount % 45 == 0 && actCount < 300){
            if(imageIndex < 3){
                setImage(lightningStrike[imageIndex]);
                imageIndex++;
            }
            else{
                setImage(new GreenfootImage("darkOverlay.png"));
            }
        }

        if(actCount == allActs[0] + allActs[1] + allActs[2]){
            getWorld().removeObject(this);
            return;
        }
        actCount++;
    }
}
