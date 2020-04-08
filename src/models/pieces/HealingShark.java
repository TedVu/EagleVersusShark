package models.pieces;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import models.engine.EngineImpl;

public class HealingShark extends AbstractPiece {

	public HealingShark(int x, int y) {
		super(x, y);
	}

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
		validMoves.addAll(validMovesSouth(currentX, currentY, 1));
		validMoves.addAll(validMovesNorth(currentX, currentY, 1));
		validMoves.addAll(validMovesEast(currentX, currentY, 1));
		validMoves.addAll(validMovesWest(currentX, currentY, 1));
		validMoves.addAll(validDiaNorthEast(currentX, currentY, 1));
		validMoves.addAll(validDiaSouthWest(currentX, currentY, 1));
		validMoves.addAll(validDiaSouthEast(currentX, currentY, 1));
		validMoves.addAll(validDiaNorthWest(currentX, currentY, 1));
		return validMoves;
	}

	/*
	 * ATTENTION: CRITICAL TODO: Refactor all classes that have this code to
	 * interface
	 */
	public Set<List<Integer>> validMovesSouth(int x, int y, int cells) {
		Set<List<Integer>> validMoves = new HashSet<List<Integer>>();
		for (int i = 1; i <= cells; i++) {
			List<Integer> validMove = new LinkedList<Integer>();
			if (y + i < EngineImpl.getSingletonInstance().pieceOperator().getBoard().getRow()) {
				validMove.add(x);
				validMove.add(y + i);
				validMoves.add(validMove);
			} else {
				break;
			}
		}
		return validMoves;
	}

	/*
	 * ATTENTION: CRITICAL TODO: Refactor all classes that have this code to
	 * interface
	 */
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
			if (x + i < EngineImpl.getSingletonInstance().pieceOperator().getBoard().getCol()) {
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
			if (x + i < EngineImpl.getSingletonInstance().pieceOperator().getBoard().getCol() && y - i >= 0) {
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
			if (y + i < EngineImpl.getSingletonInstance().pieceOperator().getBoard().getRow() && x - i >= 0) {
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
			if (x + i < EngineImpl.getSingletonInstance().pieceOperator().getBoard().getCol()
					&& y + i < EngineImpl.getSingletonInstance().pieceOperator().getBoard().getRow()) {
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

	@Override
	public boolean useAbility(String abilityName, Piece piece, Piece affectedPiece) {
		if (!abilityName.equals("HEAL SHARK"))
			return false;

		return healShark(piece, affectedPiece);
	}

	private boolean healShark(Piece piece, Piece affectedPiece) {
		try {

			if (!isSurrounding(piece, affectedPiece))
				return false;

			affectedPiece.setActive(true);
			return true;

		} catch (Exception e) {
			System.out.println("Unable to heal the selected piece!");
			return false;
		}
	}

	private boolean isSurrounding(Piece piece, Piece affectedPiece) {
		int visibility = 1;

		int pieceX = piece.getPosition().get("x");
		int pieceY = piece.getPosition().get("y");

		int affectedPieceX = affectedPiece.getPosition().get("x");
		int affectedPieceY = affectedPiece.getPosition().get("y");

		if ((pieceX + visibility == affectedPieceX || pieceX == affectedPieceX || affectedPieceX - pieceX == visibility)
				&& (pieceY + visibility == affectedPieceY || pieceY == affectedPieceY
						|| affectedPieceY - pieceY == visibility)) {
			return true;
		}
		return false;
	}
}
