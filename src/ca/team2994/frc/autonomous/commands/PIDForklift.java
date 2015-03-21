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
		System.out.println("PIDForklift tick " + Subsystems.forkliftEncoder.get());
		
		if(Subsystems.forklift.isCompleted())
		{
			return false;
		}
		
		Subsystems.forklift.manualLoop();
		
		return true;
	}
	
	@Override
	public void cleanup() {
		System.out.println("PIDForklift Cleanup");
		// Do something here?
//		Subsystems.forklift.syncPositionWithEncoder(); FIXME Might not be necessary
	}
	
}