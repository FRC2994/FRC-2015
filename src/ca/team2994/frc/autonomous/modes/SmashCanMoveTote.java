package ca.team2994.frc.autonomous.modes;

import ca.team2994.frc.autonomous.AutoMode;
import ca.team2994.frc.autonomous.AutoBuilder;
import ca.team2994.frc.autonomous.AutoCommand;
import ca.team2994.frc.autonomous.commands.DriveStraight;
import ca.team2994.frc.autonomous.commands.DriveTurn;
import ca.team2994.frc.autonomous.commands.GearShift;
import ca.team2994.frc.autonomous.commands.SimpleForklift;

public class SmashCanMoveTote extends AutoMode {

	// TODO: Test this on practice field. These are bogus values.
	private static final double FIRST_STRAIGHT_DISTANCE = 3;
	private static final double SECOND_STRAIGHT_DISTANCE = 4;
	private static final double THIRD_STRAIGHT_DISTANCE = 4;
	
	private static final int FIRST_TURN_ANGLE = 45;
	private static final int SECOND_TURN_ANGLE = 90;
	
	private static final int TIME = 500;

	@Override
	protected AutoCommand[] initializeCommands() {
		// This assumes that we start in the staging zone parallel to the
		// closest scoring platform
		AutoBuilder builder = new AutoBuilder();

		// Shift to low gear
		builder.add(new GearShift(false));
		
		//Be 45 degrees beside the can
		builder.add(new DriveTurn(FIRST_TURN_ANGLE));
		builder.add(new DriveStraight(FIRST_STRAIGHT_DISTANCE));
		
		//Smash the can
		builder.add(new DriveTurn(-FIRST_TURN_ANGLE));
		
		//Go back to original place
		builder.add(new DriveTurn(FIRST_TURN_ANGLE));
		builder.add(new DriveStraight(-FIRST_STRAIGHT_DISTANCE));
		builder.add(new DriveTurn(-FIRST_TURN_ANGLE));
		
		//Go to tote
		builder.add(new DriveStraight(SECOND_STRAIGHT_DISTANCE));
		
		//Lift tote
		builder.add(new SimpleForklift(TIME));
		
		//Turn and go
		builder.add(new DriveTurn(SECOND_TURN_ANGLE));
		builder.add(new DriveStraight(THIRD_STRAIGHT_DISTANCE));
		
		return builder.toArray();
	}
}
