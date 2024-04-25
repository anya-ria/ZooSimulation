import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * ChildParam.
 * Player set the values.
 * 
 * @author Megan Lee | Art
 * @author Vanessa Huo | Functions
 * @version 2024/04
 */
public class ChildParam extends Icon
{
    /**
     * Act - do whatever the ChildParam wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    private GreenfootImage[]list=new GreenfootImage[4];
    private GreenfootImage[]clicked=new GreenfootImage[4];
    
    public ChildParam(int x){
        for(int i=1; i<list.length;i++){
            list[i]=new GreenfootImage("images/params/childParam"+i+".png");
            GreenfootImage image = getImage();
            list[i].scale(70,70);
            setImage(list[1]);
        }

        for(int i=1; i<clicked.length;i++){
            clicked[i]=new GreenfootImage("images/paramSelect/childSelect"+i+".png");
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
            for(ChildParam other: getWorld().getObjects(ChildParam.class))
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
            //value = 20;
            value = 5;
        }
        else if(index == 2){
            //value = 25;
            value = 8;
        }
        else if(index == 3){
            //value = 30;
            value = 10;
        }
    }
    
}
