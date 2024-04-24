import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Click to start the game
 * 
 * @author Megan Lee | Art
 * @author Vanessa Huo | Functions
 * @version April 2024
 */
public class StartButton extends Icon
{
    /**
     * Act - do whatever the StartButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    
    public StartButton(){
        setImage("startButton.png");
        GreenfootImage image = getImage();
        image.scale((int)(image.getWidth()*0.9), (int)(image.getHeight()*0.9));
    }  
    
    public void setValue(){
        
    }
}