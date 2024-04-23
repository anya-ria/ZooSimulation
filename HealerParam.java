import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class HealerParam here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HealerParam extends Icon
{
    /**
     * Act - do whatever the HealerParam wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private GreenfootImage[]list=new GreenfootImage[3];
    private GreenfootImage[]clicked=new GreenfootImage[3];
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
            setClicked(true);
            setValue();
        }
    }
    
    public void setValue(){
        if(index == 1){
            value = 0;
        }
        else if(index == 2){
            value = 1;
        }
    }
}
