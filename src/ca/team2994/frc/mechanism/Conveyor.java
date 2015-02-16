package ca.team2994.frc.mechanism;
import ca.team2994.frc.robot.Constants;
import ca.team2994.frc.robot.Motor;

public class Conveyor {
	private Motor motor; 
	
	int time = Constants.getConstantAsInt(Constants.CONVEYOR_TIME);
	double speed = Constants.getConstantAsInt(Constants.CONVEYOR_SPEED);
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
	 * Moves the conveyer forward at a speed from the constants
	 */
	public void unload() {
		motor.set(speed);
	}

	/**
	 * Moves the conveyer backwards at a speed from the constants
	 */
	public void load() {
		motor.set(-speed);
	}

	/**
	 * Disables the motor
	 */
	public void stop() {
		motor.disable();
	}
}
