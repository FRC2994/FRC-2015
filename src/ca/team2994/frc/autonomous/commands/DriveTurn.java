package ca.team2994.frc.autonomous.commands;

import ca.team2994.frc.autonomous.AutoCommand;
import ca.team2994.frc.robot.Subsystems;
import ca.team2994.frc.utils.Constants;
import ca.team2994.frc.utils.SimLib;

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
		System.out.println("DriveTurn Init:" + angle);
	}
	
	@Override
	public boolean tick() {
		if (!Subsystems.gyroPID.isDone()) {
			// Angle needs to be positive
			double driveVal = Subsystems.gyroPID.calcPID(Subsystems.gyroSensor.getAngle());
			double limitVal = SimLib.limitValue(driveVal, Constants.getConstantAsDouble(Constants.GYRO_PID_MAX));
			System.out.println("gyro.getAngle() = " + Subsystems.gyroSensor.getAngle()+",limitVal = " + limitVal);
			Subsystems.robotDrive.setLeftRightMotorOutputs(limitVal, -limitVal);
			return true;
		}
		return false;
		
	}

	@Override
	public void cleanup() {
		System.out.println("DriveTurn Cleanup");
		Subsystems.robotDrive.drive(0.0, 0.0);
	}
	
}
