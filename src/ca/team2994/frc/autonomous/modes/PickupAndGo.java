package ca.team2994.frc.autonomous.modes;

import ca.team2994.frc.autonomous.AutoBuilder;
import ca.team2994.frc.autonomous.AutoCommand;
import ca.team2994.frc.autonomous.AutoMode;
import ca.team2994.frc.autonomous.commands.DriveStraight;
import ca.team2994.frc.autonomous.commands.DriveTurn;
import ca.team2994.frc.autonomous.commands.SimpleForklift;

public class PickupAndGo extends AutoMode {
	
		//TODO: Test this on practice field. These are bogus values.
		private static final int TURN_ANGLE = 90;
		private static final double STRAIGHT_DISTANCE = 4;
		private static final int TIME = 500;

		@Override
		protected AutoCommand[] initializeCommands() {
			// This assumes that we start in the staging zone parallel to the closest scoring platform
			AutoBuilder builder = new AutoBuilder();

			// Drive straight to clear the staging zone 
			builder.add(new SimpleForklift(TIME));
			// Turn 90 degrees to get into the position to drive into the zone
			builder.add(new DriveTurn(TURN_ANGLE));
			// Drive forward into the zone.
			builder.add(new DriveStraight(STRAIGHT_DISTANCE));
			
			
			return builder.toArray();
		}
	}

