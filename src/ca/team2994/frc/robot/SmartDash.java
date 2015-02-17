package ca.team2994.frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SmartDash{

	/**For testing everything
	 * For testing use only, displays the motor's speed and rotation for all the available motors 
	 */
	public void showMotors() {
		showDriveMotors();
		showConveyorMotors();
		showArmMotors();
		showForkliftMotors();
	}
	
	/**
	 * The competition smart dashboard; displaying relevant information for users
	 */
	public void compDash() {
		SmartDashboard.putBoolean("Tote_ready: ", Subsystems.toteDetectionSensor.get());
		SmartDashboard.putNumber("ForkLift_Level: ", Subsystems.forklift.getLevel());
		SmartDashboard.putNumber("Tote_Count: ", Subsystems.stateMachine.getToteCount());
		
		//To inform the user if another tote may be added and the number of the next tote
		String next;
		if(Subsystems.stateMachine.getToteCount() + 1 > 3){
			next = "Full";
		}
		else{
			next = ""+(Subsystems.stateMachine.getToteCount() + 1);
		}
		SmartDashboard.putString(" Next_Tote: ", next);
		
		
//		SmartDashboard.putBoolean(" Manual_Mode: ", Subsystems.);
		
	}
	
	/**
	 * Displays the drive frames motor PWM and encoder ticks in the Smart Dashboard
	 */
	public void showDriveMotors(){
		SmartDashboard.putString(" DRIVE ", "MOTORS ");
		SmartDashboard.putNumber(" Left_Front_Motor_PWM: ", Subsystems.leftFrontDrive.get());	
		SmartDashboard.putNumber(" Left_Rear_Motor_PWM: ", Subsystems.leftRearDrive.get());	
		SmartDashboard.putNumber(" Right_Front_Motor_PWM: ", Subsystems.rightFrontDrive.get());	
		SmartDashboard.putNumber(" Right_Rear_Motor_PWM: ", Subsystems.rightRearDrive.get());
		SmartDashboard.putNumber(" Left_Motors_Ticks: ", Subsystems.leftDriveEncoder.get());		
		SmartDashboard.putNumber(" Right_Motors_Ticks: ", Subsystems.rightDriveEncoder.get());
	}
	
	/**For testing the Conveyor
	 * Displays the Conveyor motor PWM within Smart Dashboard
	 */
	public void showConveyorMotors(){
		SmartDashboard.putString(" CONVEYOR ", "BELT ");
		SmartDashboard.putNumber(" Conveyor_PWM: ", Subsystems.conveyorMotor.get());		
	}
	
	/**For testing the Arms
	 * Displays the PWM of the Arm motors within Smart Dashboard
	 */
	public void showArmMotors(){
		SmartDashboard.putString(" ARM ", "MOTORS ");
		SmartDashboard.putNumber(" Right_Arm_PWM: ", Subsystems.rightArmMotor.get());
		SmartDashboard.putNumber(" Left_Arm_PWM: ", Subsystems.leftArmMotor.get());
	}
	
	/**For testing Forklift 
	 * Displays the PWM, ticks and rate of the Forklift motor	
	 */
	public void showForkliftMotors(){
		SmartDashboard.putString(" FORKLIFT ", "MOTORS ");
		SmartDashboard.putNumber(" Forklift_PWM: ", Subsystems.forkliftMotor.get());
		SmartDashboard.putNumber(" Forklift_Ticks: ", Subsystems.forkliftEncoder.get());
		SmartDashboard.putNumber(" Forklift_Rate: ", Subsystems.forkliftEncoder.getRate());
	}
	
	/**For the comp dashboard
	 * Belive in yourself like I belive in you
	 */
	public void motivational(){
		SmartDashboard.putString(" You can do it! ", " Belive!!");
	}
	
	/**Shows all necessary information related to the drive 
	 * displays the showDriveMotors
	 */
	public void showDrive(){
		showDriveMotors();
		
		SmartDashboard.putString(" OTHER ", "DRIVE INFO");
		SmartDashboard.putNumber(" Left_Drive_Direction: ", Subsystems.leftDriveEncoder.getDistance());
		SmartDashboard.putNumber(" Right_Drive_Direction: ", Subsystems.rightDriveEncoder.getDistance());
	}
}
