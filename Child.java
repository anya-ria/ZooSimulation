import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * A child is an abstact class that represents all the children attending the zoo.
 * 
 * @author Lucas
 * @version 2024/4/4
 */
public abstract class Child extends SuperSmoothMover
{
    /**
     * Act - do whatever the Child wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    /**
     * finds the nearest enemy withing radius of the specific class
     * @param type          The class that is to be found
     * @param detectRadius  The range in which to find enemies
     * @return int[] --     The details (direction, distance) of the enemy found, returns {0, -1} if not found
     */
    protected double[] detectNearestEnemy(Class type, int detectRadius){
        double[] enemyDetails = new double[2]; // {enemy direction, enemy distance}
        SuperSmoothMover nearestEnemy = null;
        for(int i=0; i<=detectRadius; i++){
            List<SuperSmoothMover> enemiesInRange = getObjectsInRange(i, type);
            if(enemiesInRange.size()!=0){
                nearestEnemy = enemiesInRange.get(0);
                enemyDetails[1] = i;
                enemyDetails[0] = Utility.vectorToAngle(nearestEnemy.getX()-getX(), nearestEnemy.getY()-getY());
                break;
            }
        }
        if(nearestEnemy==null) return new double[] {0, -1};
        return enemyDetails;
    }
}
