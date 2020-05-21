package model.board;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.concurrent.ThreadLocalRandom;

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;

import model.contract.Engine;
import model.enumtype.TeamType;
import model.piece.GamePiece;
import model.piece.commands.PieceCommands;
import model.player.GameTurn;
import model.player.Player;
import model.player.PlayerImpl;

/**
 * @author ted &#38; kevin
 *
 */
public class GameBoard implements Serializable {

	private static final long serialVersionUID = 5510738805489933149L;
	// Fix row and col for A1
	private int size;
	private List<List<Cell>> cells;
	private Cell sharkMasterCell = null;

	private Engine engine;

	private Set<Cell> waterCells;

	private Player eaglePlayer = new PlayerImpl(TeamType.EAGLE);

	private Player sharkPlayer = new PlayerImpl(TeamType.SHARK);

	private transient Timer gameTimer;

	// private GameEngineCallbackInterface geCallback = new
	// GameEngineCallbackImpl();

	private GameBoard board;

	private GamePiece gamePiece;

	private int turn = 1;

	private int round;

	private boolean gameRunning = false;

	private int totalNumPiece;

	private PieceCommands pieceCommands;

	private GameTurn gameTurn;

	public GameBoard(Engine engine) {
		super();
		this.engine = engine;
		this.gamePiece = engine.pieceOperator();
	}

	// public int getTotalNumPiece() {
	// return this.totalNumPiece;
	// }

	/**
	 * Return shark's master cell
	 * 
	 * @author Chanboth
	 */
	public Cell getSharkMasterCell() {
		return getCell(size / 2, size - 1);
	}

	public Cell getEagleMasterCell() {
		return getCell(size / 2, 0);
	}

	public GameBoard(int boardSize) {
		size = boardSize;
		cells = new ArrayList<>();
		for (int row = 0; row < size; ++row) {
			cells.add(new ArrayList<Cell>());
			for (int col = 0; col < size; ++col) {
				cells.get(row).add(new Cell(col, row));
			}
		}

		waterCells = new HashSet<>();
		// water cell
		int mid = (cells.size() - 1) / 2;
		for (int row = mid - 1; row <= mid + 1; ++row) {
			for (int col = 0; col < size; ++col) {
				cells.get(row).get(col).setWaterCell();
				waterCells.add(cells.get(row).get(col));
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

	/**
	 * @param x
	 * @param y
	 */
	@Requires({ "x>=0", "y>=0" })
	@Ensures("cells.get(y).get(x).getOccupied()==false")
	public void removePiece(int x, int y) {
		cells.get(y).get(x).setUnoccupied();
	}

	public Set<Cell> getWaterCells() {
		return this.waterCells;
	}

}
