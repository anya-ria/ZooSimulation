import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * ZombieParam
 * 
 * @author Megan Lee | Art
 * @author Vanessa Huo | Functions
 * @version April 2024
 */
public class ZombieParam extends Icon
{
    private GreenfootImage[]list=new GreenfootImage[4];
    private GreenfootImage[]clicked=new GreenfootImage[4];
    private GreenfootImage x;
    
    public ZombieParam(int x){
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
            ArrayList<ZombieParam> y = (ArrayList<ZombieParam>)getWorld().getObjects(ZombieParam.class);
            for(ZombieParam other: y)
            {
               if(other != this)//Check for pervious clicked option
              {
                 other.setClicked(false);//unclick perviously chose option
              }
            }
            if(!isClicked){
                this.setClicked(true);
                setValue();
            }
            else{
                this.setClicked(false);
                value = 0;
            }//set corresponding value
        }
    }
    
    /**
     * Set value according to the choosen option
     */
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
