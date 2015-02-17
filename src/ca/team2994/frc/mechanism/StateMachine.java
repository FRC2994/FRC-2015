package ca.team2994.frc.mechanism;

import ca.team2994.frc.robot.Subsystems;

/**
 * 
 * @author Tylar aka Mr.Perfect
 *
 */
public class StateMachine
{
	
	private static enum Mode
	{
		/** Add */
		A,
		/** Remove */
		R,
		/** None */
		N
	}
	
	private static enum Arm
	{
		/** Picking up */
		P,
		/** Dropping off */
		D,
		/** Loading */
		L,
		/** Unloading */
		U,
		/** Stopped */
		S
	}
	
	private static enum Conveyor
	{
		/** Loading */
		L,
		/** Unloading */
		U,
		/** Stopped */
		S
	}
	
	private static enum Lift
	{
		/** Stopped */
		S,
		/** Up */
		U,
		/** Down */
		D
	}
	
	public static enum Event
	{
		/** Lift reaches position */
		LRP,
		/** Arm stops */
		AS,
		/** Conveyor stops */
		CS,
		/** Load a tote */
		B3,
		/** Unload a tote (all totes) */
		B4
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
	
	public void callEvent(Event event)
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
		
		callEvent(Event.B3);
	}
	
	public void dropTotes()
	{
		// TODO: Add description here
		callEvent(Event.B4);
	}
	
	public void update()
	{
		if(Subsystems.forklift.isLevelReached())
		{
			callEvent(Event.LRP);
		}
		
//		if(Subsystems.robotArm.isDone())
//		{
//			callEvent(Event.AS);
//		}
		
		switch(armState)
		{
		case P:
			Subsystems.robotArm.pickup();
			break;
		case D:
			Subsystems.robotArm.dropoff();
			break;
		case L:
			Subsystems.robotArm.load();
			break;
		case U:
			Subsystems.robotArm.unload();
			break;
		case S:
			Subsystems.robotArm.stop();
			break;
		}
		
		switch(conveyorState)
		{
		case L:
			Subsystems.conveyor.load();
			 break;
		case U:
			Subsystems.conveyor.unload();
			break;
		case S:
			Subsystems.conveyor.stop();
			break;
		}
		
		switch(liftState)
		{
		case U:
			Subsystems.forklift.up(toteCount);
			break;
		case D:
			Subsystems.forklift.down(0);
			break;
		case S:
			Subsystems.forklift.stop();
			break;
		}
		
//		switch(mode)
//		{
//		case A:
//			
//			switch(armState)
//			{
//			case P:
//				Subsystems.robotArm.pickup();
//				break;
//			case L:
//				Subsystems.robotArm.load();
//				break;
//			case S:
//				// TODO: THIS HAPPENS TWICE!!!!
//				if(liftState == Lift.U)
//				{
//					
//				}
//				else if(liftState == Lift.D)
//				{
//					
//				}
//				
//				break;
//			default:
//				break;
//			}
//			
//			break;
//			
//		case R:
//			
//			switch(armState)
//			{
//			case U:
//				Subsystems.robotArm.unload(); // TODO: Verify that this has been changed to call the stop() function
//				break;
//			case D:
//				Subsystems.robotArm.dropoff(); // TODO: Verify that this has been changed to call the stop() function
//				break;
//			case S:
//				// TODO: THIS HAPPENS TWICE!!!!
//				break;
//			default:
//				break;
//			}
//			
//			break;
//			
//		case N:
//			armState = Arm.S;
//			conveyorState = Conveyor.S;
//			liftState = Lift.S;
//			break;
//			
//		}
	}
	
}