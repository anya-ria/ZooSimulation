import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Regular children are defenseless and must flee at the sight of animals
 * 
 * @author Lucas
 * @version 2024/4/4
 */
public class Regular extends Child
{
    public Regular(){
        super(100);
    }
    public void act()
    {
        if(!awake) return;
        runAway();
    }
    private void runAway(){
        double[] enemyDetails = detectNearestEntity(Animal.class, 10000);
        double[] vector;
        if(enemyDetails[1] != -1)
            vector = Utility.angleToVector(enemyDetails[0]);
        else
            vector = new double[] {0, 0}; 
        setLocation(getX()-vector[0], getY()-vector[1]);
    }
}
