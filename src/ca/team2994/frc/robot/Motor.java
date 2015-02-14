package ca.team2994.frc.robot;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.SafePWM;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;

public class Motor implements SpeedController 
{
	SafePWM realMotor;
	CANJaguar canJaguar;
	CANTalon canTalon;
	
	/**
	 * 0 = PWM Talon
	 * 1 = PWM Victor
	 * 2 = CAN Talon
	 * 3 = CAN Jaguar
	 * default = PWM Jaguar
	 */
	int motorType;
	
    /**
     * Factory to return one of the different motor controllers: Talon, Jaguar and Victor.
     * The exact type of motor controller is chosen based on the Constants.ROBOT_TYPE
     *
     * @param channel The PWM or CAN channel that the motor is attached to
     */
	public Motor(int channel, int motorType)
	{
		this.motorType = motorType;
		
		if (motorType == 0) 
		{
			realMotor =  new Talon(channel);
		} 
		else if (motorType == 1) 
		{
			realMotor = new Victor(channel);
		} 
		else if(motorType == 2)
		{
			canTalon = new CANTalon(channel);
		}
		else if (motorType == 3)
		{
			canJaguar = new CANJaguar(channel);
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
		if(motorType == 0)
		{
			((Talon)realMotor).pidWrite(result);
		}
		else if(motorType == 1)
		{
			((Victor)realMotor).pidWrite(result);
		}
		else if(motorType == 2)
		{
			canTalon.pidWrite(result);
		}
		else if(motorType == 3)
		{
			canJaguar.pidWrite(result);
		}
		else
		{
			((Jaguar)realMotor).pidWrite(result);
		}
	}

	public double get() 
	{
		if(motorType == 0)
		{
			return ((Talon)realMotor).get();
		}
		else if(motorType == 1)
		{
			return ((Victor)realMotor).get();
		}
		else if(motorType == 2)
		{
			return canTalon.get();
		}
		else if(motorType == 3)
		{
			return canJaguar.get();
		}
		else
		{
			return ((Jaguar)realMotor).get();
		}
	}

	public void set(double speed, byte syncGroup) 
	{
		if(motorType == 0)
		{
			((Talon)realMotor).set(speed);
		}
		else if(motorType == 1)
		{
			((Victor)realMotor).set(speed);
		}
		else if(motorType == 2)
		{
			canTalon.set(speed);
		}
		else if(motorType == 3)
		{
			canJaguar.set(speed);
		}
		else
		{
			((Jaguar)realMotor).set(speed);
		}
	}

	public void set(double speed) 
	{
		if(motorType == 0)
		{
			((Talon)realMotor).set(speed);
		}
		else if(motorType == 1)
		{
			((Victor)realMotor).set(speed);
		}
		else if(motorType == 2)
		{
			canTalon.set(speed);
		}
		else if(motorType == 3)
		{
			canJaguar.set(speed);
		}
		else
		{
			((Jaguar)realMotor).set(speed);
		}
	}

	public void disable() 
	{
		if(motorType == 0)
		{
			((Talon)realMotor).disable();
		}
		else if(motorType == 1)
		{
			((Victor)realMotor).disable();
		}
		else if(motorType == 2)
		{
			canTalon.disable();
		}
		else if(motorType == 3)
		{
			canJaguar.disableControl(); // Why not default disabled?
		}
		else
		{
			((Jaguar)realMotor).disable();
		}
	}
	
	public void setExpiration(double timeout) 
	{
		if(motorType != 2 && motorType != 3)
		{
			realMotor.setExpiration(timeout);
		}
		else if(motorType == 2)
		{
			canTalon.setExpiration(timeout);
		}
		else
		{
			canJaguar.setExpiration(timeout);
		}
	}
}
