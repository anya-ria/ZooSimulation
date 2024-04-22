import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Effects that control visuals
 * 
 * @author Lucas
 */
public abstract class Effect extends SuperSmoothMover
{
    protected GreenfootImage image;
    protected int width, height;
    protected int currentPhase = 0;
    protected int currentActTime;
    protected int[] allActs = new int[3];
    private GreenfootSound sound = null;
    /**
     * constructs a new effect, with a sound, image, and 3 timers
     */
    public Effect(GreenfootSound sound, GreenfootImage image, int fadeIn, int duration, int fadeOut){
        this.image = image;
        setImage(this.image);
        allActs[0] = fadeIn;
        allActs[1] = duration;
        allActs[2] = fadeOut;
        currentActTime = allActs[0];
        this.sound = sound;
    }
    
    /**
     * constructs an effect that is in the middle of its lifespan 
     */
    public Effect(GreenfootSound sound, GreenfootImage image, int fadeIn, int duration, int fadeOut, int actTime, int phase){
        this(sound, image, fadeIn, duration, fadeOut);
        currentPhase = phase;
        currentActTime = actTime;
    }
    
    /**
     * manages the fade effect
     */
    protected void fade(boolean isFadeIn, int timeLeft, int totalFadeTime){
        double percent = (double)timeLeft/(double)totalFadeTime;
        if(percent > 1.00) return;
        if(isFadeIn) percent = 1.00-percent;
        int transparency = (int)(255*percent);
        image.setTransparency(transparency);
    }
    
    public void act(){
        switch(currentPhase){
            case 0:
                fade(true, currentActTime, allActs[0]);
                break;
            case 2:
                fade(false, currentActTime, allActs[2]);
                
        }
        currentActTime--;
        // if the sound stopped playing, play it again
        if(sound!=null && !sound.isPlaying()) sound.play();
        // if current phase is finished...
        if(currentActTime==0){
            // ...move onto next phase
            currentPhase++;
            // if there is no next phase...
            if(currentPhase==3){
                // ...remove this
                getWorld().removeObject(this);
                if(sound!=null && sound.isPlaying()){
                    sound.pause();
                }
                return;
            }
            // reset act timer to act length of new phase
            currentActTime = allActs[currentPhase];
        }  
        // remove this if it leaves the world boundaries
        if(getX()+width/2<0 || getX()-width/2>1024){
            getWorld().removeObject(this);
            return;
        }
    }
}

