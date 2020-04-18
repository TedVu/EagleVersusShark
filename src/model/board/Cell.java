package model.board;

/**
 * @author ted &#38; kevin
 *
 */
public class Cell {

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
}
