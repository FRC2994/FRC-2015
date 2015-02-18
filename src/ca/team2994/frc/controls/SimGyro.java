/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.team2994.frc.controls;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Gyro;

/**
 *
 * @author Mike
 */
public class SimGyro extends Gyro {
    
    private double resetAdjustment;
    private double initialAngle;
    
    public SimGyro(int channel, double initAngle) {
        super(channel);
        this.resetAdjustment = 0.0;
        this.initialAngle = initAngle;
    }
    
    public SimGyro(int channel) {
        this(channel, 90.0);
    }
    
    public SimGyro(AnalogInput channel, double initAngle) {
        super(channel);
        this.resetAdjustment = 0.0;
        this.initialAngle = initAngle;
    }
    
    public SimGyro(AnalogInput channel) {
        this(channel, 90.0);
    }
    
    public double getAngle() {
        return this.initialAngle - (super.getAngle() - this.resetAdjustment);
    }
    
    public double getAbsoluteAngle() {
        double val = this.getAngle() % 360;
        
        if(val >= 0.0) {
            return val;
        } else {
            return 360 + val;
        }
        
    }
    
    public void reset(double initAngle) {
        this.initialAngle = initAngle;
        this.resetAdjustment = super.getAngle();
    }
    
    public void reset() {
        this.reset(90);
    }
}