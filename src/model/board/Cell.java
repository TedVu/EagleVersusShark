package model.board;

/**
 * @author ted &#38; kevin
 *
 */
public class Cell {

	private int ID;
	private int x;
	private int y;
	private boolean occupied = false;

	/**
	 * @param x
	 * @param y
	 */
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
		Integer X = x;
		Integer Y = y;
		ID = Integer.parseInt(X.toString() + Y.toString());
	}

	/**
	 * @return
	 */
	public boolean getOccupied() {
		return occupied;
	}

	/**
	 * @return
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return
	 */
	public int getY() {
		return y;
	}

	/**
	 * 
	 */
	public void setOccupied() {
		occupied = true;
	}

	/**
	 * 
	 */
	public void setUnoccupied() {
		occupied = false;
	}

	@Override
	public String toString() {
		return String.format("X=%d Y=%d", x, y);
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Cell)) {
			return false;
		}
		if (this == o) {
			return true;
		}
		Cell cell = (Cell) o;
		return this.x == cell.getX() && this.getY() == cell.getY();
	}

	@Override
	public int hashCode() {
		return ID;
	}

}
