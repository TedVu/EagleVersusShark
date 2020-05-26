package model.board;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;

import model.enumtype.CellType;
import model.enumtype.PieceType;

/**
 * @author ted &#38; kevin
 *
 */
public class GameBoard implements Serializable {

	private static final long serialVersionUID = 5510738805489933149L;

	private int size;
	private List<List<Cell>> cells;
	private Set<Cell> waterCells;
	private Set<Cell> specialPos;

	private Cell sharkMasterCell = null;
	private Cell eagleMasterCell = null;

	/**
	 * Create the board, set master cells and water cells.
	 * 
	 * @param boardSize
	 */
	public GameBoard(int boardSize, boolean hasObstacle) {
		size = boardSize;
		cells = new ArrayList<>();
		waterCells = new HashSet<>();

		specialPos = new HashSet<>();
		for (PieceType pieces : PieceType.values()) {
			specialPos.add(new Cell(pieces.xCoordinate(size), pieces.yCoordinate(size)));
		}

		int mid = (size - 1) / 2;
		for (int row = 0; row < size; ++row) {
			cells.add(new ArrayList<Cell>());
			for (int col = 0; col < size; ++col) {
				if (col == mid && row == 0) {
					eagleMasterCell = new Cell(col, row, CellType.EMASTER);
					specialPos.add(eagleMasterCell);
					cells.get(row).add(eagleMasterCell);
				} else if (col == mid && row == size - 1) {
					sharkMasterCell = new Cell(col, row, CellType.SMASTER);
					specialPos.add(sharkMasterCell);
					cells.get(row).add(sharkMasterCell);
				} else if (row == mid - 1 || row == mid || row == mid + 1) {
					Cell cell = new Cell(col, row, CellType.WATER);
					cells.get(row).add(cell);
					waterCells.add(cell);
				} else {
					cells.get(row).add(new Cell(col, row, CellType.NORMAL));
				}
			}
		}

		if (hasObstacle) {
			configObstacle();
		}
	}

	/**
	 * Set the cell to occupied after moving the piece.
	 * 
	 * @param x row
	 * @param y col
	 */
	@Requires({ "x>=0", "y>=0" })
	@Ensures("cells.get(y).get(x).getOccupied()==true")
	public void addPiece(int x, int y) {
		cells.get(y).get(x).setOccupied();
	}

	/**
	 * Create Obstacle Cells
	 */
	public void configObstacle() {
		int min = 0;
		int numObstacle = 0;

		while (numObstacle < 5) {
			int randomX = ThreadLocalRandom.current().nextInt(min, size);
			int randomY = ThreadLocalRandom.current().nextInt(min, size);

			if (!specialPos.contains(getCell(randomX, randomY))
					&& !(getCell(randomX, randomY).getType() == CellType.OBSTACLE)) {
				getCell(randomX, randomY).setType(CellType.OBSTACLE);
				getCell(randomX, randomY).setOccupied();
				++numObstacle;
			}
		}
	}

	public Cell getAvailableTopEagleSideCell() {
		Set<Cell> topEagleSideCells = new HashSet<>();
		Cell availableCell = null;

		for (int i = 0; i < size; ++i) {
			topEagleSideCells.add(this.getCell(i, 0));
		}
		int min = 0, max = size;

		do {
			int randomX = ThreadLocalRandom.current().nextInt(min, max);
			availableCell = getCell(randomX, 0);
		} while (availableCell.getOccupied());
		return availableCell;
	}

	@Requires({ "cells!=null" })
	public Cell getCell(int x, int y) {
		return cells.get(y).get(x);
	}

	@Requires({ "cells!=null" })
	public List<List<Cell>> getCells() {
		return cells;
	}

	@Requires({ "eagleMasterCell!=null" })
	public Cell getEagleMasterCell() {
		return eagleMasterCell;
	}

	@Requires({ "x>=0", "y>=0" })
	public boolean getOccupationState(int x, int y) {
		return cells.get(y).get(x).getOccupied();
	}

	@Requires({ "sharkMasterCell!=null" })
	public Cell getSharkMasterCell() {
		return sharkMasterCell;
	}

	@Requires({ "size!=0" })
	public int getSize() {
		return size;
	}

	@Requires({ "waterCells!=null" })
	public Set<Cell> getWaterCells() {
		return waterCells;
	}

	/**
	 * Set the cell to unoccupied after moving the piece.
	 * 
	 * @param x row
	 * @param y col
	 */
	@Requires({ "x>=0", "y>=0" })
	@Ensures("cells.get(y).get(x).getOccupied()==false")
	public void removePiece(int x, int y) {
		cells.get(y).get(x).setUnoccupied();
	}
}
