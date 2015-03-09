package ca.team2994.frc.autonomous.modes;

import ca.team2994.frc.autonomous.AutoBuilder;
import ca.team2994.frc.autonomous.AutoCommand;
import ca.team2994.frc.autonomous.AutoMode;
import ca.team2994.frc.autonomous.commands.DriveStraight;
import ca.team2994.frc.autonomous.commands.GearShift;

public class ToteDriveStrategy extends AutoMode {

	private static final double FORWARD_DISTANCE = 13;
	private static final double REVERSE_DISTANCE = -4;
	
	@Override
	protected AutoCommand[] initializeCommands() {
		// This assumes that we start in the staging zone parallel to the closest scoring platform
		AutoBuilder builder = new AutoBuilder();

		// Shift to low gear
		builder.add(new GearShift(false));
		
		builder.add(new DriveStraight(FORWARD_DISTANCE));

		builder.add(new DriveStraight(REVERSE_DISTANCE));
		
		return builder.toArray();
	}

}
