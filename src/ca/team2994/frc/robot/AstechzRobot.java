package ca.team2994.frc.robot;

import ca.team2994.frc.autonomous.AutoMode;
import ca.team2994.frc.autonomous.AutoModeSelector;
import ca.team2994.frc.autonomous.CalibrationManager;
import ca.team2994.frc.autonomous.modes.DriveToAutoZoneOverPlatform;
import ca.team2994.frc.utils.Constants;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class AstechzRobot extends IterativeRobot {
	public CalibrationManager calibration;
	public AutoMode currentAutoMode;
	public SmartDash smartdash;
	private AutoModeSelector selector;
	private InputControl inputControl;
	
	private int forkliftCounter = 0;
	
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
    	Subsystems.forkliftEncoder.reset();
    	
    	smartdash = new SmartDash();
    	
    	selector = new AutoModeSelector();
    	
    	// On practice robot: 0.008038585209003215, -0.0078003120124804995
    	Subsystems.readEncoderValues(0.008038585209003215,-0.0078003120124804995);
    	
    	inputControl = new InputControl();
    	inputControl.init();
    }
    
    /**
     * This function is run once each time the robot enters autonomous mode
     */
    @Override
	public void autonomousInit() {
    	// Select an autonomous mode! :) Uses DIO array from Subsystems. See 
    	// initialize for how it's initialized. See docs for selectMode to see
    	// how they're used.
    	//TODO: Make this = selector.selectMode(Subsystems.inputs) when we're sure that it works
    	// Currently we just initialize it to TestAutoMode.
//    	currentAutoMode = new DriveToAutoZoneOverPlatform(); //new TestAutoMode(); TODO FIX DIPSWITCH ISSUE
//    	currentAutoMode.initialize();
    	Subsystems.leftDriveEncoder.reset();
    	Subsystems.rightDriveEncoder.reset();    	
    	forkliftCounter = 0;
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
	public void autonomousPeriodic() {
//    	currentAutoMode.tick();
    	
    	if(forkliftCounter < 50) {
    		Subsystems.forklift.moveUp();
    		forkliftCounter++;
    	}
    	
    	if(Subsystems.leftDriveEncoder.get() < 1550) {	// 1500 = 9 and a half feet
    		Subsystems.robotDrive.drive(0.2, 0.0);
    	} else {
    		Subsystems.robotDrive.drive(0.0, 0.0);
    	}
    	
    	Subsystems.forklift.manualLoop();
    }
    
    /**
     * This function is called once each time the robot enters tele-operated mode
     */
    @Override
	public void teleopInit() {
    	Subsystems.forklift.syncPositionWithEncoder();
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
	public void teleopPeriodic() {
    	inputControl.update();
    	
		smartdash.compDash();
		//For testing in teleop or for competition 
//		smartdash.showMotors();
    }
    
    @Override
	public void testInit() {
    	calibration = new CalibrationManager();
    	calibration.calibrateInit();   
    	Subsystems.forkliftEncoder.reset();
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
