package models.board;

import java.util.ArrayList;
import java.util.List;

public class Board {

	// Fix row and col for A1
	public int boardSize = 81;
	public int boardRow = 9;
	public int boardCol = 9;

	private List<List<Cell>> cells;

	public Board() {
		cells = new ArrayList<>();
		for (int row = 0; row < boardRow; ++row) {
			cells.add(new ArrayList<Cell>());
			for (int col = 0; col < boardCol; ++col) {
				cells.get(row).add(new Cell(col, row));
			}
		}
	}

	public void addPiece(int x, int y) {
		cells.get(y).get(x).setOccupied();
	}

	// Either from being captured or have made a new move
	public void removePiece(int oldX, int oldY) {
		cells.get(oldY).get(oldX).setUnoccupied();
	}

	public int getBoardSize() {
		return boardSize;
	}

	public int getRow() {
		return boardRow;
	}

	public int getCol() {
		return boardCol;
	}

	@Override
	public String toString() {
		StringBuffer boardString = new StringBuffer("");

		for (int row = 0; row < boardRow; ++row) {
			for (int col = 0; col < boardCol; ++col) {
				boardString.append(cells.get(row).get(col));
			}
			boardString.append("\n");
		}

		return new String(boardString);
	}

}
