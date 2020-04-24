package model.piece;


public class PieceMemento {
	
	boolean isActive;
	boolean isImmune;
	int x, y;

	public PieceMemento(boolean isActive, boolean isImmune, int x, int y) {
		super();
		this.isActive = isActive;
		this.isImmune = isImmune;
		this.x = x;
		this.y = y;
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
	
	

}
