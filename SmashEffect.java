import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * The SmashEffect is an effect that deals damage in an aoe
 * 
 * @author Lucas Fu | Functions
 * @author Anya Shah | Sounds
 * @version 2024/4/16
 */
public class SmashEffect extends Effect
{
    private int damage;
    private static GreenfootSound[] healingSound;
    private static int healingSoundIndex;
    
    /**
     * creates a new smash effect that is the specified size and damage
     * @param size    how big the effect will be (diameter)
     * @param dmg     how much damage is to be dealt
     */
    public SmashEffect(int size, int dmg){
        super(null, new GreenfootImage("empty.png"),10,10,100);
        getImage().scale(size,size);
        getImage().setColor(Color.GRAY);
        // creates a new circle on the image that has a diameter of "size"
        getImage().fillOval(0,0,size,size);
        
        damage = dmg;
        playHealingSound();
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
    
    // **************************** SOUNDS ******************************** \\
    public static void init() {
        healingSoundIndex = 0;
        healingSound = new GreenfootSound[20];
        for(int i = 0; i < healingSound.length; i++) {
            healingSound[i] = new GreenfootSound("heal.mp3");
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
