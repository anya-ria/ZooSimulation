import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Trophy.
 * Indication of an achievement get completed or not
 * 
<<<<<<< HEAD
 * @author Megan Lee | Art
 * @author Vanessa Huo | Functions
 * @author Gennie Won | Sounds 
 * @version April 2024
=======
 * @author <li> Megan Lee | Art
 * @author <li> Vanessa Huo | Functions
 * @version 04/25/2024
>>>>>>> e3481e9eb85cbe3943b7a5cfde6a88c4de577ed3
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
        achievement = new GreenfootSound("achievement.mp3");
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
        achievement.setVolume(50);
        achievement.play();
    }
    
    public boolean getUnlock(){
        return unlock;
    }
}
