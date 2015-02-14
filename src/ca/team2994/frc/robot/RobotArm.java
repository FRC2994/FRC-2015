package ca.team2994.frc.robot;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;

public class RobotArm {
	public final double FORWARD_SPEED = 0.4;
	public final double REVERSE_SPEED = 0.4;
	private Motor m_leftArmMotor;
	private Motor m_rightArmMotor;

	public RobotArm(Motor leftArmMotor, Motor rightArmMotor) {
		m_leftArmMotor = leftArmMotor;
		m_rightArmMotor = rightArmMotor;
	}

	// Manually control the arms
	public void Stop() {
		m_leftArmMotor.set(0.0);
		m_rightArmMotor.set(0.0);
	}

	public void Forward() {

		m_leftArmMotor.set(FORWARD_SPEED);
		m_rightArmMotor.set(FORWARD_SPEED * -1);

	}

	public void Reverse() {

		m_leftArmMotor.set(REVERSE_SPEED * -1);
		m_rightArmMotor.set(REVERSE_SPEED);
	}

	public void pickup() {

		// Get sensor value here

		if (Subsystems.toteDetectionSensor.get() == false) {
			Forward();
		}
		if (Subsystems.toteDetectionSensor.get()) {
			Stop();
		}

	}

	public void dropoff() {
		Reverse();
	}

	public void load() {
		if (Subsystems.toteDetectionSensor.get()) {
			Forward();
		}
		if (Subsystems.toteDetectionSensor.get() == false) {
			Stop();
		}
	}

	public void unload() {
		Reverse();
	}
}
