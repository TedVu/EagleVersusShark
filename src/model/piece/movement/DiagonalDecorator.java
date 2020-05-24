package model.piece.movement;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;

import model.board.Cell;
import model.contract.Piece;
import model.contract.PieceMovement;
import model.engine.EngineImpl;

/**
 * @author sefira & kevin
 *
 */

public class DiagonalDecorator extends PieceMoveDecorator {

	private Set<Cell> validMoves = new HashSet<>();

	public DiagonalDecorator(PieceMovement pieceMove) {
		super(pieceMove);
		// TODO Auto-generated constructor stub
	}

	/*
	 * @param piece - selected piece
	 * 
	 * @param distance - piece moving distance
	 * 
	 * @return the set of valid coordinate
	 */
	@Requires({ "piece != null", "distance == 1 || distance ==2" })
	@Ensures("validMoves != null")
	@Override
	public Set<Cell> getValidMove(Piece piece, int distance) {

		validMoves = pieceMove.getValidMove(piece, distance);

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
	 * 
	 * @param distance - piece moving distance
	 * 
	 * @return the set of valid coordinate for north east direction
	 */
	@Requires({ "x>=0", "y>=0", "step== 1 || step==2" })
	@Ensures("validMoves != null")
	private Set<Cell> validDiaNorthEast(int x, int y, int step) {
		Set<Cell> validMoves = new HashSet<>();
		for (int i = 1; i <= step; i++) {
			if (x + i < EngineImpl.getSingletonInstance().gameBoard().getSize() && y - i >= 0
					&& !EngineImpl.getSingletonInstance().gameBoard().getOccupationState(x + i, y - i)) {
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
	 * 
	 * @param distance - piece moving distance
	 * 
	 * @return the set of valid coordinate for north west direction
	 */
	@Requires({ "x>=0", "y>=0", "step== 1 || step==2" })
	@Ensures("validMoves != null")
	private Set<Cell> validDiaNorthWest(int x, int y, int step) {
		Set<Cell> validMoves = new HashSet<>();
		for (int i = 1; i <= step; i++) {
			if (x - i >= 0 && y - i >= 0
					&& !EngineImpl.getSingletonInstance().gameBoard().getOccupationState(x - i, y - i)) {
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
	 * 
	 * @param distance - piece moving distance
	 * 
	 * @return the set of valid coordinate for south east direction
	 */
	@Requires({ "x>=0", "y>=0", "step== 1 || step==2" })
	@Ensures("validMoves != null")
	private Set<Cell> validDiaSouthEast(int x, int y, int step) {
		Set<Cell> validMoves = new HashSet<>();
		for (int i = 1; i <= step; i++) {
			if (x + i < EngineImpl.getSingletonInstance().gameBoard().getSize()
					&& y + i < EngineImpl.getSingletonInstance().gameBoard().getSize()
					&& !EngineImpl.getSingletonInstance().gameBoard().getOccupationState(x + i, y + i)) {
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
	 * 
	 * @param distance - piece moving distance
	 * 
	 * @return the set of valid coordinate for south west direction
	 */
	@Requires({ "x>=0", "y>=0", "step== 1 || step==2" })
	@Ensures("validMoves != null")
	private Set<Cell> validDiaSouthWest(int x, int y, int step) {
		Set<Cell> validMoves = new HashSet<>();
		for (int i = 1; i <= step; i++) {
			if (y + i < EngineImpl.getSingletonInstance().gameBoard().getSize() && x - i >= 0
					&& !EngineImpl.getSingletonInstance().gameBoard().getOccupationState(x - i, y + i)) {
				Cell validMove = new Cell(x - i, y + i);
				validMoves.add(validMove);
			} else {
				break;
			}
		}

		return validMoves;
	}
}
