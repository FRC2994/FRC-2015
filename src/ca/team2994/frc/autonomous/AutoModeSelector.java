package ca.team2994.frc.autonomous;

import java.util.ArrayList;
import java.util.List;

import ca.team2994.frc.autonomous.modes.BasicRobotSetMode;
import ca.team2994.frc.autonomous.modes.DoNothingMode;
import ca.team2994.frc.autonomous.modes.TestAutoMode;
import edu.wpi.first.wpilibj.DigitalInput;

public class AutoModeSelector {
	private static List<Class<? extends AutoMode>> modes = new ArrayList<>();
	
	static {
		//TODO: Add all autonomous modes here.
		modes.add(DoNothingMode.class);
		modes.add(TestAutoMode.class);
		modes.add(BasicRobotSetMode.class);
	}
	
	/**
	 * Selects a mode using the give array of DigitalInputs.
	 * 
	 * Treats inputs as an array of binary numbers. inputs[0] is treated
	 * as the least significant digit (2^0 = 1) and inputs[inputs.length-1] 
	 * is treated as the most significant digit (2^(inputs.length-1)).
	 * 
	 * @param inputs The list of inputs.
	 * @return The AutoMode to use.
	 */
	public AutoMode selectMode(DigitalInput[] inputs) {
		try {
			// Make a new instance of the value at the index from the binary!
			return (AutoMode)(modes.get(encodeSwitches(inputs)).newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public int encodeSwitches(DigitalInput[] inputs) {
		// Keeps the power of 2
		int power = 1;

		// Keeps the index into the modes array.
		int modeIndex = 0;
		for (int i = 0; i < inputs.length; i++) {
			if (!inputs[i].get()) {
				modeIndex += power; 
			}
			power *= 2;
		}

		// Do nothing mode
		if (modeIndex < modes.size()) {
			modeIndex = 0;
		}
		
		return modeIndex;
	}
}
