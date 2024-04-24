import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class Monkey here.
 * 
 * @author <li> Luke Xiao | Functions
 * @author <li> Anya Shah | Animations
 * @version 04/12/2024
 */
public class Monkey extends Animal
{
    // Monkey sprites
    private GreenfootImage[] walkRight = new GreenfootImage[3];
    private GreenfootImage[] walkLeft = new GreenfootImage[3];
    private GreenfootImage[] walkToward = new GreenfootImage[3];
    private GreenfootImage[] walkAway = new GreenfootImage[3];  

    // Animation variables
    private int animCounter, animDelay, animIndex;
    private int maxIndex;
    private boolean right, left, away, toward, zombie;

    public Monkey()
    {
        super(100);
        animCounter = 0;
        maxIndex = walkRight.length;
        initImages();
    }

    private void initImages() {
        // Initialize monkey images
        for(int i = 0; i < maxIndex; i++) {
            walkRight[i] = new GreenfootImage("monkeyWalkRight/monkeyWalkRight" + i + ".png");
        }
        for(int i = 0; i < maxIndex; i++) 
        {
            walkLeft[i] = new GreenfootImage("monkeyWalkRight/monkeyWalkRight" + i + ".png");
            walkLeft[i].mirrorHorizontally();
        }
        for(int i = 0; i < maxIndex; i++) 
        {
            walkAway[i] = new GreenfootImage("monkeyWalkAway/monkeyWalkAway" + i + ".png");
        }
        for(int i = 0; i < maxIndex; i++) 
        {
            walkToward[i] = new GreenfootImage("monkeyWalkToward/monkeyWalkToward" + i + ".png");
            walkToward[i].scale((int)(walkToward[i].getWidth()*1.3),(int)(walkToward[i].getHeight()*1.3));
        }
        animIndex = 0;
        animDelay = 10;
        animCounter = animDelay;
    }

    public void act()
    {
        // calls the update method from the Entity class, which returns whether this should continue acting
        if(!super.update()) return;
        moveAround();
        animate();
    }

    // Moving around in random motions in within its fences
    private void moveAround()
    {
        direction = Greenfoot.getRandomNumber(361);
        move(1);
        if (Greenfoot.getRandomNumber(240) < 10)
        {
            setRotation(direction);
            if (direction >= 315 || direction <= 45)
            {
                away = true;
                right = true;
            }
            if (direction > 45 && direction <= 135)
            {   
                right = true;
                away = false;
            }
            if (direction > 135 && direction <= 225)
            {
                right = false;
                away = false;
            }
            if (direction > 225 && direction <= 315)
            {
                right = false;
                away = true;
            }
        }
        if (getX() <= 30 || getX() >= 320)
        {
            turn(180);
        }
        if (getY() <= 45 || getY() >= 290)
        {
            turn(180);
        }
    }
    
    // Determining the animation needed for each directions
    protected void animate() {
        if(animCounter == 0) {
            animCounter = animDelay;
            animIndex++;
            if(animIndex >= maxIndex) {
                animIndex = 0;
            }
            if(right && away)
            {
                setImage(walkRight[animIndex]);
            } 
            else if (!right && !away)
            {
                setImage(walkLeft[animIndex]);
            }
            else if(left) 
            {
                setImage(walkLeft[animIndex]);
            } 
            else if(right && !away)
            {
                setImage(walkToward[animIndex]); 
            } 
            else 
            {
                setImage(walkAway[animIndex]);
            }
        }
        else if(toward)
        {
            setImage(walkToward[animIndex]);
        }
        else {
            animCounter--;
        }
    }
}
