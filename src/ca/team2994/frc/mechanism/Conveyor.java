package ca.team2994.frc.mechanism;

import ca.team2994.frc.robot.*;

public class Conveyor {
	private Motor motor; // Include motor number for next time Note* 2 is for
							// Talon and will remain the same
	private double speed = .4;
	private double time = 20;

	/**
	 * Constructs the motor using values for the motor
	 * 
	 * @param channel
	 * @param type
	 */
	public Conveyor(int channel, int type) {
		motor = new Motor(channel, type);
	}

	/**
	 * Constructs the conveyor class by recording the motor used
	 * 
	 * @param conveyorMotor
	 */
	public Conveyor(Motor conveyorMotor) {
		motor = conveyorMotor;
	}

	/**
	 * Automatically loads totes for a given time and at a given speed
	 */
	public void load() {
		motor.set(speed);
		motor.setExpiration(time);
	}

	/**
	 * Automatically unloads totes for a given time and at a given speed
	 */
	public void unload() {
		motor.set(-speed);
		motor.setExpiration(time);
	}

	/**
	 * Moves the conveyer forward at a given speed (used in manual mode)
	 */
	public void forward() {
		motor.set(speed);
	}

	/**
	 * Moves the conveyer backwards at a given speed (used in manual mode)
	 */
	public void reverse() {
		motor.set(-speed);
	}

	/**
	 * Disables the motor
	 */
	public void stop() {
		motor.disable();
	}
}
