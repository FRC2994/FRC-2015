package ca.team2994.frc.robot;

import org.junit.Test;

import ca.team2994.frc.mechanism.StateMachine;
import ca.team2994.frc.mechanism.StateMachine.Event;

public class StateMachineTest
{
	public static StateMachine stateMachine;
	
	@Test
	public void stateCodeTest()
	{
		stateMachine = new StateMachine();
		
		printCode();
		
		loadTote();
		System.out.println(stateMachine.getToteCount());
		loadTote();
		System.out.println(stateMachine.getToteCount());
		loadTote();
		System.out.println(stateMachine.getToteCount());
		
		System.out.println();
		printCode();
		stateMachine.CallEvent(Event.E_B4);
		printCode();
		stateMachine.CallEvent(Event.E_LRP);
		printCode();
		stateMachine.CallEvent(Event.E_AS);
		printCode();
		stateMachine.CallEvent(Event.E_LRP);
		printCode();
		stateMachine.CallEvent(Event.E_AS);
		printCode();
	}
	
	public void printCode()
	{
		System.out.println(stateMachine.getStateCode());
	}
	
	public void loadTote()
	{
		firstStep();
		secondStep();
		thirdStep();
		fourthStep();
		fifthStep();
	}
	
	public void firstStep()
	{
		// Test to make sure that you can only call the load tote event when there are currently no totes
		stateMachine.CallEvent(Event.E_CS);
		printCode();
		stateMachine.CallEvent(Event.E_AS);
		printCode();
		stateMachine.CallEvent(Event.E_LRP);
		printCode();
		stateMachine.CallEvent(Event.E_B3);
		printCode();
		stateMachine.CallEvent(Event.E_B4);
		printCode();
		
		System.out.println();
	}
	
	public void secondStep()
	{
		stateMachine.CallEvent(Event.E_CS);
		printCode();
		stateMachine.CallEvent(Event.E_LRP);
		printCode();
		stateMachine.CallEvent(Event.E_B3);
		printCode();
		stateMachine.CallEvent(Event.E_B4);
		printCode();
		stateMachine.CallEvent(Event.E_AS);
		printCode();
		
		System.out.println();
	}
	
	public void thirdStep()
	{
		stateMachine.CallEvent(Event.E_CS);
		printCode();
		stateMachine.CallEvent(Event.E_B3);
		printCode();
		stateMachine.CallEvent(Event.E_B4);
		printCode();
		stateMachine.CallEvent(Event.E_AS);
		printCode();
		stateMachine.CallEvent(Event.E_LRP);
		printCode();
		
		System.out.println();
	}
	
	public void fourthStep()
	{
		stateMachine.CallEvent(Event.E_CS);
		printCode();
		stateMachine.CallEvent(Event.E_LRP);
		printCode();
		stateMachine.CallEvent(Event.E_B3);
		printCode();
		stateMachine.CallEvent(Event.E_B4);
		printCode();
		stateMachine.CallEvent(Event.E_AS);
		printCode();
		
		System.out.println();
	}
	
	public void fifthStep()
	{
		stateMachine.CallEvent(Event.E_CS);
		printCode();
		stateMachine.CallEvent(Event.E_B3);
		printCode();
		stateMachine.CallEvent(Event.E_B4);
		printCode();
		stateMachine.CallEvent(Event.E_AS);
		printCode();
		stateMachine.CallEvent(Event.E_LRP);
		printCode();
		
		System.out.println();
	}
	
}