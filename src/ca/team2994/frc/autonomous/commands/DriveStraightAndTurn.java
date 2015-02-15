package ca.team2994.frc.autonomous.commands;

import ca.team2994.frc.autonomous.AutoCommand;
import ca.team2994.frc.robot.SimLib;
import ca.team2994.frc.robot.Subsystems;

public class DriveStraightAndTurn implements AutoCommand {

	private final double x;
	private final double y;
	
	private boolean stage2 = false;
	
	public DriveStraightAndTurn(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void initialize() {
		//Relative angle
		int theta = (int) Math.toDegrees(Math.atan2(x, y));
		int angle;
		if(x > 0 && y > 0) {
			angle = theta;
		}
		else if(x > 0 && y < 0) {
			angle = 180 - theta;
		}
		else if(x < 0 && y < 0) {
			angle = -(180 - theta);
		}
		else if(x < 0 && y > 0) {
			angle = -theta;
		}
		else {
			angle = 0;
		}

		
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
			//stage2 = true;
			return true;
		}
		else if (!Subsystems.encoderPID.isDone()) {
			if(!stage2) {
				stage2Init();
				stage2 = true;
			}
			
			double driveVal = Subsystems.encoderPID
					.calcPID((Subsystems.leftDriveEncoder.getDistance() + Subsystems.rightDriveEncoder
							.getDistance()) / 2.0);
			// TODO: Read this from the constants file as "encoderPIDMax"
			double limitVal = SimLib.limitValue(driveVal, 0.25);

			Subsystems.robotDrive.setLeftRightMotorOutputs(limitVal + 0.0038, limitVal);
			return true;
		}
		return false;
	}

	@Override
	public void cleanup() {
		Subsystems.robotDrive.drive(0.0, 0.0);
	}
	
	private void stage2Init() {
		double distance = Math.sqrt((x * x) + (y * y));
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
	}

}
