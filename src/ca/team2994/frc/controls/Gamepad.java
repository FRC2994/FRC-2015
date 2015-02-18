package ca.team2994.frc.controls;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;

/**
 * Handle input from Logitech Dual Action Gamepad connected to the Driver
 * Station.
 */
public class Gamepad extends Joystick {

	// Axis Type
	public static final int AXIS_TYPE_LEFT_X_AXIS = 0;
	public static final int AXIS_TYPE_LEFT_Y_AXIS = 1;
	public static final int AXIS_TYPE_RIGHT_X_AXIS = 2;
	public static final int AXIS_TYPE_RIGHT_Y_AXIS = 3;
	// DPad Direction
	public static final int DPAD_DIRECTION_CENTER = 0;
	public static final int DPAD_DIRECTION_UP = 1;
	public static final int DPAD_DIRECTION_UP_LEFT = 2;
	public static final int DPAD_DIRECTION_LEFT = 3;
	public static final int DPAD_DIRECTION_DOWN_LEFT = 4;
	public static final int DPAD_DIRECTION_DOWN = 5;
	public static final int DPAD_DIRECTION_DOWN_RIGHT = 6;
	public static final int DPAD_DIRECTION_RIGHT = 7;
	public static final int DPAD_DIRECTION_UP_RIGHT = 8;
	// Axis Numbers
	public static final int AXIS_NUM_LEFT_X = 0;
	public static final int AXIS_NUM_LEFT_Y = 1;
	public static final int AXIS_NUM_RIGHT_X = 2;
	public static final int AXIS_NUM_RIGHT_Y = 3;
	public static final int AXIS_NUM_DPAD_X = 4;
	public static final int AXIS_NUM_DPAD_Y = 5;
	// Stick Button
	public static final int STICK_BUTTON_LEFT_ANALOG = 11;
	public static final int STICK_BUTTON_RIGHT_ANALOG = 12;

    private DriverStation ap_ds;
    private int a_port;

    /**
     * Construct an instance of a Gamepad.
     *
     * @param port The port on the driver station that the gamepad is plugged into.
     */
    public Gamepad(int port) {
		super(port);
		a_port = port;
		ap_ds = DriverStation.getInstance();
	}
    
    /**
     * Get the X value of the left analog stick.
     */
    public double getLeftX() {
        return getRawAxis(AXIS_NUM_LEFT_X);
    }

    /**
     * Get the Y value of the left analog stick.
     */
    public double getLeftY() {
        return getRawAxis(AXIS_NUM_LEFT_Y);
    }

    /**
     * Get the X value of the right analog stick.
     */
    public double getRightX() {
        return getRawAxis(AXIS_NUM_RIGHT_X);
    }

    /**
     * Get the Y value of the right analog stick.
     */
    public double getRightY() {
        return getRawAxis(AXIS_NUM_RIGHT_Y);
    }

    /**
     * Get the value of the axis.
     *
     * @param axis The axis to read [1-6].
     * @return The value of the axis.
     */
    public double getRawAxis(int axis) {
        return ap_ds.getStickAxis(a_port, axis);
    }

    /**
     * Return the axis determined by the argument.
     *
     * This is for cases where the gamepad axis is returned programatically,
     * otherwise one of the previous functions would be preferable (for example
     * GetLeftX()).
     *
     * @param axis The axis to read.
     * @return The value of the axis.
     */
    public double getAxis(int axis) {
        switch(axis) {
            case AXIS_TYPE_LEFT_X_AXIS: 
            	return getLeftX();
            case AXIS_TYPE_LEFT_Y_AXIS: 
            	return getLeftY();
            case AXIS_TYPE_RIGHT_X_AXIS: 
            	return getRightX();
            case AXIS_TYPE_RIGHT_Y_AXIS: 
            	return getRightY();
            default:
                return 0.0;
        }
    }

   
    /**
     * Get the button value for buttons 1 through 12.
     *
     * The buttons are returned in a single 16 bit value with one bit representing
     * the state of each button. The appropriate button is returned as a boolean
     * value.
     *
     * @param button The button number to be read.
     * @return The state of the button.
     **/
    public boolean getNumberedButton(int button) {
        return ((0x1 << (button-1)) & ap_ds.getStickButtons(a_port)) != 0;
    }

    /**
     * Gets whether or not the left analog stick is depressed.
     *
     * @return The state of the left analog stick button.
     */
    public boolean getLeftPush() {
        return getNumberedButton(STICK_BUTTON_LEFT_ANALOG);
    }

    /**
     * Gets whether or not the right analog stick is depressed.
     *
     * @return The state of the right analog stick button.
     */
    public boolean getRightPush() {
        return getNumberedButton(STICK_BUTTON_RIGHT_ANALOG);
    }
    
    public int getDPad() {
    int	dPad = getPOV();
    	if(dPad == 0) {
    		return DPAD_DIRECTION_UP;
    	}
    	if(dPad == 45) {
    		return DPAD_DIRECTION_UP_RIGHT;
    	}
    	if(dPad == 90) {
    		return DPAD_DIRECTION_RIGHT;
    	}
    	if(dPad == 135) {
    		return DPAD_DIRECTION_DOWN_RIGHT;
    	}
    	if(dPad == 180) {
    		return DPAD_DIRECTION_DOWN;
    	}
    	if(dPad == 225) {
    		return DPAD_DIRECTION_DOWN_LEFT;
    	}
    	if(dPad == 270) {
    		return DPAD_DIRECTION_LEFT;
    	}
    	if(dPad == 315) {
    		return DPAD_DIRECTION_UP_LEFT;
    	}
    	else {
    		return DPAD_DIRECTION_CENTER;
    	}
    }
}
