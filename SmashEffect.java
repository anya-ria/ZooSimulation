import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * The SmashEffect is an effect that deals damage in an aoe
 * 
 * @author Lucas
 * @version 2024/4/16
 */
public class SmashEffect extends Effect
{
    private int damage;
    /**
     * creates a new smash effect that is the specified size and damage
     * @param size    how big the effect will be (diameter)
     * @param dmg     how much damage is to be dealt
     */
    public SmashEffect(int size, int dmg){
        super(null, new GreenfootImage("empty.png"),10,10,100);
        getImage().scale(size,size);
        getImage().setColor(Color.GRAY);
        getImage().fillOval(0,0,size,size);
        
        damage = dmg;
        Greenfoot.playSound("heal.mp3");
    }
    /**
     * @override
     * Gets all touching children when added to world, making them take damage
     */
    public void addedToWorld(World world){
        List<Child> children = getIntersectingObjects(Child.class);
        for(Child c : children){
            c.takeDamage(damage);
        }
    }
}
