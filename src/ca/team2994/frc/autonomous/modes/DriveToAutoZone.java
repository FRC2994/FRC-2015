package ca.team2994.frc.autonomous.modes;

import ca.team2994.frc.autonomous.AutoBuilder;
import ca.team2994.frc.autonomous.AutoCommand;
import ca.team2994.frc.autonomous.AutoMode;
import ca.team2994.frc.autonomous.commands.DriveStraight;

public class DriveToAutoZone extends AutoMode {
	
	//TODO: Test this on practice field. These are bogus values.
	private static final double STRAIGHT_DISTANCE = 4;

	@Override
	protected AutoCommand[] initializeCommands() {
		// This assumes that we start in the staging zone parallel to the closest scoring platform
		AutoBuilder builder = new AutoBuilder();

		// Drive forward into the zone.
		builder.add(new DriveStraight(STRAIGHT_DISTANCE));
		
		
		return builder.toArray();
	}
}
