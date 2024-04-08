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
    private int maxHp;
    private int hp;
    private SuperStatBar hpBar;
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
    /**
     * When constructed, sets the max hp and the hp
     * @param maxHp the maximum hp the child can have
     */
    protected Child(int maxHp){
        this.maxHp = maxHp;
        hp = maxHp;
    }
    /**
     * Creates a statbar when added to world
     * @override
     */
    public void addedToWorld(World world){
        hpBar = new SuperStatBar(maxHp, hp, this, 100, 10, -20, Color.GREEN, Color.RED, false);
        getWorld().addObject(hpBar, 0, 0);
    }
    /**
     * Method to make the child take damage, updates the hp and the stat bar
     * @param dmg the damage to be taken
     */
    public void takeDamage(int dmg){
        hp -= dmg;
        if(hp<0){
            die();
            return;
        }
        hpBar.update(hp);
    }
    private void die(){
        setRotation(90);
        sleepFor(100000000);
    }
}
