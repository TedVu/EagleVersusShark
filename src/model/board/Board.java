package model.board;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ted &#38; kevin
 *
 */
public class Board {

	// Fix row and col for A1
	private int size = 9;
	private List<List<Cell>> cells;

	/**
	 * 
	 */
	public Board() {
		cells = new ArrayList<>();
		for (int row = 0; row < size; ++row) {
			cells.add(new ArrayList<Cell>());
			for (int col = 0; col < size; ++col) {
				cells.get(row).add(new Cell(col, row));
			}
		}
	}

	/**
	 * @param x
	 * @param y
	 */
	public void addPiece(int x, int y) {
		cells.get(y).get(x).setOccupied();
	}

	/**
	 * @return
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param x
	 * @param y
	 */
	public void removePiece(int x, int y) {
		cells.get(y).get(x).setUnoccupied();
	}

	public boolean getOccupationState(int x, int y) {
		return cells.get(y).get(x).getOccupied();
	}
}
