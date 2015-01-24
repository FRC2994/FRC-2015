package ca.team2994.frc.robot;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.SafePWM;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;

public class Motor implements SpeedController 
{
	
	SafePWM realMotor;
	
    /**
     * Factory to return one of the different motor controllers: Talon, Jaguar and Victor.
     * The exact type of motor controller is chosen based on the Constants.ROBOT_TYPE
     *
     * @param channel The PWM channel that the motor is attached to
     */
	public Motor(int channel, int motorType)
	{
		if (motorType == 0) 
		{
			realMotor =  new Talon(channel);
		} 
		else if (motorType == 1) 
		{
			realMotor = new Victor(channel);
		} 
		else 
		{
			realMotor = new Jaguar(channel);
		}
	}

    /**
     * Write out the PID value as seen in the PIDOutput base object.
     *
     * @param output Write out the PWM value as was found in the PIDController
     */
	public void pidWrite(double result) 
	{
		if (realMotor instanceof Talon) 
		{
			((Talon)realMotor).pidWrite(result);
		}
		else if (realMotor instanceof Victor) 
		{
			((Victor)realMotor).pidWrite(result);
		} 
		else 
		{
			((Jaguar)realMotor).pidWrite(result);
		}
	}

	public double get() 
	{
		if (realMotor instanceof Talon) 
		{
			return ((Talon)realMotor).get();
		} 
		else if (realMotor instanceof Victor) 
		{
			return ((Victor)realMotor).get();
		}
		else 
		{
			return ((Jaguar)realMotor).get();
		}
	}

	public void set(double speed, byte syncGroup) 
	{
		if (realMotor instanceof Talon)
		{
			((Talon)realMotor).set(speed);
		}
		else if (realMotor instanceof Victor)
		{
			((Victor)realMotor).set(speed);
		} 
		else 
		{
			((Jaguar)realMotor).set(speed);
		}
	}

	public void set(double speed) 
	{
		if (realMotor instanceof Talon) 
		{
			((Talon)realMotor).set(speed);
		}
		else if (realMotor instanceof Victor) 
		{
			((Victor)realMotor).set(speed);
		} 
		else 
		{
			((Jaguar)realMotor).set(speed);
		}	
	}

	public void disable() 
	{
		if (realMotor instanceof Talon) 
		{
			((Talon)realMotor).disable();
		} 
		else if (realMotor instanceof Victor) 
		{
			((Victor)realMotor).disable();
		} 
		else 
		{
			((Jaguar)realMotor).disable();
		}	
	}
	
	public void setExpiration(double timeout) 
	{
		realMotor.setExpiration(timeout);
	}
}
