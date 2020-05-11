package model.board;

import java.io.Serializable;

/**
 * @author ted &#38; kevin
 *
 */
public class Cell implements Serializable {

	private static final long serialVersionUID = -906734890234310442L;
	private int x;
	private int y;
	private int ID;
	private boolean occupied = false;
	private boolean isWaterCell = false;
	private boolean isMasterCell = false;
	private boolean isObstacle = false;

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

	public void setObstacle() {
		occupied = true;
		isObstacle = true;
	}

	public boolean isObstacle() {
		return isObstacle;
	}

	@Override
	public String toString() {
		return String.format("X=%d Y=%d", x, y);
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
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
