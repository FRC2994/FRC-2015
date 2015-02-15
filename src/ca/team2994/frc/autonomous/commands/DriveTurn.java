package ca.team2994.frc.autonomous.commands;

import ca.team2994.frc.autonomous.AutoCommand;
import ca.team2994.frc.robot.SimLib;
import ca.team2994.frc.robot.Subsystems;

public class DriveTurn implements AutoCommand {

	private final int angle;
	
	public DriveTurn(int angle) {
		this.angle = angle;
	}
	
	@Override
	public void initialize() {
		Subsystems.gyroPID.setDesiredValue(angle);
		Subsystems.gyroSensor.reset(0);
		// Reset the gyro PID to a reasonable state.
		Subsystems.encoderPID.resetErrorSum();
		Subsystems.encoderPID.resetPreviousVal();
		// Used to make sure that the PID doesn't bail out as done
		// right away (we know the gyro angle is zero from the above
		// reset).
		Subsystems.gyroPID.calcPID(0);
		
	}
	
	@Override
	public boolean tick() {
		if (!Subsystems.gyroPID.isDone()) {
			System.out.println("gyro.getAngle() = " + Subsystems.gyroSensor.getAngle());
			double driveVal = Subsystems.gyroPID.calcPID(-Subsystems.gyroSensor.getAngle());
			// TODO: Read this from the constants file as "gyroPIDMax"
			double limitVal = SimLib.limitValue(driveVal, 0.25);
			System.out.println("limitVal = " + limitVal);
			Subsystems.robotDrive.setLeftRightMotorOutputs(limitVal, -limitVal);
			return true;
		}
		return false;
		
	}

	@Override
	public void cleanup() {
		Subsystems.robotDrive.drive(0.0, 0.0);
	}
	
}
