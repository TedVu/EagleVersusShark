package models.board;

import java.util.ArrayList;
import java.util.List;

public class Board {

	// Fix row and col for A1
	private int size = 9;
	private List<List<Cell>> cells;

	public Board() {
		cells = new ArrayList<>();
		for (int row = 0; row < size; ++row) {
			cells.add(new ArrayList<Cell>());
			for (int col = 0; col < size; ++col) {
				cells.get(row).add(new Cell(col, row));
			}
		}
	}

	public Board(int size) {
		this.size = size;
		cells = new ArrayList<>();
		for (int row = 0; row < this.size; ++row) {
			cells.add(new ArrayList<Cell>());
			for (int col = 0; col < this.size; ++col) {
				cells.get(row).add(new Cell(col, row));
			}
		}
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public String toString() {
		StringBuffer boardString = new StringBuffer("");
		for (int row = 0; row < size; ++row) {
			for (int col = 0; col < size; ++col) {
				boardString.append(cells.get(row).get(col));
			}
			boardString.append("\n");
		}
		return new String(boardString);
	}

	public void addPiece(int x, int y) {
		cells.get(y).get(x).setOccupied();
	}

	// Either from being captured or have made a new move
	public void removePiece(int oldX, int oldY) {
		cells.get(oldY).get(oldX).setUnoccupied();
	}
}
