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
    //sets the starts button
    public StartButton(){
        setImage("playButton.png"); //set play buttom image
        GreenfootImage image = getImage();
        image.scale((int)(image.getWidth()*0.9), (int)(image.getHeight()*0.9));
        //set image width of 0.9 and set height of 0.9
    }  
}