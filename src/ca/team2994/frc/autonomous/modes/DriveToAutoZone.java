package ca.team2994.frc.autonomous.modes;

import ca.team2994.frc.autonomous.AutoBuilder;
import ca.team2994.frc.autonomous.AutoCommand;
import ca.team2994.frc.autonomous.AutoMode;
import ca.team2994.frc.autonomous.commands.DriveStraight;
import ca.team2994.frc.autonomous.commands.GearShift;

public class DriveToAutoZone extends AutoMode {
	
	//TODO: Test this on practice field. These are bogus values.
	private static final double STRAIGHT_DISTANCE = 10; // TODO Change this number!!!!!
//	private static final int SIMPLE_FORKLIFT_COUNTER = 50;
	
	@Override
	protected AutoCommand[] initializeCommands() {
		// Alignment: Back of Robot with Staging Zone 
		AutoBuilder builder = new AutoBuilder();
		
		// Shift to low gear
		builder.add(new GearShift(false));
		
		// Drive forward into the zone.
		builder.add(new DriveStraight(STRAIGHT_DISTANCE));
		
		return builder.toArray();
	}
}
