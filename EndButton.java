import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EndButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EndButton extends Icon
{
    /**
     * Act - do whatever the EndButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public EndButton(){
        setImage("endingsButton.png");
        GreenfootImage image = getImage();
        image.scale((int)(image.getWidth()*0.9), (int)(image.getHeight()*0.9));
    }  
}