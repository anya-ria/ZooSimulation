import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Click to start the game
 * 
 * @author <li> Megan Lee | Art
 * @author <li> Vanessa Huo | Functions
 * @version 04/25/2024
 */
public class StartButton extends Icon
{
    public StartButton(){
        setImage("playButton.png");
        GreenfootImage image = getImage();
        image.scale((int)(image.getWidth()*0.9), (int)(image.getHeight()*0.9));
    }  
}