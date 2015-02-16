package ca.team2994.frc.mechanism;

import ca.team2994.frc.mechanism.StateMachine.Event;
import ca.team2994.frc.robot.Constants;
import ca.team2994.frc.robot.Motor;
import ca.team2994.frc.robot.Subsystems;

public class RobotArm {
	
	//Declare your speeds for different parts of the arm usage here
	public double FORWARD_SPEED = Constants.getConstantAsDouble(Constants.ARM_FORWARD_SPEED);
	public double REVERSE_SPEED = Constants.getConstantAsDouble(Constants.ARM_REVERSE_SPEED);
	public double PICKUP_SPEED = Constants.getConstantAsDouble(Constants.ARM_PICKUP_SPEED);
	public double DROPOFF_SPEED = Constants.getConstantAsDouble(Constants.ARM_DROPOFF_SPEED);
	public double LOAD_SPEED = Constants.getConstantAsDouble(Constants.ARM_LOAD_SPEED);
	public double UNLOAD_SPEED = Constants.getConstantAsDouble(Constants.ARM_UNLOAD_SPEED);
	public int DROPOFF_TIME = Constants.getConstantAsInt(Constants.ARM_DROPOFF_TIME);
	public int UNLOAD_TIME = Constants.getConstantAsInt(Constants.ARM_UNLOAD_TIME);
	
	private Motor m_leftArmMotor;
	private Motor m_rightArmMotor;
	

	public RobotArm(Motor leftArmMotor, Motor rightArmMotor) {
		m_leftArmMotor = leftArmMotor;
		m_rightArmMotor = rightArmMotor;
		
	}

	// Stop the arms from moving
	public void stop() {
		m_leftArmMotor.set(0.0);
		m_rightArmMotor.set(0.0);
		Subsystems.stateMachine.callEvent(Event.AS);
		// Whatever we were doing before.. we are no longer in any automatic mode

	}

	public void forward() {
		//Move forward manually
		m_leftArmMotor.set(FORWARD_SPEED);
		m_rightArmMotor.set(FORWARD_SPEED * -1);
	}

	public void reverse() {
		//Move in reverse manually
		m_leftArmMotor.set(REVERSE_SPEED * -1);
		m_rightArmMotor.set(REVERSE_SPEED);
	}

	public void pickup() {
		// Use the sensor to know when to run motors when picking up totes
			m_leftArmMotor.set(PICKUP_SPEED);
			m_rightArmMotor.set(PICKUP_SPEED * -1);
	}

	public void dropoff() {
		//Drop off totes on ground
		m_leftArmMotor.set(DROPOFF_SPEED * -1);
		m_rightArmMotor.set(DROPOFF_SPEED);
	}

	public void load() {
		//Load totes into storage area
			m_leftArmMotor.set(LOAD_SPEED);
			m_rightArmMotor.set(LOAD_SPEED * -1);
	}

	public void unload() {
		//Unload totes from storage area
		m_leftArmMotor.set(UNLOAD_SPEED * -1);
		m_leftArmMotor.set(UNLOAD_SPEED);
	}

}