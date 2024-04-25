import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
/**
 * Fights zombies...
 * 
 * @author <li> Anya Shah | Animations
 * @author <li> Lucas Fu  | Functions
 * @version 04/08/2024
 */
public class Fighter extends Child
{
    // Fighting sprites
    private GreenfootImage[] fightAway = new GreenfootImage[6];
    private GreenfootImage[] fightRight = new GreenfootImage[6];
    private GreenfootImage[] fightLeft = new GreenfootImage[6];
    private GreenfootImage[] fightToward = new GreenfootImage[6];

    // Animation variables
    private int animCounter, animDelay, animIndex;
    private int maxFightIndex, maxWalkIndex;
    private boolean right, left, away, toward, fighting;

    // Fighting variables
    private final int THROW_COOLDOWN = 50;
    private int cooldown = THROW_COOLDOWN;

    // Randomness
    Random rand = new Random();
    private int directionAdjustment = rand.nextInt(-30, 31);
    public Fighter(){
        super(200);

        animCounter = 0;
        maxFightIndex = fightAway.length;
        maxWalkIndex = walkAway.length;
        initImages();
    }

    public void act(){
        if(!super.update()) return;
        double[] enemyDetails = detectNearestEntity(Zombie.class, 500);
        if(enemyDetails[1]==-1) enemyDetails = detectNearestEntity(Traitor.class, 500);
        chaseZombies(enemyDetails);
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
            walkLeft[i].mirrorHorizontally();
        }

        animIndex = 0;
        animDelay = 5;
        animCounter = animDelay;
    }
    
    protected void animate() {
        if(animCounter == 0) {
            animCounter = animDelay;
            animIndex++;
            if(fighting) {
                if(animIndex >= maxFightIndex) {
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
                if(animIndex >= maxWalkIndex) {
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
            }
        }
        else {
            animCounter--;
        }
    }

    
    // **************************** FIGHTING ****************************** \\
    private void chaseZombies(double[] enemyDetails){
        double direction = enemyDetails[0] + directionAdjustment;
        double distance = enemyDetails[1];
        double[] vector = Utility.angleToVector(direction);
        fighting = false;
        if(distance == -1){ // couldn't find anything
            vector[0] = 0;
            vector[1] = 0;
        }
        // within throwing range
        if(distance<250 && distance >= 15 && cooldown<=0){
            fighting = true;
            throwPencil((int)direction, 6);
            cooldown = THROW_COOLDOWN;
        }
        cooldown--;
        // within punching range
        if(distance < 15){
            fighting = true;
            punch();
            setLocation(getX(), getY());
            return;
        }
        
        // update facing direction
        if(vector[0]>0 && Math.abs(vector[0])>Math.abs(vector[1])) {
            right = true;
            left = false; toward = false; away = false;
        }
        else if(vector[0]<0 && Math.abs(vector[0])>Math.abs(vector[1])) {
            left = true;
            right = false; toward = false; away = false;
        }
        else if(vector[1]<0 && Math.abs(vector[0])<Math.abs(vector[1])) {
            away = true;
            left = false; right = false; toward = false;
        }
        else if(vector[1]>0 && Math.abs(vector[0])<Math.abs(vector[1])) {
            toward = true; 
            left = false; right = false; away = false;
        }
        setLocation(getX()+vector[0], getY()+vector[1]);
    }
    
    private void throwPencil(int direction, int speed){
        int modif = rand.nextInt(-10,11);
        getWorld().addObject(new Pencil(2, 150, direction+modif, speed), getX(), getY());
        Pencil.playPencilSound();
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
        Greenfoot.playSound("punch2.mp3");
    }
}

