/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.team2994.frc.utils;

/**
 *
 * @author Mike
 */
public class SimLib {
    
    public static double limitValue(double val) {
        return SimLib.limitValue(val, 1.0);
    }
    
    public static double limitValue(double val, double max) {
        if(val > max) {
            return max;
        } else if(val < -max) {
            return -max;
        } else {
            return val;
        }
    }
    
    public static double squareMaintainSign(double val) {
        double output = val * val;
        
        //was originally regative
        if(val < 0) {
            output = -output;
        }
        
        return output;
    }
    
    public static double power3MaintainSign(double val){
    	double output = val*val*val;
    	return output;
    }
    
    public static double calcLeftDrive(double x, double y) {
        return SimLib.limitValue(y + x);
    }
    
    public static double calcRightDrive(double x, double y) {
        return SimLib.limitValue(y - x);
    }
    
    
    
}
