package ca.team2994.frc.mechanism;

/**
 * 
 * @author Tylar aka Mr.Perfect
 *
 */
public class StateMachine
{
	
	private static enum Mode
	{
		A, 						// Add
		R, 						// Remove
		N 						// None
	}
	
	private static enum Arm
	{
		P, 						// Picking up
		D, 						// Dropping off
		L, 						// Loading
		U, 						// Unloading
		S 						// Stopped
	}
	
	private static enum Conveyor
	{
		L, 						// Loading
		U, 						// Unloading
		S						// Stopped
	}
	
	private static enum Lift
	{
		S, 						// Stopped
		U, 						// Up
		D 						// Down
	}
	
	public static enum Event
	{
		LRP,					// Lift reaches position
		AS,						// Arm stops
		CS, 					// Conveyor stops (Deprecated)
		B3, 					// Load a tote and put it on the conveyor
		B4 						// Unload a tote
	}
	
	private Mode mode;
	private Arm armState;
	private Conveyor conveyorState;
	private Lift liftState;
	private int toteCount;
	private boolean increaseToteCount = false;
	
	public StateMachine()
	{
		mode = Mode.N;
		armState = Arm.S;
		conveyorState = Conveyor.S;
		liftState = Lift.S;
		toteCount = 0;
	}
	
	public int getToteCount()
	{
		return toteCount;
	}
	
	public String getStateCode()
	{
		String stateCode = "";
		stateCode += mode.name();
		stateCode += armState.name();
		stateCode += conveyorState.name();
		stateCode += liftState.name();		
		stateCode += toteCount;
		
		return stateCode;
	}
	
	private void callEvent(Event event)
	{
		switch(mode)
		{
		case A:
			if(toteCount < 3)
			{
				if(event == Event.AS)
				{
					if(armState == Arm.P)
					{
						armState = Arm.S;
						liftState = Lift.U;
					}
					else if(armState == Arm.L)
					{
						armState = Arm.S;
						conveyorState = Conveyor.S;
						liftState = Lift.D;
					}
				}
				else if(event == Event.LRP)
				{
					if(liftState == Lift.U)
					{
						armState = Arm.L;
						conveyorState = Conveyor.L;
						liftState = Lift.S;
					}
					else if(liftState == Lift.D)
					{
						// Everything is done at this point
						mode = Mode.N;
						liftState = Lift.S;
						increaseToteCount = true;
					}
				}
			}
			break;
			
		case R:
			if(event == Event.LRP)
			{
				if(liftState == Lift.U)
				{
					armState = Arm.U;
					conveyorState = Conveyor.U;
					liftState = Lift.S;
				}
				else if(liftState == Lift.D)
				{
					armState = Arm.D;
					conveyorState = Conveyor.S;
					liftState = Lift.S;
				}
			}
			else if(event == Event.AS)
			{
				if(armState == Arm.U)
				{
					armState = Arm.S;
					conveyorState = Conveyor.S;
					liftState = Lift.D;
				}
				else if(armState == Arm.D)
				{
					mode = Mode.N;
					armState = Arm.S;
					// Assume that all totes are being removed as opposed to just one
					toteCount = 0;
				}
			}
			break;
			
		case N:
			if(toteCount < 3)
			{
				if(event == Event.B3)
				{
					mode = Mode.A;
					armState = Arm.P;
				}
			}
			if(event == Event.B4)
			{
				if(toteCount > 0)
				{
					mode = Mode.R;
					liftState = Lift.U;
				}
			}
			break;
		}
		
		if(increaseToteCount == true)
		{
			toteCount++;
			increaseToteCount = false;
		}
	}
	
	// Public functions to handle what to do
	
	public void loadTote()
	{
		// B3, AS, LRP, AS, LRP, done
		// B3 means I want to load a tote and put it onto the conveyor
		// Since arm is in picking up state, put it on the ground and set the motors on until
		// the light sensor is switched
		// AS means that the arm has successfully pulled in a tote
		// Move the lift up to position one (or the next position)
		// LRP is called when the forklift reaches that position (Subsystems.forklift.isLevelReached());
		// Turn botht the arm motors and conveyor motor to intake and push the tote anto the conveyor
		// AS means that the tote is no longer crossing the light sensor so the tote has been loaded onto the
		// conveyor
		// Put the lift back to the ground
		// LRP means that the lift has reached the ground
		// Stop everything and increase the tote count (should be done automatically)
		
		callEvent(Event.E_B3);
	}
	
	public void dropTotes()
	{
		
	}
	
	public void update()
	{
		switch(mode)
		{
		case M_A:
			break;
		case M_R:
			break;
		case M_N:
			break;
		}
	}
	
}