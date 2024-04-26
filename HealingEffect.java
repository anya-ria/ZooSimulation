import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * An effect that heals children whenever it appears. This effect is only used
 * by the healer
 * 
 * @author <li> Lucas Fu  | Functions
 * @author <li> Anya Shah | Sounds
 * @version 04/25/2024
 */
public class HealingEffect extends Effect
{
    private int healingLevel;
    private static GreenfootSound[] healingSound;
    private static int healingSoundIndex;
    
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
        playHealingSound();
    }
    
    /**
     * @override
     * heals all touching children
     */
    public void addedToWorld(World world){
        List<Child> children = getIntersectingObjects(Child.class);
        for(Child c : children){
            c.heal(healingLevel);
        }
    }
    
    
    // ***************************** SOUNDS ******************************* \\
    public static void init() {
        healingSoundIndex = 0;
        healingSound = new GreenfootSound[20];
        for(int i = 0; i < healingSound.length; i++) {
            healingSound[i] = new GreenfootSound("healpop.mp3");
        }
    }
    
    public static void playHealingSound() {
        healingSound[healingSoundIndex].setVolume(50);
        healingSound[healingSoundIndex].play();
        healingSoundIndex++;
        if(healingSoundIndex >= healingSound.length) {
            healingSoundIndex = 0;
        }
    }
}
