package ca.team2994.frc.robot;

import static org.junit.Assert.assertTrue;
import mockit.Delegate;
import mockit.Mocked;
import mockit.NonStrictExpectations;

import org.junit.Test;

import ca.team2994.frc.controls.Motor;
import ca.team2994.frc.mechanism.Forklift;
import ca.team2994.frc.utils.Constants;
import edu.wpi.first.wpilibj.Encoder;

/**
 * BROKEN
 */
public class ForkliftTest {
	
	@Mocked
	private Motor motor;
	@Mocked
	private Encoder encoder;
	
	double motorValue;
	int encoderValue;
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
			result = new Delegate<Integer>() {
				@SuppressWarnings("unused")
				int get() {
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
		
		Constants.readConstantPropertiesFromFile();

		// Initialize the PID.
		Subsystems.initPID();

		Forklift forklift = new Forklift(motor, encoder, Subsystems.forkliftPID);
		
		forklift.up(2);
			
		pidLoopForklift(forklift);

		assertTrue(error(encoder.get(), Constants.getConstantAsDouble(Constants.ENCODER_LEVELS[2])) < Constants.getConstantAsDouble(Constants.FORKLIFT_PID_E));

		forklift.down(1);

		pidLoopForklift(forklift);

		assertTrue(error(encoder.get(), Constants.getConstantAsDouble(Constants.ENCODER_LEVELS[1])) < Constants.getConstantAsDouble(Constants.FORKLIFT_PID_E));

		forklift.stop();
	}
	
	/**
	 * Simulates a PID loop one tick at a time
	 * @param forklift The Forklift object being tested.
	 */
	private void pidLoopForklift(Forklift forklift) {
		// Wait till it gets to it's level
		while (!forklift.isLevelReached()) {
			// The sign of motor.get() will be the direction we move the encoder
			encoderValue += (motor.get()/(Math.abs(motor.get())));
			forklift.automaticLoop();
		}
	}

	/**
	 * Returns the equivalent of |one-two|
	 * 
	 * @param one The number to be subtracted from
	 * @param two The number to subtract
	 * @return The absolute difference between the two numbers.
	 */
	private double error(double one, double two) {
		return Math.abs(one - two);
	}
}
