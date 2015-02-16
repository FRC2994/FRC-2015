package ca.team2994.frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SmartDash {

	public void showMotors() {
		
		SmartDashboard.putNumber(" Left_Front_Motor_Speed", Subsystems.leftFrontDrive.get());	
		SmartDashboard.putNumber(" Left_Rear_Motors_Speed", Subsystems.leftRearDrive.get());	
		SmartDashboard.putNumber(" Right_Front_Motors_Speed", Subsystems.rightFrontDrive.get());	
		SmartDashboard.putNumber(" Right_Rear_Motors_Speed", Subsystems.rightRearDrive.get());
		SmartDashboard.putNumber(" Left_Motor_Rotation", Subsystems.leftDriveEncoder.get());		
		SmartDashboard.putNumber(" Right_Motor_Rotation", Subsystems.rightDriveEncoder.get());	
	}
	
	public void compDash() {
		SmartDashboard.putBoolean(" Tote_ready: ",Subsystems.toteDetectionSensor.get());
		SmartDashboard.putNumber(" ForkLift_Level: ",Subsystems.forklift.getLevel());
		if (AstechzRobot.Gear == false){
			SmartDashboard.putNumber(" Gear level: ",1);
		} else {
			SmartDashboard.putNumber(" Gear level: ",2);
		}
  }
}