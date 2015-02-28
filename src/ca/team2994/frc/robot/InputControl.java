package ca.team2994.frc.robot;

import ca.team2994.frc.controls.ButtonEntry;
import ca.team2994.frc.controls.EGamepad;
import ca.team2994.frc.mechanism.StateMachine.Event;
import ca.team2994.frc.utils.Constants;

public class InputControl
{
	public static boolean isManualMode = true;
	
	public void init()
	{
		// Enable all buttons on the joystick and the gamepad here
		Subsystems.driveJoystick.enableButton(Constants.getConstantAsInt(Constants.JOYSTICK_HIGH_GEAR));
		Subsystems.controlGamepad.enableButton(Constants.getConstantAsInt(Constants.GAMEPAD_TOGGLE_MODE));
		Subsystems.controlGamepad.enableButton(Constants.getConstantAsInt(Constants.GAMEPAD_LOAD_TOTE));
		Subsystems.controlGamepad.enableButton(Constants.getConstantAsInt(Constants.GAMEPAD_UNLOAD_TOTE));
		Subsystems.controlGamepad.enableButton(Constants.getConstantAsInt(Constants.GAMEPAD_INCREMENT_HELD_TOTES));
		Subsystems.controlGamepad.enableButton(Constants.getConstantAsInt(Constants.GAMEPAD_DECREMENT_HELD_TOTES));
	}
	
	public void update()
	{
		Subsystems.driveJoystick.update();
		Subsystems.controlGamepad.update();
		
		if(Subsystems.driveJoystick.getState(Constants.getConstantAsInt(Constants.JOYSTICK_HIGH_GEAR)) == ButtonEntry.STATE_CLOSED)
		{
    		Subsystems.robotDrive.setHighGear();
		}
		else if(Subsystems.driveJoystick.getEvent(Constants.getConstantAsInt(Constants.JOYSTICK_HIGH_GEAR)) != ButtonEntry.STATE_ERR)
		{
			Subsystems.robotDrive.setLowGear();
		}
		
		Subsystems.robotDrive.arcadeDrive(Subsystems.driveJoystick, false);
		
		if(Subsystems.controlGamepad.getEvent(Constants.getConstantAsInt(Constants.GAMEPAD_TOGGLE_MODE)) == ButtonEntry.EVENT_CLOSED)
		{
			isManualMode = !isManualMode;
		}
		
		if (Subsystems.controlGamepad.getAxisCount() == 0)
		{
			isManualMode = true;
		}
		
		if(isManualMode)
		{
			// TODO Do things manually, add the ability to do this in a bit
			
			if (Subsystems.controlGamepad.getAxisCount() != 0)
			{
				if(Subsystems.controlGamepad.getDPadState(EGamepad.DPAD_DIRECTION_UP) == ButtonEntry.STATE_CLOSED)
				{
					Subsystems.forklift.moveUp();
				}
				else if(Subsystems.controlGamepad.getDPadState(EGamepad.DPAD_DIRECTION_DOWN) == ButtonEntry.STATE_CLOSED)
				{
					Subsystems.forklift.moveDown();
				}
				else
				{
					Subsystems.forklift.stop();
				}
				
				if(Subsystems.controlGamepad.getEvent(Constants.getConstantAsInt(Constants.GAMEPAD_INCREMENT_HELD_TOTES)) == ButtonEntry.EVENT_CLOSED)
				{
					Subsystems.forklift.increaseTotesHeld();
				}
				
				if(Subsystems.controlGamepad.getEvent(Constants.getConstantAsInt(Constants.GAMEPAD_DECREMENT_HELD_TOTES)) == ButtonEntry.EVENT_CLOSED)
				{
					Subsystems.forklift.decreaseTotesHeld();
				}
			}
			
			Subsystems.forklift.manualLoop();
		}
		else // XXX VERY DANGEROUS WAY TO TEST STATE MACHINE
		{
			if (Subsystems.controlGamepad.getAxisCount() != 0)
			{
				if(Subsystems.controlGamepad.getDPadEvent(EGamepad.DPAD_DIRECTION_UP) == ButtonEntry.EVENT_CLOSED)
				{
					Subsystems.forklift.up(Subsystems.forklift.getLevel() + 1);
				}
				
				if(Subsystems.controlGamepad.getDPadEvent(EGamepad.DPAD_DIRECTION_DOWN) == ButtonEntry.EVENT_CLOSED)
				{
					Subsystems.forklift.down(Subsystems.forklift.getLevel() - 1);
				}
				
				if(Subsystems.controlGamepad.getEvent(Constants.getConstantAsInt(Constants.GAMEPAD_LOAD_TOTE)) == ButtonEntry.EVENT_CLOSED)
				{
					Subsystems.stateMachine.callEvent(Event.B3);
				}
				
				if(Subsystems.controlGamepad.getEvent(Constants.getConstantAsInt(Constants.GAMEPAD_UNLOAD_TOTE)) == ButtonEntry.EVENT_CLOSED)
				{
					Subsystems.stateMachine.callEvent(Event.B4);
				}
			}
			
			Subsystems.forklift.automaticLoop();
		}
	}
	
}