package ca.team2994.frc.robot;
 
import mockit.Deencapsulation;
import mockit.Delegate;
import mockit.Mocked;
import mockit.NonStrictExpectations;

import org.junit.Test;

import ca.team2994.frc.autonomous.AutoMode;
import ca.team2994.frc.autonomous.modes.TestAutoMode;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
 
public class AutonomousTest {
 
	double angle = 0;
	double rightEncoder = 0;
	double leftEncoder = 0;
	int counter = 0;
 
	
	@Mocked SimGyro simGyro;
	@Mocked Encoder rightDriveEncoder;
	@Mocked Encoder leftDriveEncoder;
	@Mocked ERobotDrive robotDrive;
	@Mocked Talon talon;
 
	@SuppressWarnings("rawtypes")
	@Test
	public void mainTest() {		
		simGyro = new SimGyro(0);
		rightDriveEncoder = new Encoder(0,1);
		leftDriveEncoder = new Encoder(2,3);
		robotDrive = new ERobotDrive(new Motor(0,0), new Motor(1,0), new Motor(2,0), new Motor(3,0));
		
		SimPID gyroPID;
		SimPID encoderPID;
		
		gyroPID = new SimPID(2.16, 0.0, 0.1, 0.1);
		encoderPID = new SimPID(2.16, 0.0, 0.0, 0.1);
		
		new NonStrictExpectations() {{
			simGyro.getAngle(); result = getAngle();
			rightDriveEncoder.getDistance(); 
			result = new Delegate<Integer>() {
				@SuppressWarnings("unused")
				double getDistance() {
					return getRightEncoder();
				}
			};
			leftDriveEncoder.getDistance();
			result = new Delegate<Integer>() {
				@SuppressWarnings("unused")
				double getDistance() {
					return getLeftEncoder();
				}
			};
			robotDrive.setLeftRightMotorOutputs(anyDouble, anyDouble); 
			result = new Delegate() {
				@SuppressWarnings("unused")
				void setLeftRightMotorOutputs(double leftOutput, double rightOutput) {
					System.out.println("C:"+counter+",A:"+angle+",LE"+leftEncoder+",LO:"+leftOutput+",RE:"+rightEncoder+",RO:"+rightOutput);	
				}
			};
		}};
		
		Deencapsulation.setField(Subsystems.class, "gyroSensor", simGyro);
		Deencapsulation.setField(Subsystems.class, "rightDriveEncoder", rightDriveEncoder);
		Deencapsulation.setField(Subsystems.class, "leftDriveEncoder", leftDriveEncoder);
		Deencapsulation.setField(Subsystems.class, "robotDrive", robotDrive);
		Deencapsulation.setField(Subsystems.class, "gyroPID", gyroPID);
		Deencapsulation.setField(Subsystems.class, "encoderPID", encoderPID);
 
		AutoMode currentAutoMode = new TestAutoMode();
		currentAutoMode.initialize();
		
    	while (counter < 60) {
    		currentAutoMode.tick();
    		
        	angle++;
        	rightEncoder += 0.1;
        	leftEncoder += 0.1;
        	counter++;
    	}
	}
	
	public double getAngle() {
		return angle;
	}
	
	public double getRightEncoder() {
		return rightEncoder;
	}
	
	public double getLeftEncoder() {
		return leftEncoder;
	}
}