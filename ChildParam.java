import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class ChildParam here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
    
    public void act(){
        if(!isClicked){ //false
            setImage(list[this.index]);
        }
        else if(isClicked){ //true
            setImage(clicked[this.index]);
        }
        
        if(Greenfoot.mouseClicked(this)){ //true
            ArrayList<ChildParam> y = (ArrayList<ChildParam>)getWorld().getObjects(ChildParam.class);
            for(ChildParam other: y)
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
    
    public void setValue(){
        if(index == 1){
            value = 20;
        }
        else if(index == 2){
            value = 25;
        }
        else{
            value = 30;
        }
    }
    
}
