import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Trophy.
 * Indication of an achievement get completed or not
 * 
 * @author Megan Lee | Art
 * @author Vanessa Huo | Functions
 * @version April 2024
 */
public class Trophy extends Icon
{
    private GreenfootImage grey = new GreenfootImage("golden_cup1.png");
    private GreenfootImage gold = new GreenfootImage("golden_cup2.png");
    
    //Init sound effect
    private GreenfootSound achievement;
    
    private boolean unlock;
    
    public Trophy(){
        unlock = false;
        setImage(grey);
        grey.scale(70, 70);
    }
    
    /**
     * If boolean 'unlock' is true, change grey trophy to gold trophy. 
     */
    public void act(){
        if(unlock){
            setImage(gold);
            gold.scale(70, 70);
        }
    }
    
    /**
     * Set unlock to true and play sound effect.
     */
    public void setAchieved(){
        unlock = true;
        achievement = new GreenfootSound ("achievement.mp3");
        achievement.play();
        achievement.setVolume(50);
    }
    
    public boolean getUnlock(){
        return unlock;
    }
    
}
