package ca.team2994.frc.robot;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SimPIDTest {
	@Test
	public void maxOutputTest() {
		SimPID pid = new SimPID();
		pid.setMaxOutput(200);
		assertTrue("PID Max set improperly", pid.getMaxOutput() == 1.0);
	}
}
