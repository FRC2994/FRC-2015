package ca.team2994.frc.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class AstechzRobot extends IterativeRobot {
	int autoLoopCounter;
	SimPID encoderPID;
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
	public void robotInit() {
    	Constants.readConstantPropertiesFromFile();
    	Subsystems.initialize();
    	encoderPID = new SimPID(0.006, 0.000, 0.000);
    }
    
    /**
     * This function is run once each time the robot enters autonomous mode
     */
    @Override
	public void autonomousInit() {
    	autoLoopCounter = 0;
    	Subsystems.leftDriveEncoder.reset();
    	Subsystems.rightDriveEncoder.reset();
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
	public void autonomousPeriodic() {
    	if(autoLoopCounter < 100) { //Check if we've completed 100 loops (approximately 2 seconds)
			Subsystems.robotDrive.drive(-0.5, 0.0); 	// drive forwards half speed
			autoLoopCounter++;
		} else {
			Subsystems.robotDrive.drive(0.0, 0.0); 	// stop robot
		}
    }
    
    /**
     * This function is called once each time the robot enters tele-operated mode
     */
    @Override
	public void teleopInit(){
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
	public void teleopPeriodic() {
//    	TEST_EControlers.testEGamepad();
//    	TEST_EControlers.testEJoystick();
    	Subsystems.robotDrive.arcadeDrive(Subsystems.rightDriveJoystick);
    }
    
    /**
     * This function is called periodically during test mode
     */
    @Override
	public void testPeriodic() {
//    	TEST_EControlers.testEGamepad();
//    	TEST_EControlers.testEJoystick();
    	LiveWindow.run();
    }
    
    private void testPID() {
    	if(autoLoopCounter == 0) {
    		encoderPID.setDesiredValue(Constants.getConstantAsDouble(Constants.PID_TARGET));
    		Subsystems.leftDriveEncoder.reset();
        	Subsystems.rightDriveEncoder.reset();
    	}
    	
    	double driveVal = encoderPID.calcPID((-Subsystems.leftDriveEncoder.get() - Subsystems.rightDriveEncoder.get()) / 2.0);
    	double limitVal = SimLib.limitValue(driveVal, 0.25);
    	
    	Subsystems.leftDrive.set(limitVal + 0.038);
    	Subsystems.rightDrive.set(-limitVal);
    	autoLoopCounter++;
    	
    	System.out.println("driveVal: " + driveVal + " limitVal: " + limitVal + " leftEncoder: " + Subsystems.leftDriveEncoder.get() + " rightEncoder: " + Subsystems.rightDriveEncoder.get());
    }
}
