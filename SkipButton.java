import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Button to skip cutscene.
 * 
 * @author Megan Lee
 * @version 04/25/2024
 */
public class SkipButton extends Icon
{
    public SkipButton(){
        setImage("skipButton.png");
        GreenfootImage image = getImage();
        image.scale((int)(image.getWidth()*0.9), (int)(image.getHeight()*0.9));
    }  
    
    /**
     * If the button is clicked, set world as the Zoo and play click sound effect.
     */
    public void act(){
        if(Greenfoot.mouseClicked(this)){
            (getWorld()).stopped();
            Zoo zoo = new Zoo();
            zoo.started();
            Greenfoot.setWorld(zoo);
            Greenfoot.playSound("mouseClick.mp3");
        }
    }
}
