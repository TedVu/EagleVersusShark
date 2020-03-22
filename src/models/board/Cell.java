package models.board;

public class Cell {

	private boolean occupied = false;
	private int x;
	private int y;

	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean getOccupied() {
		return occupied;
	}

	public void setOccupied() {
		occupied = true;
	}

	public void setUnoccupied() {
		occupied = false;
	}

	@Override
	public String toString() {
		return String.format("%s\t", occupied == true ? "OC" : Integer.toString(x) + Integer.toString(y));
	}
}
