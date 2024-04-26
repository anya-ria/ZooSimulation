import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * The traitor is a child that no longer wants to cooperate with other children.
 * <li> This character will imitate all other characters' attacks.
 * <li> Also has his own special move: Ground Pound
 * 
 * @author <li> Anya Shah | Animations + Sounds
 * @author <li> Lucas Fu  | Functions
 * @author Gennie Won | Sounds 
 * @version 04/08/2024
 */
public class Traitor extends Child
{
    // Punching sprites
    private GreenfootImage[] punchAway = new GreenfootImage[6];
    private GreenfootImage[] punchToward = new GreenfootImage[6];
    private GreenfootImage[] punchRight = new GreenfootImage[6];
    private GreenfootImage[] punchLeft = new GreenfootImage[6];
    
    // Animation variables
    private int maxPunchIndex;
    private boolean punching;
    
    // Sounds
    private static GreenfootSound[] laughSound;
    private static int laughSoundIndex;
    private static GreenfootSound[] punchSound;
    private static int punchSoundIndex;
    private static GreenfootSound[] healMeSound;
    private static int healMeSoundIndex;
    private static GreenfootSound[] dyingSound;
    private static int dyingSoundIndex;
    
    // Maximum attack cooldowns
    private final int MAX_THROW_COOLDOWN = 50;
    private final int MAX_HEAL_COOLDOWN = 100;
    private final int MAX_SMASH_COOLDOWN = 500;
    
    // Attack cooldowns
    private int throwCooldown = MAX_THROW_COOLDOWN;
    private int healCooldown = MAX_HEAL_COOLDOWN;
    private int smashCooldown = MAX_SMASH_COOLDOWN;
    private int stunDuration = 0;
    
    // Revives
    private int revives = 2;
    
    public Traitor(){
        super(200);
        
        animCounter = 0;
        maxPunchIndex = punchAway.length;
        maxWalkIndex = walkAway.length;
        initImages();
    }
    
    public void act(){
        // do not act if update methods returns false (check entity's update method)
        if(!super.update()) return;
        // if currenly stunned
        if(stunDuration>0){
            stunDuration--;
            // make sure this guy isn't animating when stunned
            animCounter = 10; 
            // keep a setLocation without moving to keep physics working
            setLocation(getX(), getY()); 
            return;
        }
        // find nearest child
        double[] childDetails = detectNearestEntity(Child.class, 1000);
        chaseChildren(childDetails);
    }
    
    
    // **************************** SOUNDS ****************************
    public static void init() {
        // Laughing sounds
        laughSoundIndex = 0;
        laughSound = new GreenfootSound[20];
        for(int i = 0; i < laughSound.length; i++) {
            laughSound[i] = new GreenfootSound("sinisterLaugh.mp3");
        }
        // Punching sounds
        punchSoundIndex = 0;
        punchSound = new GreenfootSound[20];
        for(int i = 0; i < punchSound.length; i++) {
            punchSound[i] = new GreenfootSound("swishingPunch.mp3");
        }
        // Self healing sounds
        healMeSoundIndex = 0;
        healMeSound = new GreenfootSound[20];
        for(int i = 0; i < healMeSound.length; i++) {
            healMeSound[i] = new GreenfootSound("healup.mp3");
        }
        // Dying sounds
        dyingSoundIndex = 0;
        dyingSound = new GreenfootSound[20];
        for(int i = 0; i < dyingSound.length; i++) {
            dyingSound[i] = new GreenfootSound("dyingGrunt.mp3");
        }
    }
    public static void playLaughSound() {
        laughSound[laughSoundIndex].setVolume(50);
        laughSound[laughSoundIndex].play();
        laughSoundIndex++;
        if(laughSoundIndex >= laughSound.length) {
            laughSoundIndex = 0;
        }
    }
    public static void playPunchSound() {
        punchSound[punchSoundIndex].setVolume(50);
        punchSound[punchSoundIndex].play();
        punchSoundIndex++;
        if(punchSoundIndex >= punchSound.length) {
            punchSoundIndex = 0;
        }
    }
    public static void playSelfHealSound() {
        healMeSound[healMeSoundIndex].setVolume(50);
        healMeSound[healMeSoundIndex].play();
        healMeSoundIndex++;
        if(healMeSoundIndex >= healMeSound.length) {
            healMeSoundIndex = 0;
        }
    }
    public static void playDyingSound() {
        dyingSound[dyingSoundIndex].setVolume(50);
        dyingSound[dyingSoundIndex].play();
        dyingSoundIndex++;
        if(dyingSoundIndex >= dyingSound.length) {
            dyingSoundIndex = 0;
        }
    }
    
    // ********************* IMAGES AND ANIMATIONS ************************
    private void initImages() {
        // Initialize punching images
        for(int i = 0; i < maxPunchIndex; i++) {
            punchAway[i] = new GreenfootImage("traitorPunchAway/punchAway" + i + ".png");
        }
        for(int i = 0; i < maxPunchIndex; i++) {
            punchToward[i] = new GreenfootImage("traitorPunchToward/punchToward" + i + ".png");
        }
        for(int i = 0; i < maxPunchIndex; i++) {
            punchRight[i] = new GreenfootImage("traitorPunchRight/punchRight" + i + ".png");
        }
        for(int i = 0; i < maxPunchIndex; i++) {
            punchLeft[i] = new GreenfootImage("traitorPunchRight/punchRight" + i + ".png");
            punchLeft[i].mirrorHorizontally();
        }
        // Initialize walking images
        for(int i = 0; i < maxWalkIndex; i++) {
            walkAway[i] = new GreenfootImage("traitorWalkAway/walkAway" + i + ".png");
        }
        for(int i = 0; i < maxWalkIndex; i++) {
            walkToward[i] = new GreenfootImage("traitorWalkToward/walkToward" + i + ".png");
        }
        for(int i = 0; i < maxWalkIndex; i++) {
            walkRight[i] = new GreenfootImage("traitorWalkRight/walkRight" + i + ".png");
        }
        for(int i = 0; i < maxWalkIndex; i++) {
            walkLeft[i] = new GreenfootImage("traitorWalkRight/walkRight" + i + ".png");
            walkLeft[i].mirrorHorizontally();
        }
        
        animIndex = 0;
        animDelay = 8;
        animCounter = animDelay;
    }
    protected void animate() {
        if(animCounter == 0) {
            animCounter = animDelay;
            animIndex++;
            if(punching) {
                if(animIndex >= maxPunchIndex) {
                    animIndex = 0;
                    punching = false;
                }
                if(right) {
                    setImage(punchRight[animIndex]);
                }
                else if(left) {
                    setImage(punchLeft[animIndex]);
                }
                else if(away) {
                    setImage(punchAway[animIndex]);
                }
                else if(toward) {
                    setImage(punchToward[animIndex]);
                }
            }
            else {
                updateWalking();
            }
        }
        else {
            animCounter--;
        }
    }
    
    // ************************ FIGHTING SECTION ************************* \\
    private void chaseChildren(double[] enemyDetails){
        double direction = enemyDetails[0];
        double distance = enemyDetails[1];
        double[] vector = Utility.angleToVector(direction);
        // if enemy does not exist
        if(distance == -1){
            // set x-velocity and y-velocity back to 0
            vector[0] = 0;
            vector[1] = 0;
        }
        
        // if below 75% hp and off cooldown
        if(hp<150 && healCooldown<=0){
            // stops punching animation
            punching = false;
            // heals himself
            selfHeal();
            // resets cooldown for both healing and throwing
            healCooldown = MAX_HEAL_COOLDOWN;
            throwCooldown = MAX_HEAL_COOLDOWN;
        }
        
        // if within throwing range and off cooldown
        if(distance<500 && distance>50 && throwCooldown<=0){
            // randomly throws a banana or a pencil
            switch(rand.nextInt(2)){
                case 0:
                    throwBanana((int)direction, 6);
                    break;
                case 1:
                    throwPencil((int)direction, 6);
                    break;
            }
            // starts punching animation
            punching = true;
            // resets cooldown
            throwCooldown = MAX_THROW_COOLDOWN;
        }
        
        // if within smashing distance, with enough hp, and off cooldown
        if(distance>=10 && distance<100 && hp>=100 && smashCooldown<=0){
            // smash
            smash();
            // start punching animation
            punching = true;
        }
        
        // reduce all cooldowns
        throwCooldown--; healCooldown--; smashCooldown--;
        
        // if within punching distance        
        if(distance < 15){
            // punch
            punch();
            // start punching animation
            punching = true;
            // still use setLocation to keep the physics running
            setLocation(getX(), getY());
            // skip the movement part
            return;
        }
        // move according to vector values
        setLocation(getX()+vector[0]*1.2, getY()+vector[1]*1.2);
        // update facing direction
        updateDirection(vector);
    }
    
    // ** traitor moves ** 
    private void throwPencil(int direction, int speed){
        // add some randomness to throwing
        int modif = rand.nextInt(-10,11); 
        // launches a new pencil with dot of 5dmg lasting 150 acts 
        getWorld().addObject(new Pencil(5, 150, direction+modif, speed), getX(), getY());
        Pencil.playPencilSound();
    }
    private void throwBanana(int direction, int speed){
        // add some randomness to throwing
        int modif = rand.nextInt(-10,11); 
        // launches a new Banana in the specified direction with the specified speed
        getWorld().addObject(new Banana(direction+modif, speed), getX(), getY());
        Banana.playBananaSound();
    }
    private void selfHeal(){
        // heals 40 hp in a tiny radius (20px) centered on the traitor
        getWorld().addObject(new HealingEffect(20, 40), getX(), getY());
        playSelfHealSound();
    }
    private void smash(){
        // creates a new smash effect of radius 200 dealing 99dmg to all affected
        getWorld().addObject(new SmashEffect(200, 99), getX(), getY());
        // heals self once
        selfHeal(); 
        // deal knockback inversely proportional to distance from traitor
        for(Entity e: getObjectsInRange(150, Entity.class)){
            double vx = (150-Math.abs(e.getX()-getX()))*Math.signum(e.getX()-getX())/8.0;
            double vy = (150-Math.abs(e.getY()-getY()))*Math.signum(e.getY()-getY())/8.0;
            e.push(vx, vy);
        }
        // resets cooldown
        smashCooldown = MAX_SMASH_COOLDOWN;
        // stuns for 200 acts (exhaution)
        stunDuration = 200;
    }
    private void punch(){
        // finds the details of the nearest child within 15px
        double[] enemyDetails = detectNearestEntity(Child.class, 15);
        // if nothing is found for some reason, do not punch
        if(enemyDetails[1] == -1) return;
        // gets a child within 15px
        Child enemy = getObjectsInRange(15, Child.class).get(0);
        // they take 10dmg
        enemy.takeDamage(10);
        // they get pushed away from this traitor
        enemy.push((int)enemyDetails[0], 10);
        playPunchSound();
    }
    private void revive(){
        // resets all wounds
        wound[0] = 0; wound[1] = 0;
        // heal for 200 hp
        for(int i=0; i<5; i++){ 
            selfHeal();
        }
        // push away everyone in range of 150px
        for(Entity e: getObjectsInRange(150, Entity.class)){
            double vx = (150-Math.abs(e.getX()-getX()))*Math.signum(e.getX()-getX())/8.0;
            double vy = (150-Math.abs(e.getY()-getY()))*Math.signum(e.getY()-getY())/8.0;
            e.push(vx, vy);
        }
        // throw out a ring of bananas and pencils
        for(int i=0; i<360; i+=10){
            getWorld().addObject(new Banana(i, 8), getX(), getY());
            getWorld().addObject(new Pencil(20, 150, i+5, 8), getX(), getY());
        }
        playLaughSound();
    }
    
    /**
     * @override, gives traitor ability to revive before dying
     */
    protected void die(){
        // revives instead of dying as long as the traitor has revives
        if(revives>0){
            revives--;
            revive();
        } else {
            super.die();
            playDyingSound();
        }
    }
}
