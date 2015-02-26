package ca.team2994.frc.autonomous.commands;

import ca.team2994.frc.autonomous.AutoCommand;
import ca.team2994.frc.robot.Subsystems;

public class SimpleForklift implements AutoCommand {

	private final int tickLimit;
	private int tickCount = 0;
	
	public SimpleForklift(int tickLimit) {
		this.tickLimit = tickLimit;
	}
	
	@Override
	public void initialize() {
//		Subsystems.forklift.setLevel(level);
		
	}

	@Override
	public boolean tick() {
		if (tickCount < tickLimit) {
			Subsystems.forklift.moveUp();
			tickCount++;
			return true;
		}
		return false;
	}

	@Override
	public void cleanup() {
		Subsystems.forklift.stop();
	}

}
