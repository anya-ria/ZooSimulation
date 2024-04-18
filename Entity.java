import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * The Entity is an abstract class that encompasses animals and children.
 * <li> manages the hp mechanics
 * <li> manages physics-y push mechanics
 * 
 * @author Lucas
 * @version 2024/4/12
 */
public abstract class Entity extends SuperSmoothMover
{
    private int maxHp;
    protected int hp;
    protected int[] wound = new int[] {0, 0};
    private SuperStatBar hpBar;
    
    private double tempVx = 0; // temporary vx added from push
    private double tempVy = 0; // temporary vy added from push
    private double friction = 0.95;
    
    private double slippedDuration = 0;
    
    private boolean awake = true;
    
    /**
     * When constructed, sets the max hp and the hp
     * @param maxHp   the maximum hp the entity can have
     */
    protected Entity(int maxHp){
        this.maxHp = maxHp;
        hp = maxHp;
    }
    
    /**
     * updates all stuns, wounds and other stuff
     * @return boolean -- whether this should continue acting this cycle
     */
    public boolean update(){
        if(!isAwake()) return false;
        updateWound();
        if(checkSlipping()) return false;
        return true;
    }    
    
    /**
     * Creates a statbar when added to world
     * @override
     */
    public void addedToWorld(World world){
        hpBar = new SuperStatBar(maxHp, hp, this, 100, 10, -20, Color.GREEN, Color.RED, true);
        getWorld().addObject(hpBar, 0, 0);
    }
    
    
    // *********************** DAMAGE SECTION ***************************
    /**
     * finds the nearest entity withing radius of the specific class
     * @param type          The class that is to be found
     * @param detectRadius  The range in which to find enemies
     * @return double[] --  The details (direction, distance) of the enemy found, returns {0, -1} if not found
     */
    protected double[] detectNearestEntity(Class type, int detectRadius){
        double[] enemyDetails = new double[] {0, -1}; // {enemy direction, enemy distance}
        Entity nearestEnemy = null;
        for(double i=0; i<=detectRadius; i+=5){
            List<Entity> enemiesInRange = getObjectsInRange((int)i, type);
            while(enemiesInRange.size()>0){
                nearestEnemy = (Entity) enemiesInRange.get(0);
                if(!nearestEnemy.isAwake()){
                    enemiesInRange.remove(0);
                    nearestEnemy = null;
                    continue;
                }
                enemyDetails[1] = i;
                enemyDetails[0] = Utility.vectorToAngle(nearestEnemy.getX()-getX(), nearestEnemy.getY()-getY());
                i = detectRadius;
                break;
            }
        }
        if(nearestEnemy==null) return new double[] {0, -1};
        return enemyDetails;
    }
    /**
     * Method to make the entity take damage, updates the hp and the stat bar
     * @param dmg   the damage to be taken
     */
    public void takeDamage(int dmg){
        hp -= dmg;
        if(hp<0){
            hpBar.update(0);
            die();
            return;
        }
        hpBar.update(hp);
    }
    /**
     * Method to make the entity take DOT damage, classified as "wound"
     * @param dmg         The damage to be taken every tick (10 acts)
     * @param duration    The length, in acts
     */
    public void getWounded(int dmg, int duration){
        if(dmg > wound[0]){ // replace the previous wound stack
            wound[0] = dmg; wound[1] = duration;
        } else { // refresh the wound and increase damage
            wound[0]++;
            wound[1] = duration;
        }
    }
    private void updateWound(){
        if(wound[1]>0){
            if(wound[1]%30==0) takeDamage(wound[0]);
            wound[1]--;
        }        
    }
    /**
     * heals the entity, but also checks
     * if the entity is still alive
     * 
     * @param healing   the amount to be healed
     */
    public void heal(int healing){
        if(awake){
            hp += healing;
            if(hp>maxHp) hp = maxHp;
            hpBar.update(hp);
        }
    }
    protected void die(){
        setRotation(90);
        awake = false;
        hpBar.hide();
    }
    
    
    // ********************* PHYSICS SECTION *********************
    /**
     * @override
     * Overidden from SuperSmoothMover, now has temporary velocities
     * 
     * Set the location using exact coordinates.
     * Modified to include temporary velocities
     * 
     * @param x the new x location
     * @param y the new y location
     */
    public void setLocation(double x, double y) 
    {
        exactX = x+tempVx; // add temporary speed to new position
        exactY = y+tempVy; 
        tempVx *= friction; // reduce temporary speed
        tempVy *= friction;
        // bounce back if hitting border
        if(exactX<=0||exactX>=getWorld().getWidth()-1)
            tempVx *= -1; 
        if(exactY<=0||exactY>=getWorld().getHeight()-1)
            tempVy *= -1;
            
        // add artificial temp velocity if close to border
        // (we don't want to see children hugging the wall!)
        if(this instanceof Child){
            if(exactX<100) tempVx += 1;
            if(exactX>924) tempVx -= 1;
            if(exactY<050) tempVy += 1;
            if(exactY>750) tempVy -= 1;
        }
        super.setLocation(exactX, exactY);
    }
    /**
     * Set the location using integer coordinates.
     * (Overrides the method in SuperSmoothMover.)    
     * 
     * @param x the new x location
     * @param y the new y location
     */
    @Override
    public void setLocation(int x, int y) 
    {
        setLocation((double)x, (double)y);
    }
    /**
     * The Entity has slipped! Rotates upside-down and is now stunned for a duration
     */
    public void slip(){
        setRotation(180);
        slippedDuration = 200;
    }
    public void push(double vx, double vy){
        tempVx += vx;
        tempVy += vy;
    }
    public void push(int angle, double speed){
        push(Utility.angleToVector(angle)[0]*speed, 
             Utility.angleToVector(angle)[1]*speed);
    }
    public boolean isAwake(){
        return awake;
    }
    private boolean checkSlipping(){
        if(slippedDuration>0){
            slippedDuration--;
            setLocation(getX(), getY());
            return true;
        } else if(slippedDuration==0){
            setRotation(0);
            slippedDuration--; // effectively only makes this code run once
        }        
        return false;
    }
}
