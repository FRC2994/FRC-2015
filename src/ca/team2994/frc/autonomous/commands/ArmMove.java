package ca.team2994.frc.autonomous.commands;

import ca.team2994.frc.autonomous.AutoCommand;
import ca.team2994.frc.robot.Subsystems;

public class ArmMove implements AutoCommand {
	
	private final boolean direction;
	private final long timeInMillis;
	
	private long startTime;
	
	public ArmMove(boolean direction, long timeInMillis) {
		this.direction = direction;
		this.timeInMillis = timeInMillis;
	}
	
	@Override
	public void initialize() {
		if(direction) {
			Subsystems.robotArm.forward();
		}
		else {
			Subsystems.robotArm.reverse();
		}	
		startTime = System.currentTimeMillis();
	}

	@Override
	public boolean tick() {
		long timeDelta = System.currentTimeMillis() - startTime;
		if(timeDelta > timeInMillis) {
			return false;
		}
		return true;
	}

	@Override
	public void cleanup() {
		Subsystems.robotArm.stop();
	}

}
