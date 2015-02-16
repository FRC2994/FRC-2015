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
		
		System.out.println("One tote");
		loadTote();
		System.out.println(stateMachine.getToteCount());
		dropTotes();
		System.out.println(stateMachine.getToteCount());
		System.out.println();
		
		System.out.println("Two totes");
		loadTote();
		System.out.println(stateMachine.getToteCount());
		loadTote();
		System.out.println(stateMachine.getToteCount());
		dropTotes();
		System.out.println(stateMachine.getToteCount());
		System.out.println();
		
		System.out.println("Three totes");
		loadTote();
		System.out.println(stateMachine.getToteCount());
		loadTote();
		System.out.println(stateMachine.getToteCount());
		loadTote();
		System.out.println(stateMachine.getToteCount());
		dropTotes();
		System.out.println(stateMachine.getToteCount());
	}
	
	public void printCode()
	{
		System.out.println(stateMachine.getStateCode());
	}
	
	public void loadTote()
	{
		stateMachine.CallEvent(Event.E_B3);
		printCode();
		stateMachine.CallEvent(Event.E_AS);
		printCode();
		stateMachine.CallEvent(Event.E_LRP);
		printCode();
		stateMachine.CallEvent(Event.E_AS);
		printCode();
		stateMachine.CallEvent(Event.E_LRP);
		printCode();
	}
	
	public void dropTotes()
	{
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
	
}