package ca.team2994.frc.robot;

import edu.wpi.first.wpilibj.Encoder;
import ca.team2994.frc.mechanism.*;


public class Subsystems {
	// Motors
//	public static Motor leftFrontDrive;
//	public static Motor rightFrontDrive;
//	public static Motor leftRearDrive;
//	public static Motor rightRearDrive;
	
	// Drive 
//	public static ERobotDrive robotDrive;

	// Encoders
//	public static Encoder leftDriveEncoder;
//	public static Encoder rightDriveEncoder;
	
	// USB devices
//	public static EJoystick rightDriveJoystick;
//	public static PowerDistributionPanel powerPanel;
	
//	public static DigitalInput totesensor;
	
//	public static SerialPort blingPort;
	
	// Motors
	public static Motor 		rightFrontDrive;
	public static Motor 		rightRearDrive;
	public static Motor 		leftFrontDrive;
	public static Motor 		leftRearDrive;
	public static Motor         conveyorMotor;
	
	// Drive
	public static ERobotDrive	robotDrive;
	
	//Conveyor
	public static Conveyor      conveyorBelt;
	
	// Encoders
	public static Encoder 		rightDriveEncoder;
	public static Encoder 		leftDriveEncoder;
	public static Encoder 		forkliftEncoder;
	
	// USB
	public static EJoystick 	driveJoystick;
	public static EGamepad 		controlGamepad;
	
	/**
	 * Initialize all of the subsystems, assumes that the constants file has been read already
	 */
	public static void initialize()
	{
		// Motors
		rightFrontDrive = new Motor(Constants.getConstantAsInt(Constants.PWM_RIGHT_FRONT_DRIVE),   0);
		rightRearDrive  = new Motor(Constants.getConstantAsInt(Constants.PWM_RIGHT_REAR_DRIVE),    0);
		leftFrontDrive  = new Motor(Constants.getConstantAsInt(Constants.PWM_LEFT_FRONT_DRIVE),    0);
		leftRearDrive   = new Motor(Constants.getConstantAsInt(Constants.PWM_LEFT_REAR_DRIVE),     0);
		conveyorMotor   = new Motor(Constants.getConstantAsInt(Constants.PWM_CONVEYOR_MOTOR),      0);
		
		// Drive
		robotDrive = new ERobotDrive(leftFrontDrive, leftRearDrive, rightFrontDrive, rightRearDrive);
		
		// Conveyor
		conveyorBelt   = new Conveyor(conveyorMotor);
		
		// Encoders
		rightDriveEncoder = new Encoder(Constants.getConstantAsInt(Constants.DIO_RIGHT_ENCODER_A),    Constants.getConstantAsInt(Constants.DIO_RIGHT_ENCODER_B));
		leftDriveEncoder  = new Encoder(Constants.getConstantAsInt(Constants.DIO_LEFT_ENCODER_A),     Constants.getConstantAsInt(Constants.DIO_LEFT_ENCODER_B));
		forkliftEncoder   = new Encoder(Constants.getConstantAsInt(Constants.DIO_FORKLIFT_ENCODER_A), Constants.getConstantAsInt(Constants.DIO_FORKLIFT_ENCODER_B));
		
		// USB
		driveJoystick = new EJoystick(Constants.getConstantAsInt(Constants.USB_DRIVE_STICK));
		controlGamepad = new EGamepad(Constants.getConstantAsInt(Constants.USB_CONTROL_GAMEPAD));
		
//		leftFrontDrive = new Motor(Constants.getConstantAsInt(Constants.LEFT_FRONT_DRIVE_PWM), Constants.getConstantAsInt(Constants.MOTOR_TYPE));
//		leftRearDrive = new Motor(Constants.getConstantAsInt(Constants.LEFT_REAR_DRIVE_PWM), Constants.getConstantAsInt(Constants.MOTOR_TYPE));
//		rightFrontDrive = new Motor(Constants.getConstantAsInt(Constants.RIGHT_FRONT_DRIVE_PWM), Constants.getConstantAsInt(Constants.MOTOR_TYPE));
//		rightRearDrive = new Motor(Constants.getConstantAsInt(Constants.RIGHT_REAR_DRIVE_PWM), Constants.getConstantAsInt(Constants.MOTOR_TYPE));
//		robotDrive = new ERobotDrive(leftFrontDrive, leftRearDrive, rightFrontDrive, rightRearDrive);
//		rightDriveJoystick = new EJoystick(Constants.getConstantAsInt(Constants.RIGHT_DRIVE_STICK));
//		leftDriveEncoder = new Encoder(Constants.getConstantAsInt(Constants.LEFT_ENCODER_A), Constants.getConstantAsInt(Constants.LEFT_ENCODER_B));
//		rightDriveEncoder = new Encoder(Constants.getConstantAsInt(Constants.RIGHT_ENCODER_A), Constants.getConstantAsInt(Constants.RIGHT_ENCODER_B));
//		powerPanel = new PowerDistributionPanel();
//		totesensor = new DigitalInput(Constants.getConstantAsInt(Constants.TOTE_SENSOR));
//		blingPort = new SerialPort(9600, Port.kMXP);
	}
}
