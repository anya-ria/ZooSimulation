import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.Random;
/**
 * The Entity is an abstract class that encompasses animals and children.
 * <li> manages the hp mechanics
 * <li> manages physics-y push mechanics
 * 
 * @author Lucas Fu
 * @version 04/25/2024
 */
public abstract class Entity extends SuperSmoothMover
{
    // hp variables
    private int maxHp;
    protected int hp;
    protected int[] wound = new int[] {0, 0};
    private SuperStatBar hpBar;
    
    // physics variables
    private double tempVx = 0; // temporary vx added from push
    private double tempVy = 0; // temporary vy added from push
    protected double friction = 0.95; // lower is more friction
    
    // stun due to slipping on a banana or anything else
    private double slippedDuration = 0;
    
    // "is this still alive"
    private boolean awake = true;
    
    // declare this for all subclasses
    protected Random rand = new Random();
    
    // number of entities healed
    private int numHeal;
    
    //Check if an entity is being counted
    private boolean check = false;
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
        animate();
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
    
    // ********************* ANIMATION SECTION **************************
    protected abstract void animate();
    
    // *********************** DAMAGE SECTION ***************************
    /**
     * finds the nearest entity withing radius of the specific class
     * @param type          The class that is to be found
     * @param detectRadius  The range in which to find enemies
     * @return double[] --  The details (direction, distance) of the enemy found, returns {0, -1} if not found
     */
    protected double[] detectNearestEntity(Class type, int detectRadius){
        // enemy details: {enemy direction, enemy distance}
        double[] enemyDetails = new double[] {0, -1}; 
        Entity nearestEnemy = null;
        
        // checks for entities in an increasing radius (quite CPU-consuming)
        detection: // <-- this is a label
        for(double i=5; i<=detectRadius; i+=5){
            // define list of enemies in range
            List<Entity> enemiesInRange = getObjectsInRange((int)i, type);
            // while there's still enemies in the list
            while(enemiesInRange.size()>0){
                // define nearest enemy as the first enemy on the list
                nearestEnemy = (Entity) enemiesInRange.get(0);
                // if that enemy is dead...
                if(!nearestEnemy.isAwake()){
                    // remove that enemy from the list
                    enemiesInRange.remove(0);
                    // reset nearest enemy
                    nearestEnemy = null;
                    // skip the rest of the loop
                    continue;
                }
                // defining some helper variables
                int x = nearestEnemy.getX(); int y = nearestEnemy.getY();
                // sets enemy distance to precise distance
                enemyDetails[1] = Math.sqrt((x-getX())*(x-getX())+(y-getY())*(y-getY()));
                // sets enemy direction according to position
                enemyDetails[0] = Utility.vectorToAngle(x-getX(), y-getY());
                // breaks the outer loop
                break detection;
            }
        }
        // if no enemy was found, return details with angle 0 and distance -1
        // seeing a "-1" in distance should mean NO ENTITY FOUND
        if(nearestEnemy==null) return new double[] {0, -1};
        return enemyDetails;
    }
    /**
     * Method to make the entity take damage, updates the hp and the stat bar
     * @param dmg   the damage to be taken
     */
    public void takeDamage(int dmg){
        // decrease hp
        hp -= dmg;
        // if that kills the entity
        if(hp<0){
            // set hp bar to 0
            hpBar.update(0);
            die();
            return;
        }
        // otherwise update the hp bar with the current hp value
        hpBar.update(hp);
    }
    /**
     * Method to make the entity take DOT damage, classified as "wound"
     * @param dmg         The damage to be taken every tick (10 acts)
     * @param duration    The length, in acts
     */
    public void getWounded(int dmg, int duration){
        // if the dot dmg exceeds the current wound damage...
        if(dmg > wound[0]){ 
            // replace the previous wound stack
            wound[0] = dmg; wound[1] = duration;
        } 
        // otherwise...
        else { 
            // refresh the wound and increase damage
            wound[0]++;
            wound[1] = duration;
        }
    }
    /**
     * Updates the wound, taking damage if the wound timer is a multiple of 30 acts
     */
    private void updateWound(){
        if(wound[1]>0){
            if(wound[1]%30==0) takeDamage(wound[0]);
            wound[1]--;
        }        
    }
    /**
     * heals the entity, but also checks if the entity is still alive
     * 
     * @param healing   the amount to be healed
     */
    public void heal(int healing){
        if(awake){
            hp += healing;
            if(hp>maxHp) hp = maxHp;
            hpBar.update(hp);
            //number of healed children increases by 1
            Zoo.healed();
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
        // add temporary speed to new position
        exactX = x+tempVx;
        exactY = y+tempVy; 
        // reduce temporary speed by value of friction
        tempVx *= friction; 
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
        // artificial temp velocity for animals too, but a bit less
        if(this instanceof Animal){
            if(exactX<100) tempVx += 0.5;
            if(exactX>924) tempVx -= 0.5;
            if(exactY<050) tempVy += 0.5;
            if(exactY>750) tempVy -= 0.5;
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
    /**
     * The Entity got pushed! Adds temporary x-velocity and y-velocity
     * @param vx    x-velocity
     * @param vy    y-velocity
     */
    public void push(double vx, double vy){
        tempVx += vx;
        tempVy += vy;
    }
    /**
     * Pushes the entity at a fixed speed towards a direction
     * @param angle     direction of push
     * @param speed     speed of push
     */
    public void push(int angle, double speed){
        push(Utility.angleToVector(angle)[0]*speed, 
             Utility.angleToVector(angle)[1]*speed);
    }
    public boolean isAwake(){
        return awake;
    }
    /**
     * Is this slipping?
     * @return boolean -- whether this is slipping
     */
    private boolean checkSlipping(){
        // if this hasn't finished slipping...
        if(slippedDuration>0){
            slippedDuration--;
            // keep using setLocation to keep the physics running
            setLocation(getX(), getY());
            // yes this is slipping
            return true;
        } 
        // otherwise...
        else if(slippedDuration==0){
            // set rotation back to normal
            setRotation(0);
            slippedDuration--; // effectively only makes this code run once
        }        
        // no this is not slipping
        return false;
    }
    protected boolean isPushed(){
        return (tempVx!=0 || tempVy!=0);
    }
    //check if the entity is counted as dead in Zoo world
    public boolean isCounted(){ 
        return check;
    }
    public void counted(){
        check = true;
    }
}
