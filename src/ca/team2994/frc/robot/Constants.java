package ca.team2994.frc.robot;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Constants {
	private static Properties defaults = new Properties();
	private static Properties constants = new Properties();
	
	/* Property names */
	public static final String ROBOT_TYPE = "ROBOT_TYPE";
	public static final String LEFT_FRONT_DRIVE_PWM = "LEFT_FRONT_DRIVE_PWM";
	
	static {
		defaults.put(ROBOT_TYPE, 0);
		defaults.put(LEFT_FRONT_DRIVE_PWM, 0);
	}
	
	public static String getConstant(String name) {
		return constants.getProperty(name);
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
