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
		M_A, 						// Add
		M_R, 						// Remove
		M_N 						// None
	}
	
	private static enum Arm
	{
		A_P, 						// Picking up
		A_D, 						// Dropping off
		A_L, 						// Loading
		A_U, 						// Unloading
		A_S 						// Stopped
	}
	
	private static enum Conveyor
	{
		C_L, 						// Loading
		C_U, 						// Unloading
		C_S							// Stopped
	}
	
	private static enum Lift
	{
		L_S, 						// Stopped
		L_U, 						// Up
		L_D 						// Down
	}
	
	public static enum Event
	{
		E_LRP,						// Lift reaches position
		E_AS,						// Arm stops
		E_CS, 						// Conveyor stops (Deprecated)
		E_B3, 						// Load a tote and put it on the conveyor
		E_B4 						// Unload a tote
	}
	
	private Mode mode;
	private Arm armState;
	private Conveyor conveyorState;
	private Lift liftState;
	private int toteCount;
	private boolean increaseToteCount = false;
	
	public StateMachine()
	{
		mode = Mode.M_N;
		armState = Arm.A_S;
		conveyorState = Conveyor.C_S;
		liftState = Lift.L_S;
		toteCount = 0;
	}
	
	public int getToteCount()
	{
		return toteCount;
	}
	
	public String getStateCode()
	{
		String stateCode = "";
		
		switch(mode)
		{
		case M_A:
			stateCode += "A";
			break;
		case M_R:
			stateCode += "R";
			break;
		case M_N:
			stateCode += "N";
			break;
		default:
			stateCode += "X";
			break;
		}
		
		switch(armState)
		{
		case A_P:
			stateCode += "P";
			break;
		case A_D:
			stateCode += "D";
			break;
		case A_L:
			stateCode += "L";
			break;
		case A_U:
			stateCode += "U";
			break;
		case A_S:
			stateCode += "S";
			break;
		default:
			stateCode += "X";
			break;
		}
		
		switch(conveyorState)
		{
		case C_L:
			stateCode += "L";
			break;
		case C_U:
			stateCode += "U";
			break;
		case C_S:
			stateCode += "S";
			break;
		default:
			stateCode += "X";
			break;
		}
		
		switch(liftState)
		{
		case L_S:
			stateCode += "S";
			break;
		case L_U:
			stateCode += "U";
			break;
		case L_D:
			stateCode += "D";
			break;
		default:
			stateCode += "X";
			break;
		}
		
		stateCode += toteCount;
		
		return stateCode;
	}
	
	public void callEvent(Event event)
	{
		switch(mode)
		{
		case M_A:
			if(toteCount < 3)
			{
				if(event == Event.E_AS)
				{
					if(armState == Arm.A_P)
					{
						armState = Arm.A_S;
						liftState = Lift.L_U;
					}
					else if(armState == Arm.A_L)
					{
						armState = Arm.A_S;
						conveyorState = Conveyor.C_S;
						liftState = Lift.L_D;
					}
				}
				else if(event == Event.E_LRP)
				{
					if(liftState == Lift.L_U)
					{
						armState = Arm.A_L;
						conveyorState = Conveyor.C_L;
						liftState = Lift.L_S;
					}
					else if(liftState == Lift.L_D)
					{
						// Everything is done at this point
						mode = Mode.M_N;
						liftState = Lift.L_S;
						increaseToteCount = true;
					}
				}
			}
			break;
			
			
			
			
		case M_R:
			if(event == Event.E_LRP)
			{
				if(liftState == Lift.L_U)
				{
					armState = Arm.A_U;
					conveyorState = Conveyor.C_U;
					liftState = Lift.L_S;
				}
				else if(liftState == Lift.L_D)
				{
					armState = Arm.A_D;
					conveyorState = Conveyor.C_S;
					liftState = Lift.L_S;
				}
			}
			else if(event == Event.E_AS)
			{
				if(armState == Arm.A_U)
				{
					armState = Arm.A_S;
					conveyorState = Conveyor.C_S;
					liftState = Lift.L_D;
				}
				else if(armState == Arm.A_D)
				{
					mode = Mode.M_N;
					armState = Arm.A_S;
					// Assume that all totes are being removed as opposed to just one
					toteCount = 0;
				}
			}
			break;
			
			
			
			
			
		case M_N:
			if(toteCount < 3)
			{
				if(event == Event.E_B3)
				{
					mode = Mode.M_A;
					armState = Arm.A_P;
				}
			}
			if(event == Event.E_B4)
			{
				if(toteCount > 0)
				{
					mode = Mode.M_R;
					liftState = Lift.L_U;
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
}