package ca.team2994.frc.robot;

import ca.team2994.frc.autonomous.AutoMode;
import ca.team2994.frc.autonomous.AutoModeSelector;
import ca.team2994.frc.autonomous.CalibrationManager;
import ca.team2994.frc.autonomous.modes.TestAutoMode;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class AstechzRobot extends IterativeRobot {
	public CalibrationManager calibration;
	public AutoMode currentAutoMode;
	public SmartDash smartdash;
	private AutoModeSelector selector;
	private InputControl inputControl;
	
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
    	
    	smartdash = new SmartDash();
    	
    	selector = new AutoModeSelector();
    	
    	Subsystems.readEncoderValues();
    	
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
    	currentAutoMode = new TestAutoMode();
    	currentAutoMode.initialize();
    	System.out.println(selector.encodeSwitches(Subsystems.inputs));
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
    	System.out.println(selector.encodeSwitches(Subsystems.inputs));
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
	public void teleopPeriodic() {
    	inputControl.update();
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
