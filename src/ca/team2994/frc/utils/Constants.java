package ca.team2994.frc.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Constants
{
	private static final String CONSTANTS_FILE_NAME = 			"/home/lvuser/constants.properties";
	
	private static Properties defaults = new Properties();
	private static Properties constants = new Properties();
	
	/* Property names */
	// Motor Types
	public static final String MOTOR_TYPE_DRIVE =				"MOTOR_TYPE_DRIVE";
	public static final String MOTOR_TYPE_ARM =					"MOTOR_TYPE_ARM";
	public static final String MOTOR_TYPE_FORKLIFT =			"MOTOR_TYPE_FORKLIFT";
	public static final String MOTOR_TYPE_CONVEYOR =			"MOTOR_TYPE_CONVEYOR";
	
	// PWM
	public static final String PWM_RIGHT_FRONT_DRIVE =			"PWM_RIGHT_FRONT_DRIVE";
	public static final String PWM_RIGHT_REAR_DRIVE =			"PWM_RIGHT_REAR_DRIVE";
	public static final String PWM_LEFT_FRONT_DRIVE = 			"PWM_LEFT_FRONT_DRIVE";
	public static final String PWM_LEFT_REAR_DRIVE =			"PWM_LEFT_REAR_DRIVE";
	
	public static final String PWM_RIGHT_ARM =					"PWM_RIGHT_ARM";
	public static final String PWM_LEFT_ARM =					"PWM_LEFT_ARM";
	public static final String PWM_FORKLIFT =					"PWM_FORKLIFT";
	public static final String PWM_CONVEYOR =					"PWM_CONVEYOR";
	
	// DIO
	public static final String DIO_RIGHT_ENCODER_A =			"DIO_RIGHT_ENCODER_A";
	public static final String DIO_RIGHT_ENCODER_B =			"DIO_RIGHT_ENCODER_B";
	public static final String DIO_LEFT_ENCODER_A =				"DIO_LEFT_ENCODER_A";
	public static final String DIO_LEFT_ENCODER_B =				"DIO_LEFT_ENCODER_B";
	
	public static final String DIO_TOTE_DETECT_SENSOR =			"DIO_TOTE_DETECT_SENSOR";
	public static final String DIO_FORKLIFT_ENCODER_A =			"DIO_FORKLIFT_ENCODER_A";
	public static final String DIO_FORKLIFT_ENCODER_B =			"DIO_FORKLIFT_ENCODER_B";
	
	// Analog IO
	public static final String AIO_GYRO_SENSOR =				"AIO_GYRO_SENSOR";

	// USB
	public static final String USB_DRIVE_STICK =				"USB_RIGHT_STICK";
	public static final String USB_CONTROL_GAMEPAD =			"USB_CONTROL_GAMEPAD";
	
	// Solenoids
	public static final String PCM_SHIFTER_A = 					"PCM_SHIFTER_A";
	public static final String PCM_SHIFTER_B = 					"PCM_SHIFTER_B";
	
	// Other
	public static final String BLING_ENABLED =					"BLING_ENABLED";
	
	//Robot Arm
	
	public static final String ARM_FORWARD_SPEED =				"ARM_FORWARD_SPEED";
	public static final String ARM_REVERSE_SPEED =				"ARM_REVERSE_SPEED";
	public static final String ARM_PICKUP_SPEED =				"ARM_PICKUP_SPEED";
	public static final String ARM_DROPOFF_SPEED =				"ARM_DROPOFF_SPEED";
	public static final String ARM_LOAD_SPEED =					"ARM_LOAD_SPEED";
	public static final String ARM_UNLOAD_SPEED =				"ARM_UNLOAD_SPEED";
	public static final String ARM_DROPOFF_TIME =				"ARM_DROPOFF_TIME";
	public static final String ARM_UNLOAD_TIME =				"ARM_UNLOAD_TIME";
	
	//PID
	public static final String FORKLIFT_PID_P =					"FORKLIFT_PID_P";
	public static final String FORKLIFT_PID_I =					"FORKLIFT_PID_I";
	public static final String FORKLIFT_PID_D =					"FORKLIFT_PID_D";
	public static final String FORKLIFT_PID_E =					"FORKLIFT_PID_E";
	
	public static final String GYRO_PID_P = 					"GYRO_PID_P";
	public static final String GYRO_PID_I = 					"GYRO_PID_I";
	public static final String GYRO_PID_D = 					"GYRO_PID_D";
	public static final String GYRO_PID_E = 					"GYRO_PID_E";
	public static final String GYRO_PID_MAX = 					"GYRO_PID_MAX";
	
	public static final String ENCODER_PID_P = 					"ENCODER_PID_P";
	public static final String ENCODER_PID_I = 					"ENCODER_PID_I";
	public static final String ENCODER_PID_D = 					"ENCODER_PID_D";
	public static final String ENCODER_PID_E = 					"ENCODER_PID_E";
	public static final String ENCODER_PID_MAX = 				"ENCODER_PID_MAX";
	

	public static final String CALIBRATION_FILE_LOC =			"CALIBRATION_FILE_LOC";
	public static final String CALIBRATION_BUTTON = 			"CALIBRATION_BUTTON";
	
	//Compressor channel
	public static final String COMPRESSOR_CHANNEL =				"COMPRESSOR_CHANNEL";
	

	//Double Solenoid Channels
	public static final String SOLENOID_SHIFTER_CHANNEL1 =		"SOLENOID_SHIFTER_CHANNEL1";
	public static final String SOLENOID_SHIFTER_CHANNEL2 =		"SOLENOID_SHIFTER_CHANNEL2";

	//Conveyor
	public static final String CONVEYOR_SPEED =					"CONVEYOR_SPEED";
	public static final String CONVEYOR_TIME =					"CONVEYOR_TIME";
	
	//Forklift
	public static final String[] ENCODER_LEVELS  =               {"LEVELS_1", "LEVELS_2", "LEVELS_3", "LEVEL_4"};
	public static final String FORKLIFT_UP_SPEED =				 "FORKLIFT_UP_SPEED";
	public static final String FORKLIFT_DOWN_SPEED = 			 "FORKLIFT_DOWN_SPEED";
	
	//Gamepad Buttons
	public static final String GAMEPAD_TOGGLE_MODE =			"GAMEPAD_TOGGLE_MODE";
	public static final String GAMEPAD_LOAD_TOTE =				"GAMEPAD_LOAD_TOTE";
	public static final String GAMEPAD_UNLOAD_TOTE =			"GAMEPAD_UNLOAD_TOTE";
	
	//Joystick Buttons
	public static final String JOYSTICK_HIGH_GEAR  =			"JOYSTICK_HIGH_GEAR";
	public static final String JOYSTICK_CALIBRATE  =			"JOYSTICK_CALIBRATE";
	
	// Digital Inputs
	public static final String NUM_AUTO_SELECT =				"NUM_INPUTS";
	public static final String FIRST_DIGITAL_SELECT = 			"FIRST_DIGITAL_SELECT";
	public static final String SECOND_DIGITAL_SELECT = 			"SECOND_DIGITAL_SELECT";

	static {
		// PWM
		defaults.put(MOTOR_TYPE_DRIVE, "0");
		defaults.put(MOTOR_TYPE_ARM, "0");
		defaults.put(MOTOR_TYPE_FORKLIFT, "2");
		defaults.put(MOTOR_TYPE_CONVEYOR, "0");

		// PWM
		defaults.put(PWM_RIGHT_FRONT_DRIVE, "3");
		defaults.put(PWM_RIGHT_REAR_DRIVE, "2");
		defaults.put(PWM_LEFT_FRONT_DRIVE, "9");
		defaults.put(PWM_LEFT_REAR_DRIVE, "8");
		
		defaults.put(PWM_RIGHT_ARM, "4");
		defaults.put(PWM_LEFT_ARM, "5");
		defaults.put(PWM_FORKLIFT, "11"); // CAN
		defaults.put(PWM_CONVEYOR, "7");
		
		// DIO
		defaults.put(DIO_RIGHT_ENCODER_A, "9");
		defaults.put(DIO_RIGHT_ENCODER_B, "8");
		defaults.put(DIO_LEFT_ENCODER_A, "7");
		defaults.put(DIO_LEFT_ENCODER_B, "6");
		
		defaults.put(DIO_TOTE_DETECT_SENSOR, "2");
		defaults.put(DIO_FORKLIFT_ENCODER_A, "0");
		defaults.put(DIO_FORKLIFT_ENCODER_B, "1");
		
		// Analog IO
		defaults.put(AIO_GYRO_SENSOR, "0");

		// USB
		defaults.put(USB_DRIVE_STICK, "0");
		defaults.put(USB_CONTROL_GAMEPAD, "1");

		// Solenoid
		defaults.put(PCM_SHIFTER_A, "0");
		defaults.put(PCM_SHIFTER_B, "1");
		
		//Robot Arm
		defaults.put(ARM_FORWARD_SPEED, "0.4");
		defaults.put(ARM_REVERSE_SPEED, "0.4");
		defaults.put(ARM_PICKUP_SPEED, "0.4");
		defaults.put(ARM_DROPOFF_SPEED, "0.4");
		defaults.put(ARM_LOAD_SPEED, "0.3");
		defaults.put(ARM_UNLOAD_SPEED, "0.3");
		defaults.put(ARM_DROPOFF_TIME, "0");
		defaults.put(ARM_UNLOAD_TIME, "0");
		
		// Other
		defaults.put(BLING_ENABLED, "0");
		
		//PID
		defaults.put(FORKLIFT_PID_P, "0.006");
		defaults.put(FORKLIFT_PID_I, "0.001");
		defaults.put(FORKLIFT_PID_D, "0.001");
		// The forklift PID works at encoder-value-scale so we don't need as much accuracy.
		defaults.put(FORKLIFT_PID_E, "10");
		
		defaults.put(GYRO_PID_P, "0.005");
		defaults.put(GYRO_PID_I, "0.0");
		defaults.put(GYRO_PID_D, "0.0");
		defaults.put(GYRO_PID_E, "1.0");
		defaults.put(GYRO_PID_MAX, "0.4");
		
		defaults.put(ENCODER_PID_P, "2.16");
		defaults.put(ENCODER_PID_I, "0.0");
		defaults.put(ENCODER_PID_D, "0.0");
		defaults.put(ENCODER_PID_E, "0.1");
		defaults.put(ENCODER_PID_MAX, "0.4");

		defaults.put(CALIBRATION_FILE_LOC, "/home/lvuser/calibration.txt");
		defaults.put(CALIBRATION_BUTTON, "2");
		
		//Compressor Channel
		defaults.put(COMPRESSOR_CHANNEL, "1");
		
		// Double Solenoid Channels
		defaults.put(SOLENOID_SHIFTER_CHANNEL1, "0");
		defaults.put(SOLENOID_SHIFTER_CHANNEL2, "1");
		
		//Conveyor
		defaults.put(CONVEYOR_SPEED, "0.4");
		defaults.put(CONVEYOR_TIME,"20");

		//Gamepad Buttons
		defaults.put(GAMEPAD_TOGGLE_MODE, "10"); // Start
		defaults.put(GAMEPAD_LOAD_TOTE, "2"); // A
		defaults.put(GAMEPAD_UNLOAD_TOTE, "3"); // B
		
		//Joystick Buttons
		defaults.put(JOYSTICK_HIGH_GEAR, "7");
		defaults.put(JOYSTICK_CALIBRATE, "2");
		
		// Digital Inputs
		// Allows for 8
		defaults.put(NUM_AUTO_SELECT, "3");
		defaults.put(FIRST_DIGITAL_SELECT, "3");
		defaults.put(SECOND_DIGITAL_SELECT, "5");
		
		defaults.put(ENCODER_LEVELS[0], "300.0");
		defaults.put(ENCODER_LEVELS[1], "600.0");
		defaults.put(ENCODER_LEVELS[2], "900.0");
		defaults.put(ENCODER_LEVELS[3], "1200.0");
		
		defaults.put(FORKLIFT_UP_SPEED, "-0.75");
		defaults.put(FORKLIFT_DOWN_SPEED, "0.50");

		constants.putAll(defaults);
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
			System.out.println("Warning: Unable to load properties file " + CONSTANTS_FILE_NAME);
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
		
		constants.putAll(defaultsFromFile);
	}
}
