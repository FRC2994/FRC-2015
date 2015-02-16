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
		stateMachine.callEvent(Event.B3);
		printCode();
		stateMachine.callEvent(Event.AS);
		printCode();
		stateMachine.callEvent(Event.LRP);
		printCode();
		stateMachine.callEvent(Event.AS);
		printCode();
		stateMachine.callEvent(Event.LRP);
		printCode();
	}
	
	public void dropTotes()
	{
		stateMachine.callEvent(Event.B4);
		printCode();
		stateMachine.callEvent(Event.LRP);
		printCode();
		stateMachine.callEvent(Event.AS);
		printCode();
		stateMachine.callEvent(Event.LRP);
		printCode();
		stateMachine.callEvent(Event.AS);
		printCode();
	}
	
}