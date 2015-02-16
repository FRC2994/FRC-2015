package ca.team2994.frc.mechanism;

import ca.team2994.frc.robot.Motor;
import ca.team2994.frc.robot.SimPID;
import edu.wpi.first.wpilibj.Encoder;

/**
 * 
 * @author Tylar aka Mr.Perfect
 *
 */
public class Forklift
{
	private final Motor forkliftMotor;
	private final Encoder forkliftEncoder;
	private SimPID forkliftPID;
	
	private double encoderLevels[] = new double[4];
	private int levelIndex = 0;
	private boolean isManualMode = true;
	private double manualSpeed = 0.0;
	
	public Forklift(Motor liftMotor, Encoder liftEncoder)
	{
		forkliftMotor = liftMotor;
		forkliftEncoder = liftEncoder;
		forkliftPID = new SimPID(0.006, 0.001, 0.001);
		encoderLevels[0] = 300.0;
		encoderLevels[1] = 600.0;
		encoderLevels[2] = 900.0;
		encoderLevels[3] = 1200.0;
		forkliftEncoder.reset();
	}
	
	public void levelUp()
	{
		setLevel(getLevel() + 1);
	}
	
	public void levelDown()
	{
		setLevel(getLevel() - 1);
	}
	
	public void setLevel(int level)
	{
		levelIndex = level;
		capLiftLevel();
		forkliftPID.setDesiredValue(encoderLevels[levelIndex]);
	}
	
	public void toggleManual()
	{
		setManualMode(!getManualMode());
	}
	
	public void moveUp()
	{
		setSpeed(0.5);
	}
	
	public void moveDown()
	{
		setSpeed(-0.5);
	}
	
	public void stop()
	{
		setSpeed(0.0);
	}
	
	public void setSpeed(double speed)
	{
		manualSpeed = speed;
	}
	
	public int getLevel()
	{
		return levelIndex;
	}
	
	public boolean isLevelReached()
	{
		return forkliftPID.isDone();
	}
	
	private boolean getManualMode()
	{
		return isManualMode;
	}
	
	private void setManualMode(boolean isManual)
	{
		isManualMode = isManual;
	}
	
	private void capLiftLevel()
	{
		if(levelIndex < 0)
		{
			levelIndex = 0;
		}
		
		if(levelIndex > 3)
		{
			levelIndex = 3;
		}
	}
	
	public void update()
	{
		double driveVal = 0.0;
		if(isManualMode)
		{
			driveVal = manualSpeed;
		}
		else
		{
			driveVal = forkliftPID.calcPID(forkliftEncoder.get());
		}
		
		forkliftMotor.set(driveVal);
	}
}