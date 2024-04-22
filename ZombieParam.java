import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class ChildParam here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ZombieParam extends Icon
{
    /**
     * Act - do whatever the ChildParam wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    private GreenfootImage[]list=new GreenfootImage[4];
    private GreenfootImage[]clicked=new GreenfootImage[4];
    private GreenfootImage x;
    
    public ZombieParam(){
        for(int i=1; i<list.length;i++){
            list[i]=new GreenfootImage("images/params/zombieParam"+i+".png");
            GreenfootImage image = getImage();
            list[i].scale(70,70);
            setImage(list[1]);
        }

        for(int i=1; i<clicked.length;i++){
            clicked[i]=new GreenfootImage("images/paramSelect/zombieSelect"+i+".png");
            GreenfootImage image = getImage();
            clicked[i].scale(70,70);
        }
    }
    
    public void setIcon(int x){
        if(x<list.length){
            setImage(list[x]);
        }
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
            ArrayList<ZombieParam> y = (ArrayList<ZombieParam>)getWorld().getObjects(ZombieParam.class);
            for(ZombieParam other: y)
            {
               if(other != this)
              {
                 other.setClicked(false);
              }
            }
            this.setClicked(true);
            setValue();
        }
    }
    
    public void setValue(){
        if(index == 1){
            value = 3;
        }
        else if(index == 2){
            value = 6;
        }
        else{
            value = 9;
        }
    }
    
}
