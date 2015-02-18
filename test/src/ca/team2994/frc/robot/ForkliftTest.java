package ca.team2994.frc.robot;

import static org.junit.Assert.assertTrue;
import mockit.Delegate;
import mockit.Mocked;
import mockit.NonStrictExpectations;

import org.junit.Test;

import ca.team2994.frc.mechanism.Forklift;
import ca.team2994.frc.robot.Motor;
import edu.wpi.first.wpilibj.Encoder;

/**
 * BROKEN
 */
public class ForkliftTest {
	
	@Mocked
	private Motor motor;
	@Mocked
	private Encoder encoder;
	@Mocked
	private SimPID pid;
	
	double motorValue;
	double encoderValue;
	double ForkliftValue;
	int numb = 2;
	
	
	@Test
	public void forkliftTest() {
		new NonStrictExpectations() {{
			motor.set(anyDouble);
			result = new Delegate<Double>() {
				@SuppressWarnings("unused")
				void set(double speed) {
					System.out.println("Speed is " + speed);
					motorValue = speed;
				}
				
			};
			motor.get();
			result = new Delegate<Double>() {
				@SuppressWarnings("unused")
				double get() {
					if(numb != 0) {
						numb--;
					}
					else {
						System.out.println("Speed is " + motorValue);
					}
					return motorValue;
				}
				
			};	
			motor.disable();
			result = new Delegate<Double>() {
				@SuppressWarnings("unused")
				void disable() {
					System.out.println("Disabled");
				}
				
			};		
			encoder.reset();
			result = new Delegate<Double>() {
				@SuppressWarnings("unused")
				void reset() {
					System.out.println("Encoder is 0");
					encoderValue = 0;
				}
			};
			encoder.get();
			result = new Delegate<Double>() {
				@SuppressWarnings("unused")
				double get() {
					if(numb != 0) {
						numb--;
					}
					else {
						System.out.println("Encoder is " + encoderValue);
					}
					return encoderValue;
				}
				
			};	
			motor.disable();
			result = new Delegate<Double>() {
				@SuppressWarnings("unused")
				void disable() {
					System.out.println("Disabled");
				}
				
			};				
		}};
	
		encoder.reset();
		
		pid = new SimPID(0.006, 0.001, 0.001);
		
		Constants.readConstantPropertiesFromFile();

		Forklift forklift = new Forklift(motor, encoder, pid);
		
		forklift.up(2);
		
		assertTrue(encoder.get() == Constants.getConstantAsDouble(Constants.ENCODER_LEVELS[1]));
		
		forklift.down(1);
		
		assertTrue(encoder.get() == Constants.getConstantAsDouble(Constants.ENCODER_LEVELS[0]));
		
		forklift.stop();
		}
}
