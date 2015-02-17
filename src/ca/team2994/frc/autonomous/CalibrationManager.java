package ca.team2994.frc.autonomous;

import java.io.File;

import ca.team2994.frc.robot.ButtonEntry;
import ca.team2994.frc.robot.Constants;
import ca.team2994.frc.robot.Subsystems;

public class CalibrationManager {
	
	private boolean calibrationDone = false;
	
	public void calibrateInit() {
    	Subsystems.leftDriveEncoder.reset();
    	Subsystems.rightDriveEncoder.reset();
    	
    	Subsystems.driveJoystick.enableButton(Constants.getConstantAsInt(Constants.CALIBRATION_BUTTON));
	}
	
	public void calibrateTick() {
		if(Subsystems.driveJoystick.getEvent(Constants.getConstantAsInt(Constants.JOYSTICK_CALIBRATE))
				!= ButtonEntry.EVENT_CLOSED) {
    		Subsystems.driveJoystick.update();
    		Subsystems.robotDrive.arcadeDrive(Subsystems.driveJoystick, false);
    	}
		else if(!calibrationDone) {
			Subsystems.robotDrive.drive(0, 0);
	    	
	    	int encoderAValue = Subsystems.leftDriveEncoder.get();
	    	int encoderBValue = Subsystems.rightDriveEncoder.get();
	    	
	    	double encoderAConstant = 0;
	    	double encoderBConstant = 0;
	    	
	    	if(encoderAValue != 0) {
	    		encoderAConstant = 5.0 / encoderAValue;
	    	}
	    	
	    	if(encoderBValue != 0) {
	    		encoderBConstant = 5.0 / encoderBValue;
	    	}
	    	
	    	String value = "//Encoder A (Left), Distance Travelled: 5ft, Number of encoder ticks: " + encoderAValue
	    			+ ", Calibration constant: " + encoderAConstant;
	    	System.out.println(value);
			AutoHelper.writeLineToFile(value, new File(Constants.getConstant(Constants.CALIBRATION_FILE_LOC)));
	    	
			value = "//Encoder B (Right), Distance Travelled: 5ft, Number of encoder ticks: " + encoderBValue
	    			+ ", Calibration constant: " + encoderBConstant;
			System.out.println(value);
			AutoHelper.writeLineToFile(value, new File(Constants.getConstant(Constants.CALIBRATION_FILE_LOC)));
	    	
			AutoHelper.writeLineToFile(encoderAConstant + ", " + encoderBConstant, new File(Constants.getConstant(Constants.CALIBRATION_FILE_LOC)));
			
			calibrationDone = true;
		}
	}
}
