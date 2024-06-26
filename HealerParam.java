import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * HealerParam.
 * 
 * @author <li> Megan Lee | Art
 * @author <li> Vanessa Huo | Functions
 * @version 04/25/2024
 */
public class HealerParam extends Icon
{
    private GreenfootImage[]list=new GreenfootImage[4];
    private GreenfootImage[]clicked=new GreenfootImage[4];
    private GreenfootImage x;
    
    public HealerParam(int x){
        for(int i=1; i<list.length;i++){
            list[i]=new GreenfootImage("images/params/healerParam"+i+".png");
            GreenfootImage image = getImage();
            list[i].scale(70,70);
            setImage(list[1]);
        }

        for(int i=1; i<clicked.length;i++){
            clicked[i]=new GreenfootImage("images/paramSelect/healerSelect"+i+".png");
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
            ArrayList<HealerParam> y = (ArrayList<HealerParam>)getWorld().getObjects(HealerParam.class);
            for(HealerParam other: y)
            {
               if(other != this)
              {
                 other.setClicked(false);
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
            value = 1;
        }
        else if(index == 3) {
            value = 5;
        }
    }
}
