import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Regular children are defenseless and must flee at the sight of animals
 * 
 * @author Lucas
 * @version 2024/4/4
 */
public class Regular extends Child
{
    /**
     * Act - do whatever the Regular wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        runAway();
    }
    private void runAway(){
        double[] enemyDetails = detectNearestEnemy(Animal.class, 10000);
        if(enemyDetails[1] == -1) return;
        double[] vector = Utility.angleToVector(enemyDetails[0]);
        setLocation(getX()-vector[0], getY()-vector[1]);
    }
}
