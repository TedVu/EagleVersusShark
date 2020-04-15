package model.piece;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import model.board.Cell;
import model.contract.PieceInterface;
import model.contract.PieceMovementInterface;
import model.engine.EngineImpl;

/**
 * @author sefira & chanboth
 *
 */
public abstract class AbstractPiece
		implements
			PieceInterface,
			PieceMovementInterface {

	private Map<String, Integer> position = new HashMap<String, Integer>();
	private boolean isActive;

	/*
	 * 
	 * Constructor for initial eagle creation
	 * 
	 */
	public AbstractPiece(int x, int y) {
		position.put("x", x);
		position.put("y", y);
		isActive = true;
	}

	@Override
	public Map<String, Integer> getPosition() {
		return this.position;
	}

	@Override
	public boolean isActive() {
		return this.isActive;
	}

	@Override
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public void setPosition(int x, int y) {
		this.position.replace("x", x);
		this.position.replace("y", y);
	}

	@Override
	public Set<Cell> validDiaNorthEast(int x, int y, int step) {
		Set<Cell> validMoves = new HashSet<>();
		for (int i = 1; i <= step; i++) {
			if (x + i < EngineImpl.getSingletonInstance().getBoard().getSize()
					&& y - i >= 0 && !EngineImpl.getSingletonInstance()
							.getBoard().getOccupationState(x + i, y - i)) {
				Cell validMove = new Cell(x + i, y - i);
				validMoves.add(validMove);
			} else {
				break;
			}
		}
		return validMoves;
	}

	@Override
	public Set<Cell> validDiaNorthWest(int x, int y, int step) {
		Set<Cell> validMoves = new HashSet<>();
		for (int i = 1; i <= step; i++) {
			if (x - i >= 0 && y - i >= 0 && !EngineImpl.getSingletonInstance()
					.getBoard().getOccupationState(x - i, y - i)) {
				Cell validMove = new Cell(x - i, y - i);
				validMoves.add(validMove);
			} else {
				break;
			}
		}

		return validMoves;
	}

	@Override
	public Set<Cell> validDiaSouthEast(int x, int y, int step) {
		Set<Cell> validMoves = new HashSet<>();
		for (int i = 1; i <= step; i++) {
			if (x + i < EngineImpl.getSingletonInstance().getBoard().getSize()
					&& y + i < EngineImpl.getSingletonInstance().getBoard()
							.getSize()
					&& !EngineImpl.getSingletonInstance().getBoard()
							.getOccupationState(x + i, y + i)) {
				Cell validMove = new Cell(x + i, y + i);
				validMoves.add(validMove);
			} else {
				break;
			}
		}

		return validMoves;
	}

	@Override
	public Set<Cell> validDiaSouthWest(int x, int y, int step) {
		Set<Cell> validMoves = new HashSet<>();
		for (int i = 1; i <= step; i++) {
			if (y + i < EngineImpl.getSingletonInstance().getBoard().getSize()
					&& x - i >= 0 && !EngineImpl.getSingletonInstance()
							.getBoard().getOccupationState(x - i, y + i)) {
				Cell validMove = new Cell(x - i, y + i);
				validMoves.add(validMove);
			} else {
				break;
			}
		}

		return validMoves;
	}

	@Override
	public Set<Cell> validMovesEast(int x, int y, int step) {
		Set<Cell> validMoves = new HashSet<>();
		for (int i = 1; i <= step; i++) {
			if (x + i < EngineImpl.getSingletonInstance().getBoard().getSize()
					&& !EngineImpl.getSingletonInstance().getBoard()
							.getOccupationState(x + i, y)) {
				Cell validMove = new Cell(x + i, y);
				validMoves.add(validMove);
			} else {
				break;
			}
		}

		return validMoves;
	}

	@Override
	public Set<Cell> validMovesNorth(int x, int y, int step) {
		Set<Cell> validMoves = new HashSet<>();
		for (int i = 1; i <= step; i++) {
			if (y - i >= 0 && !EngineImpl.getSingletonInstance().getBoard()
					.getOccupationState(x, y - i)) {
				Cell validMove = new Cell(x, y - i);
				validMoves.add(validMove);
			} else {
				break;
			}
		}

		return validMoves;
	}

	@Override
	public Set<Cell> validMovesSouth(int x, int y, int step) {
		Set<Cell> validMoves = new HashSet<Cell>();
		for (int i = 1; i <= step; i++) {
			if (y + i < EngineImpl.getSingletonInstance().getBoard().getSize()
					&& !EngineImpl.getSingletonInstance().getBoard()
							.getOccupationState(x, y + i)) {
				Cell validMove = new Cell(x, y + i);
				validMoves.add(validMove);
			} else {
				break;
			}
		}
		return validMoves;
	}

	@Override
	public Set<Cell> validMovesWest(int x, int y, int step) {
		Set<Cell> validMoves = new HashSet<>();
		for (int i = 1; i <= step; i++) {
			if (x - i >= 0 && !EngineImpl.getSingletonInstance().getBoard()
					.getOccupationState(x - i, y)) {
				Cell validMove = new Cell(x - i, y);
				validMoves.add(validMove);
			} else {
				break;
			}
		}

		return validMoves;
	}
}
