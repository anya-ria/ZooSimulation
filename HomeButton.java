import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * HomeButton
 * Return to main title screen
 * 
 * @author <li> Megan Lee | Art
 * @author <li> Vanessa Huo | Functions
 * @version 04/25/2024
 */
public class HomeButton extends Icon
{
    //sets the home button
    public HomeButton(){
        setImage("homeButton.png"); //set image for the home button
        GreenfootImage image = getImage();
        image.scale((int)(image.getWidth()*0.9), (int)(image.getHeight()*0.9));
        //set the width as 0.9 and set the height as 0.9
    }  
    
    /**
     * If the button is clicked, go to title screen and play sound effect
     */
    public void act(){
        if(Greenfoot.mouseClicked(this)){
            TitleScreen game = new TitleScreen();
            
            (getWorld()).stopped();
            game.started();
            
            Greenfoot.setWorld(game);
            Greenfoot.playSound("mouseClick.mp3");
        }
    }
}
