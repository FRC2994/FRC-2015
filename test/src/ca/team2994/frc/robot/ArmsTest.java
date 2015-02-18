package ca.team2994.frc.robot;

import static org.junit.Assert.*;
import mockit.Delegate;
import mockit.Mocked;
import mockit.NonStrictExpectations;

import org.junit.Test;

import ca.team2994.frc.controls.Motor;
import ca.team2994.frc.mechanism.RobotArm;
import ca.team2994.frc.utils.Constants;

public class ArmsTest {
	
	@Mocked
	private Motor leftArm;
	@Mocked
	private Motor rightArm;
	
	
	double rightValue;
	double leftValue;
	int numb = 2;
	
	
	@Test
	public void armTest() {
		new NonStrictExpectations() {{
			leftArm.set(anyDouble);
			result = new Delegate<Double>() {
				@SuppressWarnings("unused")
				void set(double speed) {
					System.out.println("Left speed is " + speed);
					leftValue = speed;
				}
				
			};
			leftArm.get();
			result = new Delegate<Double>() {
				@SuppressWarnings("unused")
				double get() {
					if(numb != 0) {
						numb--;
					}
					else {
						System.out.println("Left motor is " + leftValue);
					}
					return leftValue;
				}
				
			};	
			leftArm.disable();
			result = new Delegate<Double>() {
				@SuppressWarnings("unused")
				void disable() {
					System.out.println("Disabled");
				}
				
			};				
			rightArm.set(anyDouble);
			result = new Delegate<Double>() {
				@SuppressWarnings("unused")
				void set(double speed) {
					System.out.println("Right speed is " + speed);
					rightValue = speed;
				}
				
			};
			rightArm.get();
			result = new Delegate<Double>() {
				@SuppressWarnings("unused")
				double get() {
					if(numb != 0) {
						numb--;
					}
					else {
						System.out.println("Right motor is " + rightValue);
					}
					return rightValue;
				}
				
			};	
			rightArm.disable();
			result = new Delegate<Double>() {
				@SuppressWarnings("unused")
				void disable() {
					System.out.println("Disabled");
				}
				
			};				
		}};
		
		Constants.readConstantPropertiesFromFile();

		RobotArm arms = new RobotArm(leftArm, rightArm);

		arms.load();
		 
		assertTrue(rightArm.get() == - Constants.getConstantAsDouble(Constants.ARM_LOAD_SPEED));
		assertTrue(leftArm.get()  == Constants.getConstantAsDouble(Constants.ARM_LOAD_SPEED));
		
		arms.unload();
		
		assertTrue(rightArm.get() == Constants.getConstantAsDouble(Constants.ARM_UNLOAD_SPEED));
		assertTrue(leftArm.get()  == - Constants.getConstantAsDouble(Constants.ARM_UNLOAD_SPEED));
		
		arms.stop();
	}
}

