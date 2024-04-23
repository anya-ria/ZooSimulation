import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.World;
/**
 * Write a description of class AchieveButton here.
 * 
 * @author Megan Lee
 * @version April 2024
 */
public class AchieveButton extends Icon
{
    /**
     * Act - do whatever the AchieveButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public AchieveButton(){
        setImage("achievementsButton.png");
        GreenfootImage image = getImage();
        image.scale((int)(image.getWidth()*0.9), (int)(image.getHeight()*0.9));
    }  
    
    public void act(){
        if(Greenfoot.mouseClicked(this)){
            Achievement achi1 = new Achievement();
            Greenfoot.setWorld(achi1);
            Greenfoot.playSound("mouseClick.mp3");
        }
    }
    
}