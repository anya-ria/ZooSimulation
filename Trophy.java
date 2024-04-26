import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Trophy.
 * Indication of an achievement get completed or not
 * 
 * @author <li> Megan Lee   | Art
 * @author <li> Vanessa Huo | Functions
 * @author <li> Gennie Won  | Sounds
 * @version 04/25/2024
 */
public class Trophy extends Icon
{
    //create new greenfoot images 
    private GreenfootImage grey = new GreenfootImage("golden_cup1.png");//unachieved cup
    private GreenfootImage gold = new GreenfootImage("golden_cup2.png");//achieved cups
    
    //Init sound effect
    private GreenfootSound achievement;
    
    //Create unlock
    private boolean unlock;
    
    //Sets trophy as unachieved at first
    public Trophy(){
        unlock = false; //set unlock as false because the achievements were not accomplished
        setImage(grey);
        grey.scale(70, 70);
        achievement = new GreenfootSound("achievement.mp3");//set new achievement sound
        
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
        unlock = true; //sets the unlock as true and play achievement sound
        achievement.setVolume(50); //set the volume to half its sound
        achievement.play(); //finally play the sound
    }
    
    public boolean getUnlock(){
        return unlock; //sets unlock boolean
    }
    
    public void stopped() {
        achievement.pause(); //stops the sound after it is paused
    }
}
