package ca.team2994.frc.autonomous.modes;

import ca.team2994.frc.autonomous.AutoCommand;
import ca.team2994.frc.autonomous.AutoMode;

public class DoNothingMode extends AutoMode {
	@Override
	protected AutoCommand[] initializeCommands() {
		// Does nothing.
		return new AutoCommand[] {};
	}

}
