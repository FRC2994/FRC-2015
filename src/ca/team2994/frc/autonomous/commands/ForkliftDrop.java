package ca.team2994.frc.autonomous.commands;

import ca.team2994.frc.autonomous.AutoCommand;
import ca.team2994.frc.robot.Subsystems;

public class ForkliftDrop implements AutoCommand {

	@Override
	public void initialize() {
		Subsystems.forklift.disable();
	}

	@Override
	public boolean tick() {
		return false;
	}

	@Override
	public void cleanup() {
	}

}
