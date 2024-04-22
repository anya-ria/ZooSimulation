import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EndButton here.
 * 
 * @author Megan Lee
 * @version April 2024
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
    
    public void act(){
        if(Greenfoot.mouseClicked(this)){
            Collections collec1 = new Collections();
            Greenfoot.setWorld(collec1);
            Greenfoot.playSound("mouseClick.mp3");
        }
    }

}