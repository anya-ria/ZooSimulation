import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * FighterParam.
 * 
 * @author Megan Lee | Art
 * @author Vanessa Huo | Functions
 * @version April 2024
 */
public class FighterParam extends Icon
{
    /**
     * Act - do whatever the FighterParam wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private GreenfootImage[]list=new GreenfootImage[3];
    private GreenfootImage[]clicked=new GreenfootImage[3];
    private GreenfootImage x;
    
    public FighterParam(int x){
        for(int i=1; i<list.length;i++){
            list[i]=new GreenfootImage("images/params/fighterParam"+i+".png");
            GreenfootImage image = getImage();
            list[i].scale(70,70);
            setImage(list[1]);
        }

        for(int i=1; i<clicked.length;i++){
            clicked[i]=new GreenfootImage("images/paramSelect/fighterSelect"+i+".png");
            GreenfootImage image = getImage();
            clicked[i].scale(70,70);
        }
        
        setImage(list[index]);
        index = x;
    }
    
    /**
     * If a button is clicked (option is choosen), set to 'clicked' image and reset all other options to 'unclicked'.
     */
    public void act(){
        if(!isClicked){ //false
            setImage(list[this.index]);
        }
        else if(isClicked){ //true
            setImage(clicked[this.index]);
        }
        
        if(Greenfoot.mouseClicked(this)){ //true
            ArrayList<FighterParam> y = (ArrayList<FighterParam>)getWorld().getObjects(FighterParam.class);
            for(FighterParam other: y)
            {
               if(other != this)
              {
                 other.setClicked(false);
                 other.reset();
              }
            }
            if(!isClicked){
                this.setClicked(true);
                setValue();
            }
            else{
                this.setClicked(false);
                value = 0;
            }
        }
    }
    
    /**
     * Set value according to the choosen option
     */
    public void setValue(){
        if(index == 1){
            value = 0;
        }
        else if(index == 2){
            value = 15;
        }
    }
}
