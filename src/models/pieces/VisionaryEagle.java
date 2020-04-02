
package models.pieces;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import models.engine.EngineImpl;

public class VisionaryEagle extends AbstractPiece {

	public VisionaryEagle(int x, int y) {
		super(x, y);
	}

	private boolean canSwapPosition = true;

	public boolean isCanSwapPosition() {
		// TODO Auto-generated constructor stub
		return canSwapPosition;
	}

	public void setCanSwapPosition(boolean canSwapPosition) {
		// TODO Auto-generated constructor stub
		this.canSwapPosition = canSwapPosition;
	}

	/*
	 * validate the new position and set it if it's valid
	 * 
	 * @param int newX - new x position
	 * 
	 * @param int newY - new y position
	 * 
	 * @return position valid based on rule ? true : false
	 */
	@Override
	public boolean movePiece(int newX, int newY) {

		setPosition(newX, newY);
		return true;

	}

	@Override
	public Set<List<Integer>> getValidMove() {
		Map<String, Integer> currentPosition = this.getPosition();
		int currentX = currentPosition.get("x");
		int currentY = currentPosition.get("y");
		Set<List<Integer>> validMoves = new HashSet<List<Integer>>();
		validMoves.addAll(validMovesSouth(currentX, currentY, 2));
		validMoves.addAll(validMovesNorth(currentX, currentY, 2));
		validMoves.addAll(validMovesEast(currentX, currentY, 2));
		validMoves.addAll(validMovesWest(currentX, currentY, 2));
		validMoves.addAll(validDiaNorthEast(currentX, currentY, 2));
		validMoves.addAll(validDiaSouthWest(currentX, currentY, 2));
		validMoves.addAll(validDiaSouthEast(currentX, currentY, 2));
		validMoves.addAll(validDiaNorthWest(currentX, currentY, 2));
		return validMoves;
	}

	public Set<List<Integer>> validMovesSouth(int x, int y, int cells) {
		Set<List<Integer>> validMoves = new HashSet<List<Integer>>();
		for (int i = 1; i <= cells; i++) {
			List<Integer> validMove = new LinkedList<Integer>();
			if (y + i < EngineImpl.getSingletonInstance().getBoard().getRow()) {
				validMove.add(x);
				validMove.add(y + i);
				validMoves.add(validMove);
			} else {
				break;
			}
		}
		return validMoves;
	}

	public Set<List<Integer>> validMovesNorth(int x, int y, int cells) {
		Set<List<Integer>> validMoves = new HashSet<List<Integer>>();
		for (int i = 1; i <= cells; i++) {
			List<Integer> validMove = new LinkedList<Integer>();
			if (y - i >= 0) {
				validMove.add(x);
				validMove.add(y - i);
				validMoves.add(validMove);
			} else {
				break;
			}
		}

		return validMoves;
	}

	public Set<List<Integer>> validMovesEast(int x, int y, int cells) {
		Set<List<Integer>> validMoves = new HashSet<List<Integer>>();
		for (int i = 1; i <= cells; i++) {
			List<Integer> validMove = new LinkedList<Integer>();
			if (x + i < EngineImpl.getSingletonInstance().getBoard().getCol()) {
				validMove.add(x + i);
				validMove.add(y);
				validMoves.add(validMove);
			} else {
				break;
			}
		}

		return validMoves;
	}

	public Set<List<Integer>> validMovesWest(int x, int y, int cells) {
		Set<List<Integer>> validMoves = new HashSet<List<Integer>>();
		for (int i = 1; i <= cells; i++) {
			List<Integer> validMove = new LinkedList<Integer>();
			if (x - i >= 0) {
				validMove.add(x - i);
				validMove.add(y);
				validMoves.add(validMove);
			} else {
				break;
			}
		}

		return validMoves;
	}

	public Set<List<Integer>> validDiaNorthEast(int x, int y, int cells) {
		Set<List<Integer>> validMoves = new HashSet<List<Integer>>();
		for (int i = 1; i <= cells; i++) {
			List<Integer> validMove = new LinkedList<Integer>();
			if (x + i < EngineImpl.getSingletonInstance().getBoard().getCol() && y - i >= 0) {
				validMove.add(x + i);
				validMove.add(y - i);
				validMoves.add(validMove);
			} else {
				break;
			}
		}

		return validMoves;
	}

	public Set<List<Integer>> validDiaSouthWest(int x, int y, int cells) {
		Set<List<Integer>> validMoves = new HashSet<List<Integer>>();
		for (int i = 1; i <= cells; i++) {
			List<Integer> validMove = new LinkedList<Integer>();
			if (y + i < EngineImpl.getSingletonInstance().getBoard().getRow() && x - i >= 0) {
				validMove.add(x - i);
				validMove.add(y + i);
				validMoves.add(validMove);
			} else {
				break;
			}
		}

		return validMoves;
	}

	public Set<List<Integer>> validDiaSouthEast(int x, int y, int cells) {
		Set<List<Integer>> validMoves = new HashSet<List<Integer>>();
		for (int i = 1; i <= cells; i++) {
			List<Integer> validMove = new LinkedList<Integer>();
			if (x + i < EngineImpl.getSingletonInstance().getBoard().getCol()
					&& y + i < EngineImpl.getSingletonInstance().getBoard().getRow()) {
				validMove.add(x + i);
				validMove.add(y + i);
				validMoves.add(validMove);
			} else {
				break;
			}
		}

		return validMoves;
	}

	public Set<List<Integer>> validDiaNorthWest(int x, int y, int cells) {
		Set<List<Integer>> validMoves = new HashSet<List<Integer>>();
		for (int i = 1; i <= cells; i++) {
			List<Integer> validMove = new LinkedList<Integer>();
			if (x - i >= 0 && y - i >= 0) {
				validMove.add(x - i);
				validMove.add(y - i);
				validMoves.add(validMove);
			} else {
				break;
			}
		}

		return validMoves;
	}

	// usage: piece.useAbility("swap", piece, affectedPiece)
	@Override
	public boolean useAbility(String abilityName, Piece piece, Piece affectedPiece) {
		if (abilityName.equals("swap"))
			return swap(piece, affectedPiece);

		return false;
	}

	public boolean swap(Piece piece, Piece affectedPiece) {

		try {
			Map<String, Integer> position1 = new HashMap<String, Integer>();
			Map<String, Integer> position2 = new HashMap<String, Integer>();

			position1.putAll(piece.getPosition());
			position2.putAll(affectedPiece.getPosition());

			piece.setPosition(position2.get("x"), position2.get("y"));
			affectedPiece.setPosition(position1.get("x"), position1.get("y"));

			return true;

		} catch (Exception e) {
			return false;
		}
	}

	public boolean capture(Piece capturingPiece, Piece capturedPiece) {
		throw new IllegalArgumentException("this piece can't capture opponent!");
	}

}
