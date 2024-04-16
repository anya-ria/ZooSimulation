import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
/**
 * Fights zombies...
 * 
 * @author Anya Shah | Animations
 * @author Lucas Fu  | Functions
 * @version 2024/4/8
 */
public class Fighter extends Child
{
    // Fighting sprites
    private GreenfootImage[] fightAway = new GreenfootImage[6];
    private GreenfootImage[] fightRight = new GreenfootImage[6];
    private GreenfootImage[] fightLeft = new GreenfootImage[6];
    private GreenfootImage[] fightToward = new GreenfootImage[6];

    // Walking sprites
    private GreenfootImage[] walkAway = new GreenfootImage[9];
    private GreenfootImage[] walkRight = new GreenfootImage[9];
    private GreenfootImage[] walkLeft = new GreenfootImage[9];
    private GreenfootImage[] walkToward = new GreenfootImage[9];

    // Animation variables
    private int animCounter, animDelay, animIndex;
    private int maxFightIndex, maxWalkIndex;
    private boolean right, left, away, toward, fighting;

    // fighting variables
    private final int throwCooldown = 50;
    private int cooldown = throwCooldown;
    Random rand = new Random();

    public Fighter(){
        super(200);

        animCounter = 0;
        maxFightIndex = fightAway.length;
        maxWalkIndex = walkAway.length;
        initImages();
    }

    private void initImages() {
        // Initialize 4 fighting images
        for(int i = 0; i < maxFightIndex; i++) {
            fightAway[i] = new GreenfootImage("fightAway/fightAway" + i + ".png");
        }
        for(int i = 0; i < maxFightIndex; i++) {
            fightToward[i] = new GreenfootImage("fightToward/fightToward" + i + ".png");
        }
        for(int i = 0; i < maxFightIndex; i++) {
            fightRight[i] = new GreenfootImage("fightRight/fightRight" + i + ".png");
        }
        for(int i = 0; i < maxFightIndex; i++) {
            fightLeft[i] = new GreenfootImage("fightRight/fightRight" + i + ".png");
            fightLeft[i].mirrorHorizontally();
        }

        // Initialize 4 walking images
        for(int i = 0; i < maxWalkIndex; i++) {
            walkAway[i] = new GreenfootImage("fighterWalkAway/fighterWalkAway" + i + ".png");
        }
        for(int i = 0; i < maxWalkIndex; i++) {
            walkToward[i] = new GreenfootImage("fighterWalkToward/fighterWalkToward" + i + ".png");
        }
        for(int i = 0; i < maxWalkIndex; i++) {
            walkRight[i] = new GreenfootImage("fighterWalkRight/fighterWalkRight" + i + ".png");
        }
        for(int i = 0; i < maxWalkIndex; i++) {
            walkLeft[i] = new GreenfootImage("fighterWalkRight/fighterWalkRight" + i + ".png");
        }

        animIndex = 0;
        animDelay = 5;
        animCounter = animDelay;
    }
    
    private void animate() {
        if(animCounter == 0) {
            animCounter = animDelay;
            animIndex++;
            if(fighting) {
                if(animIndex == maxFightIndex) {
                    animIndex = 0;
                }
                if(right) {
                    setImage(fightRight[animIndex]);
                }
                else if(left) {
                    setImage(fightLeft[animIndex]);
                }
                else if(away) {
                    setImage(fightAway[animIndex]);
                }
                else if(toward) {
                    setImage(fightToward[animIndex]);
                }
            }
            else {
                if(animIndex == maxWalkIndex) {
                    animIndex = 0;
                }
                if(right) {
                    setImage(walkRight[animIndex]);
                }
                else if(left) {
                    setImage(walkLeft[animIndex]);
                }
                else if(away) {
                    setImage(walkAway[animIndex]);
                }
                else if(toward) {
                    setImage(walkToward[animIndex]);
                }
                // else {
                    // setImage("fighterWalkToward/fighterWalkToward.png");
                // }
            }
        }
        else {
            animCounter--;
        }
    }

    
    // **************************** FIGHTING ****************************** \\
    public void act(){
        if(!awake) return;
        super.act();
        chaseZombies();
    }
    private void chaseZombies(){
        double[] enemyDetails = detectNearestEntity(Animal.class, 500);
        double direction = enemyDetails[0];
        double distance = enemyDetails[1];
        double[] vector = Utility.angleToVector(direction);
        if(distance == -1){
            enemyDetails = detectNearestEntity(Traitor.class, 500);
            direction = enemyDetails[0];
            distance = enemyDetails[1];
            vector = Utility.angleToVector(direction);
            if(distance == -1){
                vector[0] = 0;
                vector[1] = 0;
            }
        }
        if(distance<250 && distance > 10 && cooldown<=0){
            throwPencil((int)direction, 8);
            cooldown = throwCooldown;
        }
        cooldown--;
        if(distance < 10){
            punch();
            return;
        }
        setLocation(getX()+vector[0], getY()+vector[1]);
    }
    
    private void throwPencil(int direction, int speed){
        int modif = rand.nextInt(-10,11);
        getWorld().addObject(new Pencil(6, 150, direction+modif, speed), getX(), getY());
    }
    
    private void punch(){
        double[] enemyDetails = detectNearestEntity(Animal.class, 10);
        if(enemyDetails[1] == -1){
            enemyDetails = detectNearestEntity(Traitor.class, 10);
            if(enemyDetails[1] == -1) return;
        }
        Entity enemy = getObjectsInRange(10, Entity.class).get(0);
        enemy.takeDamage(10);
        enemy.push((int)enemyDetails[0], 10);
    }
}

