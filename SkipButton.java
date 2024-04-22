import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Button to skip cutscene.
 * 
 * @author Megan Lee
 * @version April 2024
 */
public class SkipButton extends Icon
{
    public SkipButton(){
        setImage("skipButton.png");
        GreenfootImage image = getImage();
        image.scale((int)(image.getWidth()*0.9), (int)(image.getHeight()*0.9));
    }  
    
    public void act(){
        if(Greenfoot.mouseClicked(this)){
            Zoo zoo = new Zoo();
            Greenfoot.setWorld(zoo);
            Greenfoot.playSound("mouseClick.mp3");
        }
    }
}
