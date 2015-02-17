package ca.team2994.frc.mechanism;

import ca.team2994.frc.robot.Constants;
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
	
	private double encoderLevels[];
	private int levelIndex = 0;
	
	public Forklift(Motor liftMotor, Encoder liftEncoder, SimPID liftPID)
	{
		forkliftMotor = liftMotor;
		forkliftEncoder = liftEncoder;

		forkliftPID = liftPID;
		
		for(int i = 0; i < 4; i++){
			encoderLevels[i] = Constants.getConstantAsDouble(Constants.ENCODER_LEVELS[i]);
		}

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
	
	public void moveUp()
	{
		forkliftMotor.set(0.5);
	}
	
	public void moveDown()
	{
		forkliftMotor.set(-0.5);
	}
	
	public void manualLoop()
	{
		for(int i = 0; i < encoderLevels.length; i++)
		{
			// Check to see if the encoder is within 100 of any of the levels
			if(forkliftEncoder.get() < encoderLevels[i] + 50 && forkliftEncoder.get() > encoderLevels[i] - 50)
			{
				// If it is then set the level to the current level
				levelIndex = i;
			}
		}
	}
	
	public void automaticLoop()
	{
		forkliftMotor.set(forkliftPID.calcPID(forkliftEncoder.get()));
		
		if(forkliftPID.isDone())
		{
			stop();
		}
	}
	
	public void up(int level)
	{
		levelIndex = level;
		capLiftLevel();
		forkliftPID.setDesiredValue(encoderLevels[levelIndex]);
		forkliftMotor.set(forkliftPID.calcPID(forkliftEncoder.get()));
		
		if(forkliftPID.isDone())
		{
			stop();
		}
	}
	
	public void down(int level)
	{
		levelIndex = level;
		capLiftLevel();
		forkliftPID.setDesiredValue(encoderLevels[levelIndex]);
		forkliftMotor.set(forkliftPID.calcPID(forkliftEncoder.get()));
		
		if(forkliftPID.isDone())
		{
			stop();
		}
	}
	
	public void stop()
	{
		forkliftMotor.set(0.0);
	}
	
	public int getLevel()
	{
		return levelIndex;
	}
	
	public boolean isLevelReached()
	{
		return forkliftPID.isDone();
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
}