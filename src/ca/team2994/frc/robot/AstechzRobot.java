package ca.team2994.frc.robot;

import ca.team2994.frc.autonomous.AutoMode;
import ca.team2994.frc.autonomous.AutoModeSelector;
import ca.team2994.frc.autonomous.CalibrationManager;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class AstechzRobot extends IterativeRobot {
	public CalibrationManager calibration;
	public AutoMode currentAutoMode;
	public SmartDash smartdash;
	private AutoModeSelector selector;
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
	public void robotInit() {
    	Constants.readConstantPropertiesFromFile();
    	Subsystems.initialize();
    	
    	Subsystems.leftDriveEncoder.reset();
    	Subsystems.rightDriveEncoder.reset();
    	
    	Subsystems.driveJoystick.enableButton(Constants.getConstantAsInt(Constants.JOYSTICK_GEAR_HIGH));
    	Subsystems.driveJoystick.enableButton(Constants.getConstantAsInt(Constants.JOYSTICK_GEAR_LOW));
    	Subsystems.driveJoystick.enableButton(8);
    	Subsystems.driveJoystick.enableButton(9);
    	Subsystems.driveJoystick.enableButton(10);
    	Subsystems.driveJoystick.enableButton(11);
    	smartdash = new SmartDash();
    	
    	selector = new AutoModeSelector();
    	
    	Subsystems.readEncoderValues();
    }
    
    /**
     * This function is run once each time the robot enters autonomous mode
     */
    @Override
	public void autonomousInit() {
    	// Select an autonomous mode! :) Uses DIO array from Subsystems. See 
    	// initialize for how it's initialized. See docs for selectMode to see
    	// how they're used.
    	currentAutoMode = selector.selectMode(Subsystems.inputs);
    	currentAutoMode.initialize();
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
	public void autonomousPeriodic() {
    	currentAutoMode.tick();
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
    	Subsystems.driveJoystick.update();
    	Subsystems.controlGamepad.update();
    	Subsystems.robotDrive.arcadeDrive(Subsystems.driveJoystick, false);

    	Subsystems.gearShifter.gearShift();
		smartdash.compDash();
    }
    
    @Override
	public void testInit() {
    	calibration = new CalibrationManager();
    	calibration.calibrateInit();    	
    }
    
    /**
     * This function is called periodically during test mode
     */
    @Override
	public void testPeriodic() {
    	calibration.calibrateTick();
    	LiveWindow.run();
    }
    
    /**
     * This function is called by default when the robot is disabled
     */
    @Override
    public void disabledInit() {
	}
}
