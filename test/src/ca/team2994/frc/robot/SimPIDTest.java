package ca.team2994.frc.robot;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class SimPIDTest {
	@Test
	public void maxOutputTest() {
		SimPID pid = new SimPID();
		for(double i = -10; i <= 10; i =+ 0.1) {
			pid.setMaxOutput(i);
			assertFalse("PID Max set improperly", (pid.getMaxOutput() <= 0.0 && pid.getMaxOutput() >= 1.0));
			if(pid.getMaxOutput() > 0 && pid.getMaxOutput() < 1.0) {
				assertTrue("PID Max set improperly", pid.getMaxOutput() == i);
			}
		}
	}
}
