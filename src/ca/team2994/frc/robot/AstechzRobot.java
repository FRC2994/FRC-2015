package ca.team2994.frc.robot;

import com.google.common.base.Strings;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
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
	SimPID leftPID;
	SimPID rightPID;
	boolean blingReady = false;
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
	public void robotInit() {
    	Constants.readConstantPropertiesFromFile();
    	Subsystems.initialize();
    	leftPID = new SimPID(0.006, 0.0001, 0.0001); 
    	rightPID = new SimPID(0.006, 0.0001, 0.0001);
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
    	
    	autoLoopCounter++;
    }
    
    /**
     * This function is called once each time the robot enters tele-operated mode
     */
    @Override
	public void teleopInit(){
    	Subsystems.blingPort.writeString("X");
    	
    	Subsystems.leftDriveEncoder.reset();
    	Subsystems.rightDriveEncoder.reset();
    	leftPID.setDesiredValue(Constants.getConstantAsDouble(Constants.PID_TARGET_1));
		rightPID.setDesiredValue(Constants.getConstantAsDouble(Constants.PID_TARGET_1));
		Subsystems.rightDriveJoystick.enableButton(1);
		Subsystems.rightDriveJoystick.enableButton(2);
		Subsystems.rightDriveJoystick.enableButton(3);
		Subsystems.rightDriveJoystick.enableButton(4);
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
	public void teleopPeriodic() {
    	Subsystems.robotDrive.arcadeDrive(Subsystems.rightDriveJoystick);
    	System.out.println("Temp=" + Subsystems.powerPanel.getTemperature());
    	System.out.println("Tote_Sensor=" + Subsystems.totesensor.get());
    	if (blingReady == false) {
    		String readyString = Subsystems.blingPort.readString();
    		if (!Strings.isNullOrEmpty(readyString) && (readyString.equalsIgnoreCase("S"))) {
    			blingReady = true;
    			Subsystems.blingPort.writeString("E1Z");
    		}
    	}
    	System.out.println("BlingReady=" + blingReady);
    }
    
    /**
     * This function is called periodically during test mode
     */
    @Override
	public void testPeriodic() {
    	LiveWindow.run();
    }
    
    private void testPID() {
    	if(autoLoopCounter == 0) {
    		leftPID.setDesiredValue(Constants.getConstantAsDouble(Constants.PID_TARGET_1));
    		rightPID.setDesiredValue(Constants.getConstantAsDouble(Constants.PID_TARGET_1));
    		Subsystems.leftDriveEncoder.reset();
        	Subsystems.rightDriveEncoder.reset();
    	}
    	
    	double lDriveVal = leftPID.calcPID(Subsystems.leftDriveEncoder.get());
    	double lLimitVal = SimLib.limitValue(lDriveVal, 0.25);
    	
    	double rDriveVal = rightPID.calcPID(Subsystems.rightDriveEncoder.get());
    	double rLimitVal = SimLib.limitValue(rDriveVal, 0.25);
    	
    	Subsystems.leftDrive.set(lLimitVal);
    	Subsystems.rightDrive.set(rLimitVal);
    	autoLoopCounter++;
    }
    
    private void testPlyboyPIDLevels()
    {
    	Subsystems.rightDriveJoystick.update();
    	
    	if(Subsystems.rightDriveJoystick.getEvent(1) == ButtonEntry.EVENT_CLOSED)
    	{
    		leftPID.setDesiredValue(Constants.getConstantAsDouble(Constants.PID_TARGET_1));
    		rightPID.setDesiredValue(Constants.getConstantAsDouble(Constants.PID_TARGET_1));
    		Subsystems.blingPort.writeString("I");
    		
    		Timer.delay(0.05);
    		
    		String readString = Subsystems.blingPort.readString();
    		
    		while(readString.startsWith("R"))
    		{
    			if(Strings.isNullOrEmpty(readString))
    			{
    				continue;
    			}
    			else
    			{
    				System.out.println(readString);
    			}
    			
    			readString = Subsystems.blingPort.readString();
    		}
    		
    		Subsystems.blingPort.writeString("F6C255E6Z");						// TODO: Change colour to red
    	}
    	
    	if(Subsystems.rightDriveJoystick.getEvent(2) == ButtonEntry.EVENT_CLOSED)
    	{
    		leftPID.setDesiredValue(Constants.getConstantAsDouble(Constants.PID_TARGET_2));
    		rightPID.setDesiredValue(Constants.getConstantAsDouble(Constants.PID_TARGET_2));
    		Subsystems.blingPort.writeString("I");
    		
    		Timer.delay(0.05);
    		
    		String readString = Subsystems.blingPort.readString();
    		
    		while(readString.startsWith("R"))
    		{
    			if(Strings.isNullOrEmpty(readString))
    			{
    				continue;
    			}
    			else
    			{
    				System.out.println(readString);
    			}
    			
    			readString = Subsystems.blingPort.readString();
    		}
    		
    		Subsystems.blingPort.writeString("F6C255E6Z");						// TODO: Change colour to green
    	}
    	
    	if(Subsystems.rightDriveJoystick.getEvent(3) == ButtonEntry.EVENT_CLOSED)
    	{
    		leftPID.setDesiredValue(Constants.getConstantAsDouble(Constants.PID_TARGET_3));
    		rightPID.setDesiredValue(Constants.getConstantAsDouble(Constants.PID_TARGET_3));
    		Subsystems.blingPort.writeString("I");
    		
    		Timer.delay(0.05);
    		
    		String readString = Subsystems.blingPort.readString();
    		
    		while(readString.startsWith("R"))
    		{
    			if(Strings.isNullOrEmpty(readString))
    			{
    				continue;
    			}
    			else
    			{
    				System.out.println(readString);
    			}
    			
    			readString = Subsystems.blingPort.readString();
    		}
    		
    		Subsystems.blingPort.writeString("F6C255E6Z");			// TODO: Change colour to blue
    	}
    	
    	if(Subsystems.rightDriveJoystick.getEvent(4) == ButtonEntry.EVENT_CLOSED)
    	{
    		leftPID.setDesiredValue(Constants.getConstantAsDouble(Constants.PID_TARGET_4));
    		rightPID.setDesiredValue(Constants.getConstantAsDouble(Constants.PID_TARGET_4));
    		Subsystems.blingPort.writeString("I");
    		
    		Timer.delay(0.05);
    		
    		String readString = Subsystems.blingPort.readString();
    		
    		while(readString.startsWith("R"))
    		{
    			if(Strings.isNullOrEmpty(readString))
    			{
    				continue;
    			}
    			else
    			{
    				System.out.println(readString);
    			}
    			
    			readString = Subsystems.blingPort.readString();
    		}
    		
    		Subsystems.blingPort.writeString("F6C255E6Z");			// TODO: Change colour to white
    	}
    	
    	double lDriveVal = leftPID.calcPID(-Subsystems.leftDriveEncoder.get());
    	double lLimitVal = SimLib.limitValue(lDriveVal, 0.25);
    	
    	double rDriveVal = rightPID.calcPID(Subsystems.rightDriveEncoder.get());
    	double rLimitVal = SimLib.limitValue(rDriveVal, 0.25);
    	
   		Subsystems.leftDrive.set(lLimitVal);
    	Subsystems.rightDrive.set(rLimitVal);
    }
}
