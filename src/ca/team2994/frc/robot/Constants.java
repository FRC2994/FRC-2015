package ca.team2994.frc.robot;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.lang.Integer;
import java.lang.Double;

public class Constants {
	private static Properties defaults = new Properties();
	private static Properties constants = new Properties();
	
	/* Property names */
/////////////////////////////
	public static final String ROBOT_TYPE = "ROBOT_TYPE";
//	PWMs
	public static final String LEFT_FRONT_DRIVE_PWM   ="LEFT_FRONT_DRIVE_PWM";
	public static final String LEFT_REAR_DRIVE_PWM    ="LEFT_REAR_DRIVE_PWM";    // cable 44 / 26
	public static final String RIGHT_FRONT_DRIVE_PWM  ="RIGHT_FRONT_DRIVE_PWM";  // cable 43 / 22
	public static final String RIGHT_REAR_DRIVE_PWM   ="RIGHT_REAR_DRIVE_PWM";   // cable 41 / 23
	public static final String CENTER_LEFT_DRIVE_PWM  ="CENTER_LEFT_DRIVE_PWM";  // UNKNOWN CABLE
	public static final String CENTER_RIGHT_DRIVE_PWM ="CENTER_RIGHT_DRIVE_PWM"; // UNKNOWN CABLE
	public static final String INTAKE_MOTOR_PWM       ="INTAKE_MOTOR_PWM";       // cable 34 / 21
	public static final String RIGHT_WINCH_MOTOR_PWM  ="RIGHT_WINCH_MOTOR_PWM";  // cable 40 / 27
	public static final String LEFT_WINCH_MOTOR_PWM   ="LEFT_WINCH_MOTOR_PWM";
                        
	// Solenoids        
	public static final String SHIFTER_A = "SHIFTER_A"; 				// cable 0
	public static final String SHIFTER_B = "SHIFTER_B"; 				// cable 00
	public static final String ARM_A = "ARM_A";     				// cable 22|Do we need this?
	public static final String ARM_B = "ARM_B";     				// cable 23|do we need this?	
	public static final String EJECT_A = "EJECT_A";   				// cable 7
	public static final String EJECT_B = "EJECT_B";   				// cable 12
                       
	// Digital I/O      
	public static final String LEFT_ENCODER_A = "LEFT_ENCODER_A";         // cable 11
	public static final String LEFT_ENCODER_B = "LEFT_ENCODER_B";         // cable 12
	public static final String RIGHT_ENCODER_A = "RIGHT_ENCODER_A";        // cable 39
	public static final String RIGHT_ENCODER_B = "RIGHT_ENCODER_B";        // cable 40
	public static final String COMPRESSOR_PRESSURE_SW = "COMPRESSOR_PRESSURE_SW"; //
                        
	// Relays           
	public static final String COMPRESSOR_SPIKE = "COMPRESSOR_SPIKE";		// cable 7

	// USB ports
	public static final String RIGHT_DRIVE_STICK = "RIGHT_DRIVE_STICK";
	public static final String LEFT_DRIVE_STICK  = "LEFT_DRIVE_STICK";
	public static final String GAMEPAD_PORT = "GAMEPAD_PORT";

	// Left Joystick
	public static final String BUTTON_SHIFT = "BUTTON_SHIFT";

	// Gamepad
	public static final String BUTTON_SHOOT = "BUTTON_SHOOT";
	public static final String BUTTON_INTAKE_COLLECT = "BUTTON_INTAKE_COLLECT";
	public static final String BUTTON_INTAKE_EJECT = "BUTTON_INTAKE_EJECT";

	// Intake speeds
	public static final String INTAKE_COLLECT = "INTAKE_COLLECT";
	public static final String INTAKE_EJECT = "INTAKE_EJECT";

	
	static {
		defaults.put(ROBOT_TYPE, 0);
		defaults.put(LEFT_FRONT_DRIVE_PWM,  4);
		defaults.put(LEFT_REAR_DRIVE_PWM,   3);   
		defaults.put(RIGHT_FRONT_DRIVE_PWM, 2); 
		defaults.put(RIGHT_REAR_DRIVE_PWM,  1);  
		defaults.put(CENTER_LEFT_DRIVE_PWM, 7); 
		defaults.put(CENTER_RIGHT_DRIVE_PWM,8);
		defaults.put(INTAKE_MOTOR_PWM,      5);      
		defaults.put(RIGHT_WINCH_MOTOR_PWM, 6); 
		defaults.put(LEFT_WINCH_MOTOR_PWM,  9);
		defaults.put(SHIFTER_A,           1); 				
		defaults.put(SHIFTER_B,           2); 				
		defaults.put(ARM_A,               3);     				
		defaults.put(ARM_B,               4);     				
		defaults.put(EJECT_A,             5);   				
		defaults.put(EJECT_B,             6);   					        
		defaults.put(LEFT_ENCODER_A,        2);         
		defaults.put(LEFT_ENCODER_B,        3);          
		defaults.put(RIGHT_ENCODER_A,       4);        
		defaults.put(RIGHT_ENCODER_B,       5);        
		defaults.put(COMPRESSOR_PRESSURE_SW,  8);  
		defaults.put(COMPRESSOR_SPIKE,        2);
		defaults.put(RIGHT_DRIVE_STICK,     1);      
		defaults.put(LEFT_DRIVE_STICK,      2);       
		defaults.put(GAMEPAD_PORT,          3);           
		defaults.put(BUTTON_SHIFT,          7);       
		defaults.put(INTAKE_COLLECT,  -0.95);
		defaults.put(INTAKE_EJECT,     0.95);
	}
	/**Returns String of constant
	 * 
	 * @param name
	 * @return
	 */
	public static String getConstant(String name) {
		return constants.getProperty(name);
	}
	/**Returns constant string as int
	 * 
	 * @param name
	 * @return
	 */
	public static int getConstantasInt(String name) {
		return Integer.parseInt(constants.getProperty(name));
	}
	/**Returns constant string as double
	 * 
	 * @param name
	 * @return
	 */
	public static double getConstantDouble(String name) {
		return Double.parseDouble(constants.getProperty(name));
	}

	public static void readConstantPropertiesFromFile(String filename) {
		Properties defaultsFromFile = new Properties();
		FileInputStream in = null;
		try {
			in = new FileInputStream(filename);
			defaultsFromFile.load(in);
		} catch (IOException e) {
			System.out.println("Error: Unable to load properties file " + filename);
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				System.out.println("Error: Unable to close properties file " + filename);
				e.printStackTrace();
			}			
		}
		
		constants.putAll(defaults);
		constants.putAll(defaultsFromFile);
	}
}
