package ca.team2994.frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;


public class Subsystems {
	// Motors
	public static Talon leftDrive;
	public static Talon rightDrive;
	
	// Drive
	public static RobotDrive robotDrive;

	// USB devices
	public static Joystick rightDriveJoystick;

	/**
	 * Initialize all of the subsystems, assumes that the constants file has been read already
	 */
	public static void initialize() {
		leftDrive = new Talon(Constants.getConstantAsInt(Constants.CENTER_LEFT_DRIVE_PWM));
		rightDrive = new Talon(Constants.getConstantAsInt(Constants.CENTER_RIGHT_DRIVE_PWM));
		robotDrive = new RobotDrive(leftDrive, rightDrive);
		rightDriveJoystick = new Joystick(Constants.getConstantAsInt(Constants.RIGHT_DRIVE_STICK));
	}
}
