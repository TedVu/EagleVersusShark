package model.board;

/**
 * @author ted &#38; kevin
 *
 */
public class Cell {

	private int x;
	private int y;
	private int ID;
	private boolean occupied = false;
	private boolean isWaterCell = false;
	private boolean isMasterCell = false;

	/**
	 * @param x
	 * @param y
	 */
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
		ID = Integer.parseInt((new Integer(x)).toString() + (new Integer(y)).toString());
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

	public boolean isWaterCell() {
		return isWaterCell;
	}

	public boolean isMasterCell() {
		return isMasterCell;
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

	public void setWaterCell() {
		isWaterCell = true;
	}

	public void setMasterCell() {
		isMasterCell = true;
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