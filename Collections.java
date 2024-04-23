import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Collections here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Collections extends World
{

    /**
     * Constructor for objects of class Collections.
     * 
     */
    private static boolean end1 = false; //heal 15 children
    private static boolean end2 = false; //get hit by 10 bananas
    private static boolean end3 = false;
    
    private HomeButton returnKey;
    private Lock[] arr = new Lock[3];

    public Collections()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 800, 1);  
        setBackground("collectionsScreen.png");

        returnKey = new HomeButton();
        addObject(returnKey, getWidth()-80, 750);
        addLocks();
    }

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
    
    private void addLocks(){
        for(int i=0; i<arr.length;i++){
            arr[i] = new Lock();
            addObject(arr[i], 260, (140*i+290));
        }
    }
    
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
