package ca.team2994.frc.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PowerDistributionPanel;


public class Subsystems {
	// Motors
	public static Motor leftFrontDrive;
	public static Motor rightFrontDrive;
	public static Motor leftRearDrive;
	public static Motor rightRearDrive;
	
	// Drive 
	public static ERobotDrive robotDrive;

	// Encoders
	public static Encoder leftDriveEncoder;
	public static Encoder rightDriveEncoder;
	
	// USB devices
	public static EJoystick rightDriveJoystick;
	public static PowerDistributionPanel powerPanel;
	
//	public static DigitalInput totesensor;
	
//	public static SerialPort blingPort;
	
	
	/**
	 * Initialize all of the subsystems, assumes that the constants file has been read already
	 */
	public static void initialize() {
		leftFrontDrive = new Motor(Constants.getConstantAsInt(Constants.LEFT_FRONT_DRIVE_PWM), Constants.getConstantAsInt(Constants.MOTOR_TYPE));
		leftRearDrive = new Motor(Constants.getConstantAsInt(Constants.LEFT_REAR_DRIVE_PWM), Constants.getConstantAsInt(Constants.MOTOR_TYPE));
		rightFrontDrive = new Motor(Constants.getConstantAsInt(Constants.RIGHT_FRONT_DRIVE_PWM), Constants.getConstantAsInt(Constants.MOTOR_TYPE));
		rightRearDrive = new Motor(Constants.getConstantAsInt(Constants.RIGHT_REAR_DRIVE_PWM), Constants.getConstantAsInt(Constants.MOTOR_TYPE));
		robotDrive = new ERobotDrive(leftFrontDrive, leftRearDrive, rightFrontDrive, rightRearDrive);
		rightDriveJoystick = new EJoystick(Constants.getConstantAsInt(Constants.RIGHT_DRIVE_STICK));
		leftDriveEncoder = new Encoder(Constants.getConstantAsInt(Constants.LEFT_ENCODER_A), Constants.getConstantAsInt(Constants.LEFT_ENCODER_B));
		rightDriveEncoder = new Encoder(Constants.getConstantAsInt(Constants.RIGHT_ENCODER_A), Constants.getConstantAsInt(Constants.RIGHT_ENCODER_B));
		powerPanel = new PowerDistributionPanel();
//		totesensor = new DigitalInput(Constants.getConstantAsInt(Constants.TOTE_SENSOR));
//		blingPort = new SerialPort(9600, Port.kMXP);
	}
}
