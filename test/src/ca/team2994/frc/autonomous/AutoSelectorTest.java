package ca.team2994.frc.autonomous;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AutoSelectorTest {
	
	
	@Test
	public void encodeBoolsTest() {
		boolean[] values = new boolean[] {true, false, true};
		
		AutoModeSelector selector = new AutoModeSelector();
		
		assertTrue(selector.encodeBools(values) == 5);
		
		values[1] = true;
		
		assertTrue(selector.encodeBools(values) == 7);
		
		values[0] = false;
		
		assertTrue(selector.encodeBools(values) == 6);
		
		values[1] = false;
		values[2] = false;
		
		assertTrue(selector.encodeBools(values) == 0);
	}
}
