
/**
 * Write a description of class Utility here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Utility  
{
    /**
     * converts angle into x-value and y-value
     * @param angle     The angle
     * @return int[] -- the x-value and the y-value
     */
    public static double[] angleToVector(double angle){
        return new double[] {Math.cos(angle*Math.PI/180), Math.sin(angle*Math.PI/180)};
    }
    /**
     * converts x-value and y-value into angle
     * @param x          The x-value
     * @param y          The y-value
     * @return double -- The angle
     */
    public static double vectorToAngle(double x, double y){
        double angle = Math.atan(y/x)*180/Math.PI;
        if(x<0) angle += 180;
        return angle;
    }
}
