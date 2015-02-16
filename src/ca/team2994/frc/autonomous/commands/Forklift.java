package ca.team2994.frc.autonomous.commands;

import ca.team2994.frc.autonomous.AutoCommand;
import ca.team2994.frc.robot.Subsystems;

public class Forklift implements AutoCommand {

	private final int level;
	
	public Forklift(int level) {
		this.level = level;
	}
	
	@Override
	public void initialize() {
		Subsystems.forklift.setLevel(level);
		
	}

	@Override
	public boolean tick() {
		if(!Subsystems.forklift.isLevelReached()) {
			Subsystems.forklift.pidLoop();
			return true;
		}
		return false;
	}

	@Override
	public void cleanup() {
		Subsystems.forklift.stop();
	}

}
