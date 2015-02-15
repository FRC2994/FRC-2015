package ca.team2994.frc.mechanism;

import ca.team2994.frc.robot.Constants;
import ca.team2994.frc.robot.Motor;
import ca.team2994.frc.robot.Subsystems;

public class RobotArm {
	
	//Declare your speeds for different parts of the arm usage here
double FORWARD_SPEED = Constants.ARM_FORWARD_SPEED;
double REVERSE_SPEED = Constants.ARM_REVERSE_SPEED;
double PICKUP_SPEED = Constants.ARM_PICKUP_SPEED;
double DROPOFF_SPEED = Constants.ARM_DROPOFF_SPEED;
double LOAD_SPEED = Constants.ARM_LOAD_SPEED;
double UNLOAD_SPEED = Constants.ARM_UNLOAD_SPEED;
	private Motor m_leftArmMotor;
	private Motor m_rightArmMotor;

	public RobotArm(Motor leftArmMotor, Motor rightArmMotor) {
		m_leftArmMotor = leftArmMotor;
		m_rightArmMotor = rightArmMotor;
	}

	// Stop the arms from moving
	public void Stop() {
		m_leftArmMotor.set(0.0);
		m_rightArmMotor.set(0.0);
	}

	public void Forward() {
		//Move forward manually
		m_leftArmMotor.set(FORWARD_SPEED);
		m_rightArmMotor.set(FORWARD_SPEED * -1);

	}

	public void Reverse() {
		//Move in reverse manually
		m_leftArmMotor.set(REVERSE_SPEED * -1);
		m_rightArmMotor.set(REVERSE_SPEED);
	}

	public void pickup() {

		// Use the sensor to know when to run motors when picking up totes

		if (Subsystems.toteDetectionSensor.get() == false) {
			m_leftArmMotor.set(PICKUP_SPEED);
			m_rightArmMotor.set(PICKUP_SPEED * -1);
		}
		if (Subsystems.toteDetectionSensor.get()) {
			Stop();
		}

	}

	public void dropoff() {
		//Drop off totes on ground
		m_leftArmMotor.set(DROPOFF_SPEED * -1);
		m_rightArmMotor.set(DROPOFF_SPEED);
	}

	public void load() {
		//Load totes into storage area
		if (Subsystems.toteDetectionSensor.get()) {
			m_leftArmMotor.set(LOAD_SPEED);
			m_rightArmMotor.set(LOAD_SPEED * -1);
		}
		if (Subsystems.toteDetectionSensor.get() == false) {
			Stop();
		}
	}

	public void unload() {
		//Unload totes from storage area
		m_leftArmMotor.set(UNLOAD_SPEED * -1);
		m_leftArmMotor.set(UNLOAD_SPEED);
	}
}
