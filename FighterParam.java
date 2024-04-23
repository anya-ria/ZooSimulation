import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class FighterParam here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
            this.setClicked(true);
            setValue();
        }
    }
    
    public void setIcon(int x){
        if(x<list.length){
            setImage(list[x]);
        }
        index = x;
    }
    
    public void setValue(){
        if(index == 1){
            value = 0;
        }
        else if(index == 2){
            value = 15;
        }
    }
}
