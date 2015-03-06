package ca.team2994.frc.autonomous.modes;

import ca.team2994.frc.autonomous.AutoBuilder;
import ca.team2994.frc.autonomous.AutoCommand;
import ca.team2994.frc.autonomous.AutoMode;
import ca.team2994.frc.autonomous.commands.DriveStraight;
import ca.team2994.frc.autonomous.commands.GearShift;

public class DriveToAutoZone extends AutoMode {
	
	//TODO: Test this on practice field. These are bogus values.
	private static final double STRAIGHT_DISTANCE = 6.5;
//	private static final int SIMPLE_FORKLIFT_COUNTER = 50;

	@Override
	protected AutoCommand[] initializeCommands() {
		// Alignment: Back of Robot with Staging Zone 
		AutoBuilder builder = new AutoBuilder();

		// Shift to low gear
		builder.add(new GearShift(false));
		
		// Lift Forklift Up enough to get it off the ground
		// == Don't need this with new mods done to the forklift
		//builder.add(new SimpleForklift(SIMPLE_FORKLIFT_COUNTER));
		
		// Drive forward into the zone.
		builder.add(new DriveStraight(STRAIGHT_DISTANCE * 1.2));
		
		// Disable Forklift
//		builder.add(new ForkliftDrop());
		
		return builder.toArray();
	}
}
