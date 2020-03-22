package model.board;

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
		for (int i = 0; i < boardRow; ++i) {
			cells.add(new ArrayList<Cell>());
			for (int j = 0; j < boardCol; ++j) {
				cells.get(i).add(new Cell(i, j));
			}
		}
	}

	public void addPiece(int x, int y) {
		cells.get(x).get(y).setOccupied();
	}

	// Either from being captured or have made a new move
	public void removePiece(int oldX, int oldY) {
		cells.get(oldX).get(oldY).setUnoccupied();
	}

	// comprise of remove old position and add piece to new position
	public void move(int x, int y) {

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

		for (int i = 0; i < boardRow; ++i) {
			for (int j = 0; j < boardCol; ++j) {
				boardString.append(cells.get(i).get(j));
			}
			boardString.append("\n");
		}

		return new String(boardString);
	}

}
