package ca.team2994.frc.robot;

public class GearShifter {
	
	private boolean highGear = false;
	
    public void gearShift() {
    	if(Subsystems.driveJoystick.getEvent(6) == ButtonEntry.EVENT_CLOSED) {
    		Subsystems.robotDrive.setHighGear();
    		highGear = true;
    	} else if(Subsystems.driveJoystick.getEvent(7) == ButtonEntry.EVENT_CLOSED) {
    		Subsystems.robotDrive.setLowGear();
    		highGear =  false;
    	}
    }
}
