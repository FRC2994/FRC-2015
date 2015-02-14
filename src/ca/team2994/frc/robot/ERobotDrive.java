package ca.team2994.frc.robot;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.MotorSafety;
import edu.wpi.first.wpilibj.MotorSafetyHelper;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.can.CANNotInitializedException;
import edu.wpi.first.wpilibj.communication.FRCNetworkCommunicationsLibrary.tInstances;
import edu.wpi.first.wpilibj.communication.FRCNetworkCommunicationsLibrary.tResourceType;
import edu.wpi.first.wpilibj.communication.UsageReporting;

/**
 * Utility class for handling Robot drive based on a definition of the motor configuration.
 * The robot drive class handles basic driving for a robot. Currently, 2 and 4 motor standard
 * drive trains are supported. In the future other drive types like swerve and meccanum might
 * be implemented. Motor channel numbers are passed supplied on creation of the class. Those are
 * used for either the Drive function (intended for hand created drive code, such as autonomous)
 * or with the Tank/Arcade functions intended to be used for Operator Control driving.
 */
public class ERobotDrive implements MotorSafety {

	// Motor type
	public static final int MOTOR_FRONT_LEFT = 0;
	public static final int MOTOR_FRONT_RIGHT = 1;
	public static final int MOTOR_REAR_LEFT = 2;
	public static final int MOTOR_REAR_RIGHT = 3;
	public static final int MOTOR_CENTER_LEFT = 4;
	public static final int MOTOR_CENTER_RIGHT = 5; 
	
	public static final int SIX_MOTORS = 6;
	public static final int FOUR_MOTORS = 4;
	public static final int TWO_MOTORS = 2;

	private int m_invertedMotors[];
	private double m_sensitivity;
	private double m_maxOutput;
	private SpeedController m_frontLeftMotor;
	private SpeedController m_centerLeftMotor;
	private SpeedController m_frontRightMotor;
	private SpeedController m_rearLeftMotor;
	private SpeedController m_centerRightMotor;
	private SpeedController m_rearRightMotor;
	private MotorSafetyHelper m_safetyHelper;
	
	private static boolean driveReported = false;
		
	/*
	 * Driving functions
	 * These functions provide an interface to multiple motors that is used for C programming
	 * The Drive(speed, direction) function is the main part of the set that makes it easy
	 * to set speeds and direction independently in one call.
	 */
	/**
	 * Creates 6 motors in the class
	 * 
	 * @param frontLeftMotor
	 * @param rearLeftMotor
	 * @param centerLeftMotor
	 * @param centerRightMotor
	 * @param frontRightMotor
	 * @param rearRightMotor
	 */
	public ERobotDrive(SpeedController frontLeftMotor, SpeedController rearLeftMotor, SpeedController centerLeftMotor,
			SpeedController centerRightMotor, SpeedController frontRightMotor, SpeedController rearRightMotor) {
		initRobotDrive();
		m_frontLeftMotor = frontLeftMotor;
		m_rearLeftMotor = rearLeftMotor;
		m_centerLeftMotor = centerLeftMotor;
		m_centerRightMotor = centerRightMotor;
		m_frontRightMotor = frontRightMotor;
		m_rearRightMotor = rearRightMotor;
		m_invertedMotors = new int[6];
		for (int i=0; i < SIX_MOTORS; i++) {
			m_invertedMotors[i] = 1;
		}
	}

	/**
	 * Creates 4 motors in the class
	 * 
	 * @param frontLeftMotor
	 * @param rearLeftMotor
	 * @param frontRightMotor
	 * @param rearRightMotor
	 */
	public ERobotDrive(SpeedController frontLeftMotor, SpeedController rearLeftMotor, 
			SpeedController frontRightMotor, SpeedController rearRightMotor) {
		initRobotDrive();
		m_frontLeftMotor = frontLeftMotor;
		m_rearLeftMotor = rearLeftMotor;
		m_frontRightMotor = frontRightMotor;
		m_rearRightMotor = rearRightMotor;
		m_invertedMotors = new int[4];
		for (int i=0; i < FOUR_MOTORS; i++) {
			m_invertedMotors[i] = 1;
		}
	}

	/**
	 * Creates 2 motors in the class
	 * 
	 * @param frontLeftMotor
	 * @param frontRightMotor
	 */
	public ERobotDrive(SpeedController frontLeftMotor, SpeedController frontRightMotor) {
		initRobotDrive();
		m_frontLeftMotor = frontLeftMotor;
		m_frontRightMotor = frontRightMotor;
		m_invertedMotors = new int[2];
		for (int i=0; i < TWO_MOTORS; i++) {
			m_invertedMotors[i] = 1;
		}
	}
	
	/**
	 * Common function to initialize all the robot drive constructors.
	 * Create a motor safety object (the real reason for the common code) and
	 * initialize all the motor assignments. The default timeout is set for the robot drive.
	 */
	private void initRobotDrive() {
		m_frontLeftMotor = null;
		m_centerLeftMotor = null;
		m_frontRightMotor = null;
		m_rearRightMotor = null;
		m_centerRightMotor = null;
		m_rearLeftMotor = null;
		m_sensitivity = 0.5;
		m_maxOutput = 1.0;
		m_safetyHelper = new MotorSafetyHelper(this);
		m_safetyHelper.setSafetyEnabled(true);
	}

	private int getNumMotors() {
		int motors = 0;
		if (m_frontLeftMotor != null) {
			motors++;
		}
		if (m_frontRightMotor != null) {
			motors++;
		}
		if (m_rearLeftMotor != null) {
			motors++;
		}
		if (m_rearRightMotor != null) {
			motors++;
		}
		if (m_centerRightMotor != null) {
			motors++;
		}
		if (m_centerLeftMotor != null) {
			motors++;
		}
		return motors;
	}
	
	/**
	 * Drive the motors at "speed" and "curve".
	 *
	 * The speed and curve are -1.0 to +1.0 values where 0.0 represents stopped and
	 * not turning. The algorithm for adding in the direction attempts to provide a constant
	 * turn radius for differing speeds.
	 *
	 * This function will most likely be used in an autonomous routine.
	 *
	 * @param outputMagnitude The forward component of the output magnitude to send to the motors.
	 * @param curve The rate of turn, constant for different forward speeds.
	 */
	public void drive(double outputMagnitude, double curve) {
		double leftOutput, rightOutput;
		if (!driveReported) {
			UsageReporting.report(tResourceType.kResourceType_RobotDrive, getNumMotors(), tInstances.kRobotDrive_ArcadeStandard);
			driveReported = true;
		}

		if (curve < 0) {
			double value = Math.log(-curve);
			double ratio = (value - m_sensitivity)/(value + m_sensitivity);
			if (ratio == 0) {
				ratio = 0.0000000001;
			}
			leftOutput = outputMagnitude / ratio;
			rightOutput = outputMagnitude;
		} else if (curve > 0) {
			double value = Math.log(curve);
			double ratio = (value - m_sensitivity)/(value + m_sensitivity);
			if (ratio == 0) {
				ratio = 0.0000000001;
			}
			leftOutput = outputMagnitude;
			rightOutput = outputMagnitude / ratio;
		} else {
			leftOutput = outputMagnitude;
			rightOutput = outputMagnitude;
		}
		setLeftRightMotorOutputs(leftOutput, rightOutput);
	}
	
	/**
	 * Arcade drive implements single stick driving.
	 * Given a single Joystick, the class assumes the Y axis for the move value and the X axis
	 * for the rotate value.
	 * (Should add more information here regarding the way that arcade drive works.)
	 * @param stick The joystick to use for Arcade single-stick driving. The Y-axis will be selected
	 * for forwards/backwards and the X-axis will be selected for rotation rate.
	 * @param squaredInputs If true, the sensitivity will be increased for small values
	 */
	public void arcadeDrive(GenericHID stick, boolean squaredInputs)
	{
		// simply call the full-featured ArcadeDrive with the appropriate values
		arcadeDrive(stick.getY(), stick.getX(), squaredInputs);
	}

	/**
	 * Arcade drive implements single stick driving.
	 * Given two joystick instances and two axis, compute the values to send to either two
	 * or four motors.
	 * @param moveStick The Joystick object that represents the forward/backward direction
	 * @param moveAxis The axis on the moveStick object to use for fowards/backwards (typically Y_AXIS)
	 * @param rotateStick The Joystick object that represents the rotation value
	 * @param rotateAxis The axis on the rotation object to use for the rotate right/left (typically X_AXIS)
	 * @param squaredInputs Setting this parameter to true increases the sensitivity at lower speeds
	 */
	public void arcadeDrive(GenericHID moveStick, int moveAxis,
									GenericHID rotateStick, int rotateAxis,
									boolean squaredInputs)
	{
		double moveValue = moveStick.getRawAxis(moveAxis);
		double rotateValue = rotateStick.getRawAxis(rotateAxis);

		arcadeDrive(moveValue, rotateValue, squaredInputs);
	}
		
	/**
	 * Arcade drive implements single stick driving.
	 * This function lets you directly provide joystick values from any source.
	 * @param moveValue The value to use for fowards/backwards
	 * @param rotateValue The value to use for the rotate right/left
	 * @param squaredInputs If set, increases the sensitivity at low speeds
	 */
	public void arcadeDrive(double moveValue, double rotateValue, boolean squaredInputs) {
		if (!driveReported) {
			UsageReporting.report(tResourceType.kResourceType_RobotDrive, getNumMotors(), tInstances.kRobotDrive_ArcadeStandard);
			driveReported = true;
		}

		// local variables to hold the computed PWM values for the motors
		double leftMotorOutput;
		double rightMotorOutput;

		moveValue = limit(moveValue);
		rotateValue = limit(rotateValue);

		if (squaredInputs) {
			// square the inputs (while preserving the sign) to increase fine control while permitting full power
			if (moveValue >= 0.0) {
				moveValue = (moveValue * moveValue);
			} else {
				moveValue = -(moveValue * moveValue);
			}
			if (rotateValue >= 0.0) {
				rotateValue = (rotateValue * rotateValue);
			} else {
				rotateValue = -(rotateValue * rotateValue);
			}
		}

		if (moveValue > 0.0) {
			if (rotateValue > 0.0) {
				leftMotorOutput = moveValue - rotateValue;
				rightMotorOutput = max(moveValue, rotateValue);
			} else {
				leftMotorOutput = max(moveValue, -rotateValue);
				rightMotorOutput = moveValue + rotateValue;
			}
		} else {
			if (rotateValue > 0.0) {
				leftMotorOutput = - max(-moveValue, rotateValue);
				rightMotorOutput = moveValue + rotateValue;
			} else {
				leftMotorOutput = moveValue - rotateValue;
				rightMotorOutput = - max(-moveValue, -rotateValue);
			}
		}
		setLeftRightMotorOutputs(leftMotorOutput, rightMotorOutput);
	}

	private double max(double x, double y) {
		return (x > y) ? x : y;	
	}

	/** Set the speed of the right and left motors.
	 * This is used once an appropriate drive setup function is called such as
	 * TwoWheelDrive(). The motors are set to "leftOutput" and "rightOutput"
	 * and includes flipping the direction of one side for opposing motors.
	 * @param leftOutput The speed to send to the left side of the robot.
	 * @param rightOutput The speed to send to the right side of the robot.
	 * @throws CANTimeoutException 
	 */
	private void setLeftRightMotorOutputs(double leftOutput, double rightOutput) {
        if (m_rearLeftMotor == null || m_rearRightMotor == null) {
            throw new NullPointerException("Null motor provided");
        }
        
		byte syncGroup = (byte) 0x80;

		if (m_frontLeftMotor != null) {
			m_frontLeftMotor.set(limit(leftOutput) * m_invertedMotors[MOTOR_FRONT_LEFT] * m_maxOutput, syncGroup);
		}
		if (m_centerLeftMotor != null) {
			m_centerLeftMotor.set(limit(leftOutput) * m_invertedMotors[MOTOR_CENTER_LEFT] * m_maxOutput, syncGroup);
		}
		m_rearLeftMotor.set(limit(leftOutput) * m_invertedMotors[MOTOR_REAR_LEFT] * m_maxOutput, syncGroup);
		
		if (m_frontRightMotor != null)
			m_frontRightMotor.set(-limit(rightOutput) * m_invertedMotors[MOTOR_FRONT_RIGHT] * m_maxOutput, syncGroup);
		if (m_centerRightMotor != null)
			m_centerRightMotor.set(-limit(rightOutput) * m_invertedMotors[MOTOR_CENTER_LEFT] * m_maxOutput, syncGroup);
		m_rearRightMotor.set(-limit(rightOutput) * m_invertedMotors[MOTOR_REAR_RIGHT] * m_maxOutput, syncGroup);

		try {
			CANJaguar.updateSyncGroup(syncGroup);
        } catch (CANNotInitializedException e) {
    		System.out.println("CANNotInitializedException" + e.getMessage());
		}
		m_safetyHelper.feed();
	}
	
	/**
	 * Limit motor values to the -1.0 to +1.0 range.
	 */
	private double limit(double num) {
		if (num > 1.0) {
			return 1.0;
		}
		if (num < -1.0) {
			return -1.0;
		}
		return num;
	}
	
	/**
	 * Normalize all wheel speeds if the magnitude of any wheel is greater than 1.0.
	 */
	@SuppressWarnings("unused")
	private void normalize(double wheelSpeeds[]) {
		double maxMagnitude = Math.abs(wheelSpeeds[0]);
		int i;
		for (i=1; i<m_invertedMotors.length; i++)
		{
			double temp = Math.abs(wheelSpeeds[i]);
			if (maxMagnitude < temp) {
				maxMagnitude = temp;
			}
		}
		if (maxMagnitude > 1.0) {
			for (i=0; i<m_invertedMotors.length; i++) {
				wheelSpeeds[i] = wheelSpeeds[i] / maxMagnitude;
			}
		}
	}
	
	/**
	 * Rotate a vector in Cartesian space.
	 */
	@SuppressWarnings("unused")
	private void rotateVector(double x, double y, double angle) {
		double cosA = Math.cos(angle * (3.14159 / 180.0));
		double sinA = Math.sin(angle * (3.14159 / 180.0));
		double xOut = x * cosA - y * sinA;
		double yOut = x * sinA + y * cosA;
		x = xOut;
		y = yOut;
	}
	
	/*
	 * Invert a motor direction.
	 * This is used when a motor should run in the opposite direction as the drive
	 * code would normally run it. Motors that are direct drive would be inverted, the
	 * Drive code assumes that the motors are geared with one reversal.
	 * @param motor The motor index to invert.
	 * @param isInverted True if the motor should be inverted when operated.
	 */
	public void setInvertedMotor(int motor, boolean isInverted)	{
		if (motor < 0 || motor > 3) {
			throw new Error("Invalid Motor Index");
		}
		m_invertedMotors[motor] = isInverted ? -1 : 1;
	}
	
	/**
	 * Set the turning sensitivity.
	 * 
	 * This only impacts the Drive() entry-point.
	 * @param sensitivity Effectively sets the turning sensitivity (or turn radius for a given value)
	 */
	public void setSensitivity(double sensitivity) {
		m_sensitivity = sensitivity;
	}

	/**
	 * Configure the scaling factor for using RobotDrive with motor controllers in a mode other than PercentVbus.
	 * @param maxOutput Multiplied with the output percentage computed by the drive functions.
	 */
	public void setMaxOutput(double maxOutput) {
		m_maxOutput = maxOutput;
	}
	
	@Override
	public void setExpiration(double timeout) {
		m_safetyHelper.setExpiration(timeout);		
	}

	@Override
	public double getExpiration() {
		return m_safetyHelper.getExpiration();
	}

	@Override
	public boolean isAlive() {
		return m_safetyHelper.isAlive();
	}

	@Override
	public void stopMotor() {
		if (m_frontLeftMotor != null) {
			m_frontLeftMotor.disable();
		}
		if (m_frontRightMotor != null) {
			m_frontRightMotor.disable();
		}
		if (m_rearLeftMotor != null) {
			m_rearLeftMotor.disable();
		}
		if (m_rearRightMotor != null) {
			m_rearRightMotor.disable();
		}
		if (m_centerLeftMotor != null) {
			m_centerLeftMotor.disable();
		}
		if (m_centerRightMotor != null) {
			m_centerRightMotor.disable();
		}
	}

	@Override
	public void setSafetyEnabled(boolean enabled) {
		m_safetyHelper.setSafetyEnabled(enabled);
	}

	@Override
	public boolean isSafetyEnabled() {
		return m_safetyHelper.isSafetyEnabled();
	}

	@Override
	public String getDescription() {
		return "RobotDrive";
	}

}
