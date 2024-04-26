import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * EndButton 
 * Go to the collections screen 
 * View Ending completion 
 * 
 * @author <li> Megan Lee | Art
 * @author <li> Vanessa Huo | Functions
 * @version 04/25/2024
 */
public class EndButton extends Icon
{
    //sets the end button into the world
    public EndButton(){
        setImage("endingButton.png");
        GreenfootImage image = getImage();
        image.scale((int)(image.getWidth()*0.9), (int)(image.getHeight()*0.9));
    }  
    
    /**
     * If the button is clicked, go to collections screen and play sound effect
     */
    public void act(){
        if(Greenfoot.mouseClicked(this)){
            (getWorld()).stopped();
            Collections collec1 = new Collections();
            Greenfoot.setWorld(collec1);
            Greenfoot.playSound("mouseClick.mp3");
        }
    }
}