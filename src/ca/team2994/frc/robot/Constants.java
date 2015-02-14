package ca.team2994.frc.robot;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Constants
{
	private static final String CONSTANTS_FILE_NAME = "/home/lvuser/test.properties";
	
	private static Properties defaults = new Properties();
	private static Properties constants = new Properties();
	
	// PWM
	public static final String PWM_RIGHT_FRONT_DRIVE =	"PWM_RIGHT_FRONT_DRIVE";
	public static final String PWM_RIGHT_REAR_DRIVE =	"PWM_RIGHT_REAR_DRIVE";
	public static final String PWM_LEFT_FRONT_DRIVE = 	"PWM_LEFT_FRONT_DRIVE";
	public static final String PWM_LEFT_REAR_DRIVE =	"PWM_LEFT_REAR_DRIVE";
	
	public static final String PWM_RIGHT_ARM =			"PWM_RIGHT_ARM";
	public static final String PWM_LEFT_ARM =			"PWM_LEFT_ARM";
	public static final String PWM_FORKLIFT =			"PWM_FORKLIFT";
	public static final String PWM_CONVEYOR =			"PWM_CONVEYOR";
	
	// DIO
	public static final String DIO_RIGHT_ENCODER_A =	"DIO_RIGHT_ENCODER_A";
	public static final String DIO_RIGHT_ENCODER_B =	"DIO_RIGHT_ENCODER_B";
	public static final String DIO_LEFT_ENCODER_A =		"DIO_LEFT_ENCODER_A";
	public static final String DIO_LEFT_ENCODER_B =		"DIO_LEFT_ENCODER_B";
	
	public static final String DIO_DETECTION_SENSOR =	"DIO_DETECTION_SENSOR";
	public static final String DIO_FORKLIFT_ENCODER_A =	"DIO_FORKLIFT_ENCODER_A";
	public static final String DIO_FORKLIFT_ENCODER_B =	"DIO_FORKLIFT_ENCODER_B";
	public static final String DIO_GYRO =				"DIO_GYRO";
	
	// USB
	public static final String USB_DRIVE_STICK =		"USB_RIGHT_STICK";
	public static final String USB_CONTROL_GAMEPAD =	"USB_CONTROL_GAMEPAD";
	
	/* Property names */
	// Robot
//	public static final String ROBOT_NAME = "ROBOT_NAME";
//	
//	public static final String ROBOT_TYPE = "ROBOT_TYPE";
//	public static final String MOTOR_TYPE = "MOTOR_TYPE";
//	
//	public static final String PID_TARGET_1 = "PID_TARGET_1";
//	public static final String PID_TARGET_2 = "PID_TARGET_2";
//	public static final String PID_TARGET_3 = "PID_TARGET_3";
//	public static final String PID_TARGET_4 = "PID_TARGET_4";
//	
//	// PWMs
//	public static final String LEFT_FRONT_DRIVE_PWM   ="LEFT_FRONT_DRIVE_PWM";
//	public static final String LEFT_REAR_DRIVE_PWM    ="LEFT_REAR_DRIVE_PWM";
//	public static final String RIGHT_FRONT_DRIVE_PWM  ="RIGHT_FRONT_DRIVE_PWM";
//	public static final String RIGHT_REAR_DRIVE_PWM   ="RIGHT_REAR_DRIVE_PWM";
//	public static final String CENTER_LEFT_DRIVE_PWM  ="CENTER_LEFT_DRIVE_PWM";
//	public static final String CENTER_RIGHT_DRIVE_PWM ="CENTER_RIGHT_DRIVE_PWM";
//	public static final String INTAKE_MOTOR_PWM       ="INTAKE_MOTOR_PWM";
//	public static final String RIGHT_WINCH_MOTOR_PWM  ="RIGHT_WINCH_MOTOR_PWM";
//	public static final String LEFT_WINCH_MOTOR_PWM   ="LEFT_WINCH_MOTOR_PWM";

	// Solenoids
//	public static final String SHIFTER_A = "SHIFTER_A";
//	public static final String SHIFTER_B = "SHIFTER_B";
//	public static final String ARM_A = "ARM_A";
//	public static final String ARM_B = "ARM_B";	
//	public static final String EJECT_A = "EJECT_A";
//	public static final String EJECT_B = "EJECT_B";

	// Digital I/O
//	public static final String LEFT_ENCODER_A = "LEFT_ENCODER_A";
//	public static final String LEFT_ENCODER_B = "LEFT_ENCODER_B";
//	public static final String RIGHT_ENCODER_A = "RIGHT_ENCODER_A";
//	public static final String RIGHT_ENCODER_B = "RIGHT_ENCODER_B";
//	public static final String GYRO_SENSOR = "GYRO_SENSOR";
//	public static final String TOTE_SENSOR = "TOTE_SENSOR";
//	public static final String COMPRESSOR_PRESSURE_SW = "COMPRESSOR_PRESSURE_SW";

	// Relays
//	public static final String COMPRESSOR_SPIKE = "COMPRESSOR_SPIKE";

	// USB ports
//	public static final String RIGHT_DRIVE_STICK = "RIGHT_DRIVE_STICK";
//	public static final String LEFT_DRIVE_STICK  = "LEFT_DRIVE_STICK";
//	public static final String GAMEPAD_PORT = "GAMEPAD_PORT";

	// Left Joystick
//	public static final String BUTTON_SHIFT = "BUTTON_SHIFT";

	// Gamepad
//	public static final String BUTTON_SHOOT = "BUTTON_SHOOT";
//	public static final String BUTTON_INTAKE_COLLECT = "BUTTON_INTAKE_COLLECT";
//	public static final String BUTTON_INTAKE_EJECT = "BUTTON_INTAKE_EJECT";

	// Intake speeds
//	public static final String INTAKE_COLLECT = "INTAKE_COLLECT";
//	public static final String INTAKE_EJECT = "INTAKE_EJECT";

	
	static
	{
		// PWM
		defaults.put(PWM_RIGHT_FRONT_DRIVE, "3");
		defaults.put(PWM_RIGHT_REAR_DRIVE, "2");
		defaults.put(PWM_LEFT_FRONT_DRIVE, "1");
		defaults.put(PWM_LEFT_REAR_DRIVE, "0");
		
		defaults.put(PWM_RIGHT_ARM, "4");
		defaults.put(PWM_LEFT_ARM, "5");
		defaults.put(PWM_FORKLIFT, "6");
		defaults.put(PWM_CONVEYOR, "7");
		
		// DIO
		defaults.put(DIO_RIGHT_ENCODER_A, "9");
		defaults.put(DIO_RIGHT_ENCODER_B, "8");
		defaults.put(DIO_LEFT_ENCODER_A, "7");
		defaults.put(DIO_LEFT_ENCODER_B, "6");
		
		defaults.put(DIO_DETECTION_SENSOR, "2");
		defaults.put(DIO_FORKLIFT_ENCODER_A, "3");
		defaults.put(DIO_FORKLIFT_ENCODER_B, "4");
		defaults.put(DIO_GYRO, "5");
		
		// USB
		defaults.put(USB_DRIVE_STICK, "0");
		defaults.put(USB_CONTROL_GAMEPAD, "1");
		
		// define a default for each of the properties
//		defaults.put(ROBOT_NAME,			"Nameless!");
//		
//		defaults.put(ROBOT_TYPE,			"0");
//		defaults.put(MOTOR_TYPE,            "0");
//		defaults.put(PID_TARGET_1, 			"300");
//		defaults.put(PID_TARGET_2, 			"600");
//		defaults.put(PID_TARGET_3, 			"900");
//		defaults.put(PID_TARGET_4, 			"1200");
		
//		defaults.put(LEFT_FRONT_DRIVE_PWM,	"1");
//		defaults.put(LEFT_REAR_DRIVE_PWM,	"0");
//		defaults.put(RIGHT_FRONT_DRIVE_PWM,	"3");
//		defaults.put(RIGHT_REAR_DRIVE_PWM,	"2");
//		defaults.put(CENTER_LEFT_DRIVE_PWM,	"0"); 
//		defaults.put(CENTER_RIGHT_DRIVE_PWM,"1");
//		defaults.put(INTAKE_MOTOR_PWM,		"5");
//		defaults.put(RIGHT_WINCH_MOTOR_PWM,	"6"); 
//		defaults.put(LEFT_WINCH_MOTOR_PWM,	"9");
//		defaults.put(SHIFTER_A,				"1");
//		defaults.put(SHIFTER_B,				"2");
//		defaults.put(ARM_A,					"3");
//		defaults.put(ARM_B,					"4");
//		defaults.put(EJECT_A,				"5");
//		defaults.put(EJECT_B,				"6");
//		defaults.put(LEFT_ENCODER_A,		"6");
//		defaults.put(LEFT_ENCODER_B,		"7");
//		defaults.put(RIGHT_ENCODER_A,		"8");
//		defaults.put(RIGHT_ENCODER_B,		"9");
//		defaults.put(GYRO_SENSOR,			"5");
//		defaults.put(TOTE_SENSOR, 			"4");
//		defaults.put(COMPRESSOR_PRESSURE_SW,"8");
//		defaults.put(COMPRESSOR_SPIKE,		"2");
//		defaults.put(RIGHT_DRIVE_STICK,		"0");
//		defaults.put(LEFT_DRIVE_STICK,		"2");
//		defaults.put(GAMEPAD_PORT,			"3");
//		defaults.put(BUTTON_SHIFT,			"7");
//		defaults.put(INTAKE_COLLECT,		"-0.95");
//		defaults.put(INTAKE_EJECT,			"0.95");
	}
	
	/**
	 * Returns constant as a String
	 * @param constant name
	 * @return
	 */
	public static String getConstant(String name) {
		return constants.getProperty(name);
	}
	
	/**
	 * Returns constant as an int
	 * @param constant name
	 * @return
	 */
	public static int getConstantAsInt(String name) {
		return Integer.parseInt(constants.getProperty(name));
	}
	
	/**
	 * Returns constant as a double
	 * @param constant name
	 * @return
	 */
	public static double getConstantAsDouble(String name) {
		return Double.parseDouble(constants.getProperty(name));
	}

	public static void readConstantPropertiesFromFile() {
		Properties defaultsFromFile = new Properties();
		FileInputStream in = null;
		try {
			in = new FileInputStream(CONSTANTS_FILE_NAME);			
			defaultsFromFile.load(in);
		} catch (IOException e) {
			System.out.println("Error: Unable to load properties file " + CONSTANTS_FILE_NAME);
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();	
				}
			} catch (IOException e) {
				System.out.println("Error: Unable to close properties file " + CONSTANTS_FILE_NAME);
				e.printStackTrace();
			}			
		}
		
		constants.putAll(defaults);
		constants.putAll(defaultsFromFile);
	}
}
