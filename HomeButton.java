import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HomeButton here.
 * 
 * @author Megan Lee
 * @version April 2024
 */
public class HomeButton extends Icon
{
    /**
     * Act - do whatever the HomeButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public HomeButton(){
        setImage("homeButton.png");
        GreenfootImage image = getImage();
        image.scale((int)(image.getWidth()*0.9), (int)(image.getHeight()*0.9));
    }  
    
    public void act(){
        if(Greenfoot.mouseClicked(this)){
            TitleScreen game = new TitleScreen();
            Greenfoot.setWorld(game);
            Greenfoot.playSound("mouseClick.mp3");
        }
    }
}
