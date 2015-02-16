package ca.team2994.frc.robot;

import java.io.File;
import java.io.IOException;
import java.util.List;

import ca.team2994.frc.autonomous.AutoHelper;
import ca.team2994.frc.mechanism.Forklift;
import ca.team2994.frc.mechanism.RobotArm;
import ca.team2994.frc.mechanism.StateMachine;

import com.google.common.base.Charsets;
import com.google.common.collect.Iterables;
import com.google.common.io.Files;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
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
	public static SimGyro gyroSensor;
	
	//Compressor
	public static Compressor compressor;
	
	//Solenoid - Gear control
	public static DoubleSolenoid gearShiftSolenoid;
	// USB
	public static EJoystick	driveJoystick;
	public static EGamepad controlGamepad;
	
	// Power Panel
	public static PowerDistributionPanel powerPanel;
	
	// Bling
	public static SerialPort blingPort;
	
	// Mechanisms
	public static Forklift forklift;
	
	//Robot Arm
 	public static RobotArm robotArm;
 	
 	// PIDs
 	public static SimPID gyroPID;
 	public static SimPID encoderPID;
 	
 	// State Machine
 	public static StateMachine stateMachine;

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
		compressor = new Compressor(Constants.getConstantAsInt(Constants.COMPRESSOR_CHANNEL));
		compressor.setClosedLoopControl(true); // turn back on when compressor is ready
		
		//Solenoid - Gear shift
		gearShiftSolenoid = new DoubleSolenoid(Constants.getConstantAsInt(Constants.SOLENOID_SHIFTER_CHANNEL1),Constants.getConstantAsInt(Constants.SOLENOID_SHIFTER_CHANNEL2));
		
		// Sensors
		toteDetectionSensor = new DigitalInput(Constants.getConstantAsInt(Constants.DIO_TOTE_DETECT_SENSOR));
		gyroSensor = new SimGyro(Constants.getConstantAsInt(Constants.AIO_GYRO_SENSOR));
		gyroSensor.initGyro();

		// Bling
		if (Constants.getConstantAsInt(Constants.BLING_ENABLED) > 0) {
			blingPort = new SerialPort(9600, Port.kMXP);
		}
		
		// Mechanisms
		forklift = new Forklift(forkliftMotor, forkliftEncoder);

		// Robot Arm
		robotArm = new RobotArm(leftArmMotor, rightArmMotor);
		
		//PIDs
		gyroPID = new SimPID(
				Constants.getConstantAsDouble(Constants.GYRO_PID_P),
				Constants.getConstantAsDouble(Constants.GYRO_PID_I),
				Constants.getConstantAsDouble(Constants.GYRO_PID_D),
				Constants.getConstantAsDouble(Constants.GYRO_PID_E)
		);
		
		encoderPID = new SimPID(
				Constants.getConstantAsDouble(Constants.ENCODER_PID_P),
				Constants.getConstantAsDouble(Constants.ENCODER_PID_I),
				Constants.getConstantAsDouble(Constants.ENCODER_PID_D),
				Constants.getConstantAsDouble(Constants.ENCODER_PID_E)
		);
		
		// Robot Arm
		robotArm = new RobotArm(leftArmMotor, rightArmMotor);
		
		// Set low gear by default
		robotDrive.setLowGear();
		
		// State Machine
		stateMachine = new StateMachine();
	}
	
	/**
	 * Read in the encoder values from the autonomous config file. TODO:
	 * Integrate this with Georges' Constants class.
	 */
	public static void readEncoderValues() {
		try {

			List<String> guavaResult = Files.readLines(new File(
					Constants.getConstant(Constants.CALIBRATION_FILE_LOC)), Charsets.UTF_8);
			Iterable<String> guavaResultFiltered = Iterables.filter(
					guavaResult, AutoHelper.SKIP_COMMENTS);

			String[] s = Iterables
					.toArray(AutoHelper.SPLITTER.split(guavaResultFiltered
							.iterator().next()), String.class);

			double encoderAConst = Double.parseDouble(s[0]);
			double encoderBConst = Double.parseDouble(s[1]);

			leftDriveEncoder.setDistancePerPulse(encoderAConst);
			rightDriveEncoder.setDistancePerPulse(encoderBConst);

		} catch (IOException e) {
			e.printStackTrace();
			leftDriveEncoder.setDistancePerPulse(1);
			rightDriveEncoder.setDistancePerPulse(1);
		}
	}
}
