package ca.team2994.frc.controls;


//This class extends the Gamepad class to provide button event detection

//To use, call EnableButton once to enable tracking of the button
//you want, call the Update method once before you enter your main program
//loop, and then call the Update method once per loop. At any point in
//your program loop you can check the state of the button using GetState
//and check for a state change (an "event" closed->open or open->closd)
//using GetEvent.
public class EGamepad extends Gamepad 
{
	public static final int EGAMEPAD_NUM_BUTTONS = 12;
	
	private ButtonEntry[] m_buttonTable = new ButtonEntry[EGAMEPAD_NUM_BUTTONS + 1];
	private ButtonEntry[] m_dpadTable = new ButtonEntry[Gamepad.DPAD_DIRECTION_UP_RIGHT + 1];

	public EGamepad(int port) 
	{
		super(port);
		initialize();
	}

	public int getEvent(int buttonNumber)
	{
		if (buttonNumber < 1 || buttonNumber > EGAMEPAD_NUM_BUTTONS) 
		{
			return ButtonEntry.EVENT_ERR;
		}
		
		// Disabled buttons are initialized to kEventErr
		return m_buttonTable[buttonNumber].getEvent();
	}

	public int getState(int buttonNumber) 
	{
		if (buttonNumber < 1 || buttonNumber > EGAMEPAD_NUM_BUTTONS) 
		{
			return ButtonEntry.STATE_ERR;
		}
		
		// Disabled buttons are initialized to kStateErr
		return m_buttonTable[buttonNumber].getState();
	}

	public int getDPadEvent(int direction) 
	{
		return m_dpadTable[direction].getEvent();
	}

	public int getDPadState(int direction) 
	{
		return m_dpadTable[direction].getState();
	}

	public void update()
	{
		int newState;
		
		for (int i=1; i<=EGAMEPAD_NUM_BUTTONS; i++) 
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
		
		int dpadDirection = getDPad();
		
		// By definition, only one contact on the dpad, at most, can be pressed at
		// any one time. So, we loop through the table using a newState of "open"
		// for every entry except the one (if any) that is being pressed. Note that
		// a kCenter direction is equivalent to nothing pressed.
		
		for (int i=Gamepad.DPAD_DIRECTION_CENTER; i<=Gamepad.DPAD_DIRECTION_UP_RIGHT; i++)
		{
			if (i == dpadDirection) 
			{
				newState = ButtonEntry.STATE_CLOSED;
			} 
			else 
			{
				newState = ButtonEntry.STATE_OPEN;
			}
			
			if (newState == m_dpadTable[i].getState()) 
			{
				m_dpadTable[i].setEvent(ButtonEntry.EVENT_NONE);
			}
			else 
			{
				if (newState == ButtonEntry.STATE_CLOSED) 
				{
					m_dpadTable[i].setEvent(ButtonEntry.EVENT_CLOSED);
				} 
				else 
				{
					m_dpadTable[i].setEvent(ButtonEntry.EVENT_OPENED);
				}
				m_dpadTable[i].setState(newState);
			}
		}
	}
	
	public boolean enableButton(int buttonNumber) 
	{
		if (buttonNumber < 1 || buttonNumber > EGAMEPAD_NUM_BUTTONS) 
		{
			return false;
		}
		
		m_buttonTable[buttonNumber].setEnabled(true);
		return true;
	}

	private void initialize() 
	{
		m_buttonTable[0] = new ButtonEntry();
		m_buttonTable[0].setEnabled(false);
		m_buttonTable[0].setEvent(ButtonEntry.EVENT_ERR);
		m_buttonTable[0].setState(ButtonEntry.STATE_ERR);
		
		for (int i=1; i<=EGAMEPAD_NUM_BUTTONS; i++)	
		{
			m_buttonTable[i] = new ButtonEntry();
			m_buttonTable[i].setEnabled(false);
			m_buttonTable[i].setEvent(ButtonEntry.EVENT_ERR);
			m_buttonTable[i].setState(getRawButton(i) ? ButtonEntry.EVENT_CLOSED : ButtonEntry.EVENT_OPENED);
		}
		
		// Only one DPad direction can be closed at any one time (kCenter is returned 
		// when no direction is closed). We initialize all the directions to open then
		// read the DPad and set closed the direction read (which will be, unless someone
		// is messing with the DPad at init time, kCentre)
		
		for (int i=Gamepad.DPAD_DIRECTION_CENTER; i<=Gamepad.DPAD_DIRECTION_UP_RIGHT; i++) 
		{
			m_dpadTable[i] = new ButtonEntry();
			m_dpadTable[i].setEnabled(false); // We never check this
			m_dpadTable[i].setEvent(ButtonEntry.EVENT_NONE);
			m_dpadTable[i].setState(ButtonEntry.STATE_OPEN);
		}
		
		m_dpadTable[getDPad()].setState(ButtonEntry.STATE_CLOSED);
	}

}
