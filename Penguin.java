import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class Penguin here.
 * 
 * @author <li> Luke Xiao | Functions
 * @author <li> Anya Shah | Animations
 * @author <li> Lucas Fu  | Cleanup
 * @version 04/12/2024
 */
public class Penguin extends Animal
{
    // Penguin walk sprites
    private GreenfootImage[] walkRight = new GreenfootImage[3];
    private GreenfootImage[] walkLeft = new GreenfootImage[3];
    private GreenfootImage[] walkAway = new GreenfootImage[3];
    private GreenfootImage[] walkToward = new GreenfootImage[3];

    // Animation variables
    private int animCounter, animDelay, animIndex; 
    private int maxSlideIndex, maxWalkIndex;
    private boolean right, left, away, toward, sliding;

    public Penguin()
    {
        super(100);
        animCounter = 0;
        maxWalkIndex = walkRight.length;
        initImages();
    }

    private void initImages()
    {
        // Initializing walking images
        for(int i = 0; i < maxWalkIndex; i++)
        {
            walkRight[i] = new GreenfootImage("penguinWalkRight/walkRight" + i + ".png");
            walkRight[i].scale((int)(walkRight[i].getWidth()*1.5),(int)(walkRight[i].getHeight()*1.5));
        }
        for(int i = 0; i < maxWalkIndex; i++)
        {
            walkLeft[i] = new GreenfootImage("penguinWalkRight/walkRight" + i + ".png");
            walkLeft[i].scale((int)(walkLeft[i].getWidth()*1.5),(int)(walkLeft[i].getHeight()*1.5));
            walkLeft[i].mirrorHorizontally();
        }
        for(int i = 0; i < maxWalkIndex; i++)
        {
            walkAway[i] = new GreenfootImage("penguinWalkAway/walkAway"+ i + ".png");
            walkAway[i].scale((int)(walkAway[i].getWidth()*1.5),(int)(walkAway[i].getHeight()*1.5));
        }
        for(int i = 0; i < maxWalkIndex; i++)
        {
            walkToward[i] = new GreenfootImage("penguinWalkToward/walkToward" + i + ".png");
            walkToward[i].scale((int)(walkToward[i].getWidth()*1.5),(int)(walkToward[i].getHeight()*1.5));
        }
        setImage(walkToward[1]);
        animIndex = 0;
        animDelay = 10;
        animCounter = animDelay;
    }

    public void act()
    {
        if(!super.update()) return;
        moveAround();
        animate();
    }

    // Moving in random motion within its fences
    private void moveAround()
    {
        direction = Greenfoot.getRandomNumber(361);
        move(1);
        if (Greenfoot.getRandomNumber(500) < 10)
        {
            setRotation(direction);
            right = false; away = false; left = false; toward = false;
            // The initial orientation of the images are facing RIGHT
            if (direction >= 315 || direction <= 45) // Right
            {
                right = true;
            }
            if (direction > 45 && direction <= 135) // Down
            {   
                toward = true;
            }
            if (direction > 135 && direction <= 225) // Left
            {
                left = true;
            }
            if (direction > 225 && direction <= 315) // Up
            {
                away = true;
            }
        }
        if (getX() <= 695 || getX() >= 970)

            if(Greenfoot.getRandomNumber(500) < 5){
                sliding = true;
            }
        if(Greenfoot.getRandomNumber(500) < 5){
            sliding = false;
        }
        if (getX() <= 695 || getX() >= 980)

        {
            turn(180);
        }
        if (getY() <= 510 || getY() >= 760)
        {
            turn(180);
        }
        animate();
    }

    protected void animate() {
        if(animCounter == 0) {
            animCounter = animDelay;
            animIndex++;
            if(animIndex >= maxWalkIndex) {
                animIndex = 0;
            }
            if(right)
            {
                setImage(walkRight[animIndex]);
            }
            else if(left)
            {
                setImage(walkLeft[animIndex]);
            }
            else if(away)
            {
                setImage(walkAway[animIndex]);
            }
            else if(toward)
            {
                setImage(walkToward[animIndex]);
            }
        }
        else
        {
            animCounter--;
        }
    }
}