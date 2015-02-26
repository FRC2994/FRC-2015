package ca.team2994.frc.autonomous.modes;

import ca.team2994.frc.autonomous.AutoBuilder;
import ca.team2994.frc.autonomous.AutoCommand;
import ca.team2994.frc.autonomous.AutoMode;
import ca.team2994.frc.autonomous.commands.DriveStraight;
import ca.team2994.frc.autonomous.commands.DriveTurn;
import ca.team2994.frc.autonomous.commands.GearShift;
import ca.team2994.frc.autonomous.commands.SimpleForklift;

public class TestAutoMode extends AutoMode {

	@Override
	protected AutoCommand[] initializeCommands() {
		AutoBuilder builder = new AutoBuilder();
		
		builder.add(new GearShift(false));	//Low gear

		builder.add(new SimpleForklift(40));
		builder.add(new DriveTurn(365));	//Turn 360 Degrees (virtual 360 seems to be around 365)
		builder.add(new DriveStraight(3));	//Drive 5'

		//builder.add(new Forklift(2));
		
//		builder.add(new DriveTurn(180));	//Turn 360 Degrees
//		builder.add(new DriveStraight(5));	//Drive 5'
		
		//builder.add(new Forklift(1));
		
//		builder.add(new DriveStraight(-5));	//Drive 5'
		
//		builder.add(new DriveTurn(180));	//Turn 360 Degrees
		
		return builder.toArray();
	}

}
