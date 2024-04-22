import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GoldCup here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
    
    public void setAchieved(){
        unlock = true;
    }
    
}
