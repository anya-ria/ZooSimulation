import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Show a list of endings
 * Unlocked - completed
 * Locked - not completed yet
 * 
 * @author Vanessa Huo | Functions
 * @version 04/25/2024
 */
public class Collections extends World
{
    //Booleans that detect if an ending is completed 
    private static boolean end1 = false; //all children were killed
    private static boolean end2 = false; //zombie animals were defeated 
    private static boolean end3 = false; //boss level was defeated 
    
    private HomeButton returnKey;
    private Lock[] arr = new Lock[3];

    public Collections()
    {    
        // Create a new world with 1024x800 cells with a cell size of 1x1 pixels.
        super(1024, 800, 1);  
        setBackground("collectionsScreen.png");
        
        //Add return button
        returnKey = new HomeButton();
        addObject(returnKey, getWidth()-80, 750);
        
        addLocks();
    }

    /**
     * If an endng is completed, shows "unlock" icon 
     */
    public void act(){
        if(end1){
            arr[0].setAchieved();
        }
        if(end2){
            arr[1].setAchieved();
        }
        if(end3){
            arr[2].setAchieved();
        }
    }
    
    /**
     * Adds the locks to the Collections screen
     */
    private void addLocks(){
        for(int i=0; i<arr.length;i++){
            arr[i] = new Lock();
            addObject(arr[i], 260, (140*i+290));
        }
    }
    
    //Static methods that are called when an ending is completed (change boolean to true)
    public static void unlockEnd1(){
        end1 = true;
    }
    public static void unlockEnd2(){
        end2 = true;
    }
    public static void unlockEnd3(){
        end3 = true;
    }
}
