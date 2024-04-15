import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.Random;
/**
 * The traitor is a fighter child that no longer wants to cooperate with other children.
 * This character will throw bananas and pencils, and punch other children
 * 
 * @author Lucas
 * @version 2024/4/8
 */
public class Traitor extends Child
{
    private final int throwCooldown = 50;
    
    private int cooldown = throwCooldown;
    private Random rand = new Random();
    public Traitor(){
        super(100);
    }
    public void act(){
        if(!awake) return;
        super.act();
        chaseChildren();
    }
    public void chaseChildren(){
        double[] enemyDetails = detectNearestEntity(Child.class, 1000);
        double direction = enemyDetails[0];
        double distance = enemyDetails[1];
        double[] vector = Utility.angleToVector(direction);
        if(distance == -1){
            vector[0] = 0;
            vector[1] = 1;
        }
        if(distance<500 && distance > 10 && cooldown<=0){
            switch(rand.nextInt(2)){
                case 0:
                    throwBanana((int)direction, 4);
                    break;
                case 1:
                    throwPencil((int)direction, 4);
                    break;
            }
            cooldown = throwCooldown;
        }
        cooldown--;
        if(distance < 10){
            punch();
            return;
        }
        setLocation(getX()+vector[0]*1.2, getY()+vector[1]*1.2);
    }
    private void throwPencil(int direction, int speed){
        int modif = rand.nextInt(-10,11);
        getWorld().addObject(new Pencil(direction+modif, speed), getX(), getY());
    }
    private void throwBanana(int direction, int speed){
        int modif = rand.nextInt(-10,11);
        getWorld().addObject(new Banana(direction+modif, speed), getX(), getY());
    }
    private void punch(){
        double[] enemyDetails = detectNearestEntity(Child.class, 10);
        if(enemyDetails[1] == -1) return;
        Child enemy = getObjectsInRange(10, Child.class).get(0);
        enemy.takeDamage(10);
        enemy.push((int)enemyDetails[0], 10);
    }
}
