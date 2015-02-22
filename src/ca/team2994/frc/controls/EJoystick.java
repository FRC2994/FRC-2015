package ca.team2994.frc.controls;

import edu.wpi.first.wpilibj.Joystick;

public class EJoystick extends Joystick 
{
	public static final int EJOYSTICK_NUM_BUTTONS = 11;
	private ButtonEntry m_buttonTable[] = new ButtonEntry [EJOYSTICK_NUM_BUTTONS+1];

	public EJoystick(int port)
	{
		super(port);
		initialize();
	}

	public EJoystick(int port, int numAxisTypes, int numButtonTypes) 
	{
		super(port, numAxisTypes, numButtonTypes);
		initialize();
	}

	public int getEvent(int buttonNumber) 
	{
		if (buttonNumber < 1 || buttonNumber > EJOYSTICK_NUM_BUTTONS) 
		{
			return ButtonEntry.EVENT_ERR;
		}
		
		//Disabled buttons are initialized to kEventErr
		return m_buttonTable[buttonNumber].getEvent();
	}

	public int getState(int buttonNumber)
	{
		if (buttonNumber < 1 || buttonNumber > EJOYSTICK_NUM_BUTTONS) 
		{
			return ButtonEntry.STATE_ERR;
		}
		
		//Disabled buttons are initialized to kStateErr
		return m_buttonTable[buttonNumber].getState();
	}

	public void update() 
	{
		int newState;
		
		//redeclares all the buttons
		for (int i=1; i <= EJOYSTICK_NUM_BUTTONS; i++) 
		{
			if (m_buttonTable[i].isEnabled() == true) 
			{
				newState = getRawButton(i) ? ButtonEntry.STATE_CLOSED : ButtonEntry.STATE_OPEN;
				if (newState == m_buttonTable[i].getState())	
				{
					m_buttonTable[i].setEvent(ButtonEntry.EVENT_NONE);
				}
				else 
				{
					if (newState == ButtonEntry.STATE_CLOSED) 
					{
						m_buttonTable[i].setEvent(ButtonEntry.EVENT_CLOSED);
					} 
					else 
					{
						m_buttonTable[i].setEvent(ButtonEntry.EVENT_OPENED);
					}
					m_buttonTable[i].setState(newState);
				}
			}
		}
	}

	public boolean enableButton(int buttonNumber) 
	{
		if (buttonNumber < 1 || buttonNumber > EJOYSTICK_NUM_BUTTONS) 
		{
			return false;
		}
		
		m_buttonTable[buttonNumber].setEnabled(true);
		return true;
	}

	void initialize() 
	{
		m_buttonTable[0] = new ButtonEntry();
		m_buttonTable[0].setEnabled(false);
		m_buttonTable[0].setEvent(ButtonEntry.EVENT_ERR);
		m_buttonTable[0].setState(ButtonEntry.STATE_ERR);
		
		for (int i=1; i<=EJOYSTICK_NUM_BUTTONS; i++) 
		{
			m_buttonTable[i] = new ButtonEntry();
			m_buttonTable[i].setEnabled(false);
			m_buttonTable[i].setEvent(ButtonEntry.EVENT_ERR);
			m_buttonTable[i].setState(getRawButton(i) ? ButtonEntry.STATE_CLOSED : ButtonEntry.STATE_OPEN);
		}
	}
	
	
}
