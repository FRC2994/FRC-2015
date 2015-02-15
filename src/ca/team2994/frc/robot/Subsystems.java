package ca.team2994.frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;


public class Subsystems {	
	// Motors
	public static Motor rightFrontDrive;
	public static Motor rightRearDrive;
	public static Motor leftFrontDrive;
	public static Motor leftRearDrive;
	public static Motor rightArmMotor;
	public static Motor leftArmMotor;
	public static Motor forkliftMotor;
	public static Motor conveyorMotor;
	
	// Drive
	public static ERobotDrive robotDrive;
	
	// Encoders
	public static Encoder rightDriveEncoder;
	public static Encoder leftDriveEncoder;
	public static Encoder forkliftEncoder;
	
	// Sensor
	public static DigitalInput toteDetectionSensor;
	public static Gyro gyroSensor;
	
	//Compressor
	public static Compressor compressor;
	
	// USB
	public static EJoystick	driveJoystick;
	public static EGamepad controlGamepad;
	
	// Power Panel
	public static PowerDistributionPanel powerPanel;
	
	// Bling
	public static SerialPort blingPort;
	
	// Mechanisms
	public static Forklift forklift;
	//
	//Robot Arm
 	public static RobotArm robotArm;

	/**
	 * Initialize all of the subsystems, assumes that the constants file has been read already
	 */
	public static void initialize()
	{
		// Motors
		leftFrontDrive = new Motor(Constants.getConstantAsInt(Constants.PWM_RIGHT_FRONT_DRIVE), Constants.getConstantAsInt(Constants.MOTOR_TYPE_DRIVE));
		leftRearDrive = new Motor(Constants.getConstantAsInt(Constants.PWM_RIGHT_REAR_DRIVE), Constants.getConstantAsInt(Constants.MOTOR_TYPE_DRIVE));
		rightFrontDrive = new Motor(Constants.getConstantAsInt(Constants.PWM_LEFT_FRONT_DRIVE), Constants.getConstantAsInt(Constants.MOTOR_TYPE_DRIVE));
		rightRearDrive = new Motor(Constants.getConstantAsInt(Constants.PWM_LEFT_REAR_DRIVE), Constants.getConstantAsInt(Constants.MOTOR_TYPE_DRIVE));
		rightArmMotor = new Motor(Constants.getConstantAsInt(Constants.PWM_RIGHT_ARM), Constants.getConstantAsInt(Constants.MOTOR_TYPE_ARM));
		leftArmMotor = new Motor(Constants.getConstantAsInt(Constants.PWM_LEFT_ARM), Constants.getConstantAsInt(Constants.MOTOR_TYPE_ARM));
		forkliftMotor = new Motor(Constants.getConstantAsInt(Constants.PWM_FORKLIFT), Constants.getConstantAsInt(Constants.MOTOR_TYPE_FORKLIFT));
		conveyorMotor = new Motor(Constants.getConstantAsInt(Constants.PWM_CONVEYOR), Constants.getConstantAsInt(Constants.MOTOR_TYPE_CONVEYOR));
		
		// Drive
		robotDrive = new ERobotDrive(leftFrontDrive, leftRearDrive, rightFrontDrive, rightRearDrive);
		
		// Encoders
		rightDriveEncoder = new Encoder(Constants.getConstantAsInt(Constants.DIO_RIGHT_ENCODER_A), Constants.getConstantAsInt(Constants.DIO_RIGHT_ENCODER_B));
		leftDriveEncoder = new Encoder(Constants.getConstantAsInt(Constants.DIO_LEFT_ENCODER_A), Constants.getConstantAsInt(Constants.DIO_LEFT_ENCODER_B));
		forkliftEncoder = new Encoder(Constants.getConstantAsInt(Constants.DIO_FORKLIFT_ENCODER_A), Constants.getConstantAsInt(Constants.DIO_FORKLIFT_ENCODER_B));
		
		// USB
		driveJoystick = new EJoystick(Constants.getConstantAsInt(Constants.USB_DRIVE_STICK));
		controlGamepad = new EGamepad(Constants.getConstantAsInt(Constants.USB_CONTROL_GAMEPAD));

		// Power Panel
		powerPanel = new PowerDistributionPanel();
		
		//Compressor
		compressor = new Compressor(1);
		compressor.setClosedLoopControl(true);
		
		// Sensors
		toteDetectionSensor = new DigitalInput(Constants.getConstantAsInt(Constants.DIO_TOTE_DETECT_SENSOR));
		
		// Bling
		if (Constants.getConstantAsInt(Constants.BLING_ENABLED) > 0) {
			blingPort = new SerialPort(9600, Port.kMXP);
		}
		
		// Mechanisms
		forklift = new Forklift(forkliftMotor, forkliftEncoder);
		
		// Robot Arm
		robotArm = new RobotArm(leftArmMotor, rightArmMotor);
	}
}
