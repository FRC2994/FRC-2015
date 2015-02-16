package ca.team2994.frc.robot;

public class GearShifter {
	//controlls compressor and gear shifting with solenoid
	private static boolean highGear = false;
	public static boolean compressorStatus = false;
	public static boolean gearShiftStatus = false;
	public static boolean solenoidStatus = false; 
    public static void gearShift() {
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
    	
    
    public static void compressorEnable() {
    if(Subsystems.driveJoystick.getEvent(Constants.getConstantAsInt(Constants.JOYSTICK_COMPRESSOR_ENABLE)) == ButtonEntry.EVENT_CLOSED) {
    	compressorStatus = true;
    }
    else if(Subsystems.driveJoystick.getEvent(Constants.getConstantAsInt(Constants.JOYSTICK_COMPRESSOR_DISABLE)) == ButtonEntry.EVENT_CLOSED) {
    	compressorStatus = false;
    }
    if(compressorStatus == true) {
    	Subsystems.compressor.setClosedLoopControl(true);
    }
    else if(compressorStatus == false) {
    	Subsystems.compressor.setClosedLoopControl(false);
    }
    
    }
    
    public boolean isHighGear() {
    	return highGear;
    }
}
