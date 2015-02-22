package ca.team2994.frc.controls;

import static org.junit.Assert.assertTrue;
import mockit.Mocked;

import org.junit.Test;

import ca.team2994.frc.controls.Motor;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;

public class MotorTest {

	@Mocked Talon talon;
	@Mocked Victor victor;
	@Mocked CANTalon canTalon;
	@Mocked CANJaguar canJaguar;
	@Mocked Jaguar jaguar;
		
	@Test
	public void motorTypeTest() {
		//TODO: Rewrite this test so that the Motor class doesn't have to have these members public.
		Motor motor = new Motor(0, 0);
		assertTrue("Wrong class:" + motor.realMotor.getChannel() + ", expecting Talon", motor.realMotor instanceof Talon);
		motor = new Motor(0, 1);
		assertTrue("Wrong class:" + motor.realMotor.getChannel() + ", expecting Victor", motor.realMotor instanceof Victor);
		motor = new Motor(0, 2);
		assertTrue("Wrong class as CANTalon = null", motor.canTalon != null);
		motor = new Motor(0, 3);
		assertTrue("Wrong class as CANJaguar = null", motor.canJaguar != null);
		motor = new Motor(0, 4);
		assertTrue("Wrong class:" + motor.realMotor.getChannel() + ", expecting Jaguar", motor.realMotor instanceof Jaguar);
		motor = new Motor(0, 5);
		assertTrue("Wrong class:" + motor.realMotor.getChannel() + ", expecting Jaguar", motor.realMotor instanceof Jaguar);
	}
	
}
