package ca.team2994.frc.robot;

import com.google.common.base.Strings;

import edu.wpi.first.wpilibj.Timer;

public class Bling {

	private boolean present = false;
	private boolean ready = false;
	
	public Bling() {
		if (Subsystems.blingPort != null) {
			present = true;
		}
	}
	
	public boolean isPresent() {
		return present;
	}
	
	public void init() {
		if (!present) {
			return;
		}
    	Subsystems.blingPort.writeString("X");
	}
	
	private boolean isReady() {
		if (!present) {
			return false;
		}		
    	if (!ready) {
    		String readyString = Subsystems.blingPort.readString();
    		if (!Strings.isNullOrEmpty(readyString) && (readyString.endsWith("Send a command"))) {
    			ready = true;
    		}
    	}
    	return ready;
	}
	
	/* OLD??? */
	public void simplePattern() {
		if (!present) {
			return;
		}
		if (isReady()) {
			Subsystems.blingPort.writeString("E1Z");	
		}
	}
	
	public int getBrightness() {
		if (!present) {
			return 0;
		}
		
    	Subsystems.driveJoystick.update();
    	
    	int brightness = (int)((Subsystems.driveJoystick.getZ() + 1.0) * 128.0) - 1;
    	if(brightness < 0) {
    		brightness = 0;
    	}
		return brightness;
	}
	
	public void prepSystemForCommand() {
		if (!present) {
			return;
		}

    	Subsystems.blingPort.writeString("I");
		
		Timer.delay(0.05);
		String readString = Subsystems.blingPort.readString();
		while(!readString.contains("R"))
		{
			System.out.println(readString);
			readString = Subsystems.blingPort.readString();
		}
	}
	
	// Example Colours: 16711680, 65280, 255, 16777215
	// Brightness use getBrightness
	public void doFunction6(String colour, String brightness) {
    	Subsystems.blingPort.writeString("F6");
    	Subsystems.blingPort.writeString("C" + colour);
    	Subsystems.blingPort.writeString("B" + brightness + "E6Z");
	}
}
