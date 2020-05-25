package model.piece;

import java.io.Serializable;

public class PieceMemento implements Serializable {

	private static final long serialVersionUID = -2210167835452781626L;
	
	boolean isActive;
	boolean isImmune;
	int x, y, modeUsageCount;

	public PieceMemento(boolean isActive, boolean isImmune, int x, int y, int modeUsageCount) {
		super();
		this.isActive = isActive;
		this.isImmune = isImmune;
		this.x = x;
		this.y = y;
		this.modeUsageCount = modeUsageCount;
	};

	public PieceMemento getState() {
		return this;
	}

	public boolean isActive() {
		return isActive;
	}

	public boolean isImmune() {
		return isImmune;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getModeCount() {
		return modeUsageCount;
	}

}
