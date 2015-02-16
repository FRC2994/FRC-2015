package ca.team2994.frc.robot;

public class GearShifter {
	//controlls compressor and gear shifting with solenoid
	private boolean highGear = false;
	public boolean compressorStatus = false;
	public boolean gearShiftStatus = false;
	
    public void gearShift() {
    	if(Subsystems.driveJoystick.getEvent(11) == ButtonEntry.EVENT_CLOSED) {
    		gearShiftStatus = true;
    	}
    	else if(Subsystems.driveJoystick.getEvent(10) == ButtonEntry.EVENT_CLOSED) {
    		gearShiftStatus = false;
    	}
    	if(gearShiftStatus == true) {
    	if(Subsystems.driveJoystick.getEvent(Constants.getConstantAsInt(Constants.JOYSTICK_GEAR_HIGH)) == ButtonEntry.EVENT_CLOSED) {
    		Subsystems.robotDrive.setHighGear();
    		highGear = true;
    	} else if(Subsystems.driveJoystick.getEvent(Constants.getConstantAsInt(Constants.JOYSTICK_GEAR_LOW)) == ButtonEntry.EVENT_CLOSED) {
    		Subsystems.robotDrive.setLowGear();
    		highGear =  false;
    	}
    	}
    }
    	
    
    public void compressorEnable() {
    if(Subsystems.driveJoystick.getEvent(9) == ButtonEntry.EVENT_CLOSED) {
    	compressorStatus = true;
    }
    else if(Subsystems.driveJoystick.getEvent(8) == ButtonEntry.EVENT_CLOSED) {
    	compressorStatus = false;
    }
    if(compressorStatus == true) {
    	Subsystems.compressor.setClosedLoopControl(true);
    }
    else if(compressorStatus == false) {
    	Subsystems.compressor.setClosedLoopControl(false);
    }
    
    }
}
