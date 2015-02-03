package ca.team2994.frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;

public class Subsystems {
	// Motors
	public static Motor leftDrive;
	public static Motor rightDrive;
	
	// Drive 
	public static ERobotDrive robotDrive;

	// Encoders
	public static Encoder leftDriveEncoder;
	public static Encoder rightDriveEncoder;
	
	// USB devices
	public static EJoystick rightDriveJoystick;
	public static PowerDistributionPanel powerPanel;
	
	public static DigitalInput totesensor;
	
	public static SerialPort blingPort;
	
	
	/**
	 * Initialize all of the subsystems, assumes that the constants file has been read already
	 */
	public static void initialize() {
<<<<<<< HEAD
		leftDrive = new Motor(10, 2);
		rightDrive = new Motor(11, 2);
		robotDrive = new RobotDrive(leftDrive, rightDrive);
		rightDriveJoystick = new EJoystick(Constants.getConstantAsInt(Constants.RIGHT_DRIVE_STICK));
=======
		leftDrive = new Talon(Constants.getConstantAsInt(Constants.CENTER_LEFT_DRIVE_PWM));
		rightDrive = new Talon(Constants.getConstantAsInt(Constants.CENTER_RIGHT_DRIVE_PWM));
//		robotDrive = new ERobotDrive(leftDrive, rightDrive);
		rightDriveJoystick = new Joystick(Constants.getConstantAsInt(Constants.RIGHT_DRIVE_STICK));
>>>>>>> 0c68a658333b27ecf338a728c13796dee6125d3a
		leftDriveEncoder = new Encoder(Constants.getConstantAsInt(Constants.LEFT_ENCODER_A), Constants.getConstantAsInt(Constants.LEFT_ENCODER_B));
		rightDriveEncoder = new Encoder(Constants.getConstantAsInt(Constants.RIGHT_ENCODER_A), Constants.getConstantAsInt(Constants.RIGHT_ENCODER_B));
		powerPanel = new PowerDistributionPanel();
		totesensor = new DigitalInput(Constants.getConstantAsInt(Constants.TOTE_SENSOR));
		blingPort = new SerialPort(9600, Port.kMXP);
	}
}
