package ca.team2994.frc.robot;

public class TEST_EControlers 
{
	public static void testEGamepad()
	{
		EGamepad buttons = new EGamepad(3);
		
		for(int i = 1; i<= 12; i++)
		{
			System.out.println("Gamepad Key "+i+": "+ buttons.getState(i) +", event: "+ buttons.getEvent(i));
		}
		for(int i = 1; i <= 8; i++)
		{
			System.out.println("DPad key"+i+": "+buttons.getDPadState(i)+", event: "+ buttons.getDPadEvent(i));
		}
	}

	public static void testEJoystick()
	{
		EJoystick joy = new EJoystick(1);
		
		for(int i = 1; i <= 11; i++)
		{
			System.out.println("Joystick Key "+i+": "+ joy.getState(i) +", event: "+ joy.getEvent(i));
		}
	}
	
}
