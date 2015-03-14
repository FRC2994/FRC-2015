package ca.team2994.frc.autonomous.commands;

import ca.team2994.frc.autonomous.AutoCommand;
import ca.team2994.frc.robot.Subsystems;

public class PIDForklift implements AutoCommand {
	
	private final double liftPosition;
	
	public PIDForklift(double position) {
		liftPosition = position;
	}
	
	@Override
	public void initialize() {
		Subsystems.forklift.syncPositionWithEncoder();
		Subsystems.forklift.setPosition(liftPosition);
	}
	
	@Override
	public boolean tick() {
		if(!Subsystems.forklift.isCompleted())
		{
			Subsystems.forklift.manualLoop();
			return true;
		}
		
		return false;
	}
	
	@Override
	public void cleanup() {
		// Do something here?
//		Subsystems.forklift.syncPositionWithEncoder(); FIXME Might not be necessary
	}
	
}