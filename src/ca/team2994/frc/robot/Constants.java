package ca.team2994.frc.robot;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Constants
{
	private static final String CONSTANTS_FILE_NAME = "/home/lvuser/constants.properties";
	
	private static Properties defaults = new Properties();
	private static Properties constants = new Properties();
	
	/* Property names */
	// Motor Types
	public static final String MOTOR_TYPE_DRIVE =		"MOTOR_TYPE_DRIVE";
	public static final String MOTOR_TYPE_ARM =			"MOTOR_TYPE_ARM";
	public static final String MOTOR_TYPE_FORKLIFT =	"MOTOR_TYPE_FORKLIFT";
	public static final String MOTOR_TYPE_CONVEYOR =	"MOTOR_TYPE_CONVEYOR";
	
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
	
	public static final String DIO_TOTE_DETECT_SENSOR =	"DIO_TOTE_DETECT_SENSOR";
	public static final String DIO_FORKLIFT_ENCODER_A =	"DIO_FORKLIFT_ENCODER_A";
	public static final String DIO_FORKLIFT_ENCODER_B =	"DIO_FORKLIFT_ENCODER_B";
	
	// Analog IO
	public static final String AIO_GYRO_SENSOR =		"AIO_GYRO_SENSOR";
	
	// USB
	public static final String USB_DRIVE_STICK =		"USB_RIGHT_STICK";
	public static final String USB_CONTROL_GAMEPAD =	"USB_CONTROL_GAMEPAD";
	
	// Solenoids
	public static final String PCM_SHIFTER_A = 			"PCM_SHIFTER_A";
	public static final String PCM_SHIFTER_B = 			"PCM_SHIFTER_B";
	
	// Other
	public static final String BLING_ENABLED =			"BLING_ENABLED";
	
	static
	{
		// PWM
		defaults.put(MOTOR_TYPE_DRIVE, "0");
		defaults.put(MOTOR_TYPE_ARM, "0");
		defaults.put(MOTOR_TYPE_FORKLIFT, "0");
		defaults.put(MOTOR_TYPE_CONVEYOR, "0");

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
		
		defaults.put(DIO_TOTE_DETECT_SENSOR, "2");
		defaults.put(DIO_FORKLIFT_ENCODER_A, "3");
		defaults.put(DIO_FORKLIFT_ENCODER_B, "4");
		
		// Analog IO
		defaults.put(AIO_GYRO_SENSOR, "0");
		
		// USB
		defaults.put(USB_DRIVE_STICK, "0");
		defaults.put(USB_CONTROL_GAMEPAD, "1");

		// Solenoid
		defaults.put(PCM_SHIFTER_A, "0");
		defaults.put(PCM_SHIFTER_B, "1");
		
		// Other
		defaults.put(BLING_ENABLED, "0");
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
