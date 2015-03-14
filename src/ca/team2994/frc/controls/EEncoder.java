package ca.team2994.frc.controls;

import edu.wpi.first.wpilibj.Encoder;

public class EEncoder extends Encoder {
	
	private int offset = 0;

	public EEncoder(int aChannel, int bChannel) {
		super(aChannel, bChannel);
	}
	
	public void reset(int offset) {
		this.offset = offset;
		super.reset();
	}
	
	public int get() {
		return super.get() + offset;
	}

}
