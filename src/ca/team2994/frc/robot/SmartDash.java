package ca.team2994.frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SmartDash {

	public SmartDash() {
		SmartDashboard.putNumber("LeftFrontMotor", Subsystems.leftFrontDrive.get());	
	}
}
