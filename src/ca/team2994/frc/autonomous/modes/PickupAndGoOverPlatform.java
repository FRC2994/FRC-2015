package ca.team2994.frc.autonomous.modes;

import ca.team2994.frc.autonomous.AutoBuilder;
import ca.team2994.frc.autonomous.AutoCommand;
import ca.team2994.frc.autonomous.AutoMode;
import ca.team2994.frc.autonomous.commands.DriveStraight;
import ca.team2994.frc.autonomous.commands.DriveTurn;
import ca.team2994.frc.autonomous.commands.ForkliftDrop;
import ca.team2994.frc.autonomous.commands.GearShift;
import ca.team2994.frc.autonomous.commands.SimpleForklift;

public class PickupAndGoOverPlatform extends AutoMode {

	// TODO: Test this on practice field. These are bogus values.
	private static final int TURN_ANGLE = 90;
	private static final double STRAIGHT_DISTANCE = 8.5;
	private static final double OUT_OF_THE_WAY_DISTANCE = 1;
	private static final int TIME = 100;

	@Override
	protected AutoCommand[] initializeCommands() {
		// Alignment: Forklift surrounding Left most tote
		AutoBuilder builder = new AutoBuilder();

		// Shift to low gear
		builder.add(new GearShift(false));
		
		// Pickup the tote
		builder.add(new SimpleForklift(TIME));
		
		// Drive forwards so we don't knock over the garbage can behind us
		builder.add(new DriveStraight(OUT_OF_THE_WAY_DISTANCE));
		// Turn 90 degrees to get into the position to drive into the zone
		builder.add(new DriveTurn(TURN_ANGLE));
		// Drive forward into the zone.
		builder.add(new DriveStraight(STRAIGHT_DISTANCE));

		//Drop tote
		builder.add(new ForkliftDrop());
		
		return builder.toArray();
	}
}
