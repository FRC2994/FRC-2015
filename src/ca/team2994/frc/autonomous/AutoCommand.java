package ca.team2994.frc.autonomous;

public interface AutoCommand {
	public void initialize();
	public boolean tick();
	public void cleanup();
}
