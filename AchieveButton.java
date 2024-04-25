import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.World;
/**
 * AchieveButton.
 * 
 * @author Megan Lee | Art
 * @author Vanessa Huo | Functions
 * @version April 2024
 */
public class AchieveButton extends Icon
{
    public AchieveButton(){
        setImage("achievementButton.png");
        GreenfootImage image = getImage();
        image.scale((int)(image.getWidth()*0.9), (int)(image.getHeight()*0.9));
    }  
    
    /**
     * If the button is clicked, go to achievement screen and play sound effect
     */
    public void act(){
        if(Greenfoot.mouseClicked(this)){
            Achievement achi1 = new Achievement();
            Greenfoot.setWorld(achi1);
            Greenfoot.playSound("mouseClick.mp3");
        }
    }
}