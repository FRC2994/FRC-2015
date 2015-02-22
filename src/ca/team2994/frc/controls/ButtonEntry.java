package ca.team2994.frc.controls;

public class ButtonEntry 
{
	// Event types
	public static final int EVENT_ERR = 0;
	public static final int EVENT_NONE = 1;
	public static final int EVENT_OPENED = 2;
	public static final int EVENT_CLOSED = 3;

	// State types
	public static final int STATE_OPEN = 0;
	public static final int STATE_CLOSED = 1;
	public static final int STATE_ERR = 2;
	
	private int state; 
	private int event;
	private boolean enabled;
	
	public int getState() 
	{
		return state;
	}
	
	public void setState(int state) 
	{
		this.state = state;
	}
	
	public int getEvent() 
	{
		return event;
	}
	
	public void setEvent(int event) 
	{
		this.event = event;
	}
	
	public boolean isEnabled() 
	{
		return enabled;
	}
	
	public void setEnabled(boolean enabled) 
	{
		this.enabled = enabled;
	}
}
