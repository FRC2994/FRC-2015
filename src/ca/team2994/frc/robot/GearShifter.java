package ca.team2994.frc.robot;

public class GearShifter {
	//controlls compressor and gear shifting with solenoid
	private boolean highGear = false;
	public GearShifter() {
		
	}
    public void gearShift() {
    	if (Subsystems.gearShiftSolenoid == null) {
    		return;
    	}
    	if(Subsystems.driveJoystick.getEvent(Constants.getConstantAsInt(Constants.JOYSTICK_GEAR_HIGH)) == ButtonEntry.EVENT_CLOSED) {
    		Subsystems.robotDrive.setHighGear();
    		highGear = true;
    	} else if(Subsystems.driveJoystick.getEvent(Constants.getConstantAsInt(Constants.JOYSTICK_GEAR_LOW)) == ButtonEntry.EVENT_CLOSED) {
    		Subsystems.robotDrive.setLowGear();
    		highGear =  false;
    	}
    }

    public boolean isHighGear() {
    	return highGear;
    }

}
