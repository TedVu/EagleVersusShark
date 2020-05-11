package model.board;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;

/**
 * @author ted &#38; kevin
 *
 */
public class Board implements Serializable {

	private static final long serialVersionUID = 5510738805489933149L;
	// Fix row and col for A1
	private int size;
	private List<List<Cell>> cells;
	private Cell sharkMasterCell = null;

	/**
	 * 
	 */

	/**
	 * Return shark's master cell
	 * 
	 * @author Chanboth
	 */
	public Cell getSharkMasterCell() {
		return this.sharkMasterCell;
	}

	public Board(int boardSize) {
		size = boardSize;
		cells = new ArrayList<>();
		for (int row = 0; row < size; ++row) {
			cells.add(new ArrayList<Cell>());
			for (int col = 0; col < size; ++col) {
				cells.get(row).add(new Cell(col, row));
			}
		}

		// water cell
		int mid = (cells.size() - 1) / 2;
		for (int row = mid - 1; row <= mid + 1; ++row) {
			for (int col = 0; col < size; ++col) {
				cells.get(row).get(col).setWaterCell();
			}
		}

		cells.get(0).get(mid).setMasterCell();
		cells.get(size - 1).get(mid).setMasterCell();

	}
 
	/**
	 * @param x
	 * @param y
	 */
	@Requires({ "x>=0", "y>=0" })
	@Ensures("cells.get(y).get(x).getOccupied()==true")
	public void addPiece(int x, int y) {
		cells.get(y).get(x).setOccupied();
	}

	@Requires({ "x>=0", "y>=0" })
	public boolean getOccupationState(int x, int y) {
		return cells.get(y).get(x).getOccupied();
	}

	/**
	 * @return
	 */
	public int getSize() {
		return size;
	}

	public Cell getCell(int x, int y) {
		return cells.get(y).get(x);
	}

	/**
	 * @param x
	 * @param y
	 */
	@Requires({ "x>=0", "y>=0" })
	@Ensures("cells.get(y).get(x).getOccupied()==false")
	public void removePiece(int x, int y) {
		cells.get(y).get(x).setUnoccupied();
	}
}
