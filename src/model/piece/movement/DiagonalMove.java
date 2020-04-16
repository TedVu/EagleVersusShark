package model.piece.movement;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import model.board.Cell;
import model.contract.PieceInterface;
import model.engine.EngineImpl;

/**
 * @author sefira & kevin
 *
 */

public class DiagonalMove extends AbstractPieceMove{
	
	private Set<Cell> validMoves;
	
	/*
	 * @param piece - selected piece
	 * @param distance - piece moving distance
	 * @return the set of valid coordinate
	 */
	@Override
	public Set<Cell> getValidMove(PieceInterface piece, int distance) {
		
		validMoves = super.getValidMove(piece, distance);
		
		Map<String, Integer> currentPosition = piece.getPosition();
		int currentX = currentPosition.get("x");
		int currentY = currentPosition.get("y");
		
		validMoves.addAll(validDiaNorthEast(currentX, currentY, distance));
		validMoves.addAll(validDiaSouthWest(currentX, currentY, distance));
		validMoves.addAll(validDiaSouthEast(currentX, currentY, distance));
		validMoves.addAll(validDiaNorthWest(currentX, currentY, distance));
		
		return validMoves;
	}
	
	/*
	 * @param piece - selected piece
	 * @param distance - piece moving distance
	 * @return the set of valid coordinate for north east direction
	 */
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
	
	/*
	 * @param piece - selected piece
	 * @param distance - piece moving distance
	 * @return the set of valid coordinate for north west direction
	 */
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
	
	/*
	 * @param piece - selected piece
	 * @param distance - piece moving distance
	 * @return the set of valid coordinate for south east direction
	 */
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
	
	/*
	 * @param piece - selected piece
	 * @param distance - piece moving distance
	 * @return the set of valid coordinate for south west direction
	 */
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
	
}
