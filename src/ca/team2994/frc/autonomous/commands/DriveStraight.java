package ca.team2994.frc.autonomous.commands;

import ca.team2994.frc.autonomous.AutoCommand;
import ca.team2994.frc.robot.SimLib;
import ca.team2994.frc.robot.Subsystems;

public class DriveStraight implements AutoCommand {
	
	private final double distance;

	public DriveStraight(double distance) {
		this.distance = distance;
	}
	
	@Override
	public void initialize() {
		// Reset the encoders (encoder.get(Distance|)() == 0)
		Subsystems.leftDriveEncoder.reset();
		Subsystems.rightDriveEncoder.reset();
		// Set up the desired number of units.
		Subsystems.encoderPID.setDesiredValue(distance);
		// Reset the encoder PID to a reasonable state.
		Subsystems.encoderPID.resetErrorSum();
		Subsystems.encoderPID.resetPreviousVal();
		// Used to make sure that the PID doesn't bail out as done
		// right away (we know both the distances are zero from the
		// above reset).
		Subsystems.encoderPID.calcPID(0);
		System.out.println("DriveStraight Init");
	}
	
	@Override
	public boolean tick() {
		if (!Subsystems.encoderPID.isDone()) {
			double driveVal = Subsystems.encoderPID.calcPID((Subsystems.leftDriveEncoder.getDistance() + Subsystems.rightDriveEncoder.getDistance()) / 2.0);
			// TODO: Read this from the constants file as "encoderPIDMax"
			double limitVal = SimLib.limitValue(driveVal, 0.25);

			Subsystems.robotDrive.setLeftRightMotorOutputs(limitVal, limitVal);
			System.out.println("LEV:" + Subsystems.leftDriveEncoder.get() + ",REV:" + Subsystems.rightDriveEncoder.get()+
					",LED:" + Subsystems.leftDriveEncoder.getDistance() + ",RED:" + Subsystems.rightDriveEncoder.getDistance()+",DV:"+driveVal);
			return true;
		}
		return false;
		
	}

	@Override
	public void cleanup() {
		Subsystems.robotDrive.drive(0.0, 0.0);	
	}

}
