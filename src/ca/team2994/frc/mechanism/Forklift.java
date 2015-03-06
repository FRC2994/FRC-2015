package ca.team2994.frc.mechanism;

import ca.team2994.frc.controls.Motor;
import ca.team2994.frc.utils.Constants;
import ca.team2994.frc.utils.SimPID;
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
	private double currentPosition;
	
	private double encoderLevels[] = new double[Constants.getConstantAsInt(Constants.FORKLIFT_MAX_LEVEL) + 1];
	private int levelIndex = 0;
	private int totesHeld = 0;
	
	public Forklift(Motor liftMotor, Encoder liftEncoder, SimPID liftPID)
	{
		forkliftMotor = liftMotor;
		forkliftEncoder = liftEncoder;

		forkliftPID = liftPID;
		
		for(int i = 0; i < 4; i++){
			encoderLevels[i] = Constants.getConstantAsDouble(Constants.ENCODER_LEVELS[i]);
		}

		forkliftEncoder.reset();
		currentPosition = forkliftEncoder.get();
	}
	
	public void increaseTotesHeld()
	{
		totesHeld++;
	}
	
	public void decreaseTotesHeld()
	{
		totesHeld--;
	}
	
	public int getTotesHeld()
	{
		return totesHeld;
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
		currentPosition += Constants.getConstantAsDouble(Constants.FORKLIFT_POSITION_INCREMENT);
//		forkliftMotor.set(Constants.getConstantAsDouble(Constants.FORKLIFT_UP_SPEED));
	}
	
	public void moveDown()
	{
		currentPosition += Constants.getConstantAsDouble(Constants.FORKLIFT_POSITION_DECREMENT);
//		forkliftMotor.set(Constants.getConstantAsDouble(Constants.FORKLIFT_DOWN_SPEED));
	}
	
	public void manualLoop()
	{
		for(int i = 0; i < encoderLevels.length; i++)
		{
			if(forkliftEncoder.get() < encoderLevels[i] + 50 && forkliftEncoder.get() > encoderLevels[i] - 50)
			{
				levelIndex = i;
			}
		}
		
		capLiftPosition();
		forkliftPID.setDesiredValue(currentPosition);
		forkliftMotor.set(forkliftPID.calcPID(forkliftEncoder.get()));
		System.out.println("Pos: " + currentPosition + " Val: " + forkliftEncoder.get());
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
//		forkliftMotor.set(0.0);
		forkliftMotor.set(Constants.getConstantAsDouble(Constants.FORKLIFT_TOTE_HOLD_SPEED));
	}
	
	public void disable()
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
		if(levelIndex < Constants.getConstantAsInt(Constants.FORKLIFT_MIN_LEVEL))
		{
			levelIndex = Constants.getConstantAsInt(Constants.FORKLIFT_MIN_LEVEL);
		}
		
		if(levelIndex > Constants.getConstantAsInt(Constants.FORKLIFT_MAX_LEVEL))
		{
			levelIndex = Constants.getConstantAsInt(Constants.FORKLIFT_MAX_LEVEL);
		}
	}
	
	private void capLiftPosition()
	{
		if(currentPosition < Constants.getConstantAsDouble(Constants.FORKLIFT_MIN_POSITION))
		{
			currentPosition = Constants.getConstantAsDouble(Constants.FORKLIFT_MIN_POSITION);
		}
		
		if(currentPosition > Constants.getConstantAsDouble(Constants.FORKLIFT_MAX_POSITION))
		{
			currentPosition = Constants.getConstantAsDouble(Constants.FORKLIFT_MAX_POSITION);
		}
	}
}