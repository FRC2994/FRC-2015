package ca.team2994.frc.autonomous;

public abstract class AutoMode {
	
	private AutoCommand[] commands;
	private int commandIdx = 0;
	
	public void tick() {
		if (commandIdx < commands.length) {  //Still commands
			if (!commands[commandIdx].tick()) { //This command is done
				commands[commandIdx].cleanup(); // cleanup command
				commandIdx++; //increment index
				if (commandIdx < commands.length) { // still commands after incrementation
					commands[commandIdx].initialize(); // Init next command
				}
			}
		}
	}
	
	protected abstract AutoCommand[] initializeCommands();
	
	public void initialize() {
		commands = initializeCommands();
		if (commands != null && commands.length > 0) {
			commands[0].initialize();	
		}
	}
}
