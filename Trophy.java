import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GoldCup here.
 * 
 * @author Megan Lee
 * @version April 2024
 */
public class Trophy extends Icon
{
    /**
     * Act - do whatever the GoldCup wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    private boolean unlock;
    private GreenfootImage grey = new GreenfootImage("golden_cup1.png");
    private GreenfootImage gold = new GreenfootImage("golden_cup2.png");
    
    public Trophy(){
        unlock = false;
        setImage(grey);
        grey.scale(70, 70);
    }
    
    public void act(){
        if(unlock){
            setImage(gold);
            gold.scale(70, 70);
        }
    }
    
    /**
     * Sets an achievement as "achieved", turning the trophy from silver to gold
     */
    public void setAchieved(){
        unlock = true;
        Greenfoot.playSound("achievement.mp3");
    }
}
