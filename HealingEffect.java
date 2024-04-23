import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * An effect that heals children whenever it appears. This effect is only used
 * by the healer
 * 
 * @author Lucas
 * @version 2024/4/10
 */
public class HealingEffect extends Effect
{
    private int healingLevel;
    /**
     * creates a new healing effect that is the specified size and level
     * @param size      how big the effect will be
     * @param level     how much healing is to be dealt
     */
    public HealingEffect(int size, int level){
        super(null, new GreenfootImage("empty.png"),10,10,100);
        getImage().scale(size,size);
        getImage().setColor(Color.YELLOW);
        getImage().fillOval(0,0,size,size);
        
        healingLevel = level;
    }
    /**
     * @override
     * gets touching children, and heals them
     */
    public void addedToWorld(World world){
        List<Child> children = getIntersectingObjects(Child.class);
        for(Child c : children){
            c.heal(healingLevel);
        }
    }
}
