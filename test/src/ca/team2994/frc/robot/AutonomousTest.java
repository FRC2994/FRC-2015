package ca.team2994.frc.robot;
 
import mockit.Deencapsulation;
import mockit.Delegate;
import mockit.Mocked;
import mockit.NonStrictExpectations;
 
import org.junit.Test;
 
import ca.team2994.frc.auto.AutonControl;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
 
public class AutonomousTest {
 
	double angle = 0;
	int rightEncoder = 0;
	int leftEncoder = 0;
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
		
		new NonStrictExpectations() {{
			simGyro.getAngle(); result = getAngle();
			rightDriveEncoder.get(); 
			result = new Delegate<Integer>() {
				@SuppressWarnings("unused")
				int get() {
					return getRightEncoder();
				}
			};
			leftDriveEncoder.get();
			result = new Delegate<Integer>() {
				@SuppressWarnings("unused")
				int get() {
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
		        
    	AutonControl.getInstance().reset();
    	AutonControl.getInstance().initialize();
 
    	while (counter < 100) {
        	AutonControl.getInstance().updatePosition();
        	AutonControl.getInstance().runCycle();
//        	angle++;
        	rightEncoder += 100;
        	leftEncoder += 100;
        	counter++;
        	try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
	}
	
	public double getAngle() {
		return angle;
	}
	
	public int getRightEncoder() {
		return rightEncoder;
	}
	
	public int getLeftEncoder() {
		return leftEncoder;
	}
}