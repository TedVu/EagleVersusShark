package models.pieces;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import models.engine.EngineImpl;

public class AggressiveShark extends AbstractPiece {

	private boolean onWaterCell;

	public AggressiveShark(int x, int y) {
		super(x, y);
		onWaterCell = false;
	}

	@Override
	public boolean movePiece(int newX, int newY) {
		/*
		 * Quickly move to any water cell if currently on water cell.
		 */
		if (onWaterCell) {
			// Discuss on how to implement this for A2
		}
		setPosition(newX, newY);
		return true;
	}

	// Same movement as LeadershipEagle except 1 cell ←→↑↓
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
		return validMoves;
	}

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

	@Override
	public boolean useAbility(String abilityName, Piece piece, Piece affectedPiece) {
		if (!abilityName.equals("CAPTURE EAGLE"))
			return false;

		return captureEagle(piece, affectedPiece);
	}

	private boolean captureEagle(Piece piece, Piece affectedPiece) {
		try {
			if (!isSurrounding(piece, affectedPiece)) {
				System.out.println("Piece is not close enough to capture");
				return false;
			}

			if (!affectedPiece.isImmune()) {
				affectedPiece.setActive(false);
			} else {
				System.out.println("Cannot capture the specified piece!");
			}
			// TODO: implement inactive ability for one turn after using

			// TODO: implement "can quickly move to neighbouring cell of any sharks"

			return true;
		} catch (Exception e) {
			System.out.println("Unable to protect the selected piece from being captured!");
			return false;
		}
	}

	private boolean isSurrounding(Piece piece, Piece affectedPiece) {

		int pieceX = piece.getPosition().get("x");
		int pieceY = piece.getPosition().get("y");

		int affectedPieceX = affectedPiece.getPosition().get("x");
		int affectedPieceY = affectedPiece.getPosition().get("y");

		int defaultVisibility = 1;
		int visibilityInWaterCell = 2;
		int visibility;

		/*
		 * For water cell A2. Can immediately capture any opponent on one cell
		 * surrounding if on water cell (opponent and itself on water cell) regardless
		 * of any opponent position/ability.
		 */
		if (this.onWaterCell) {
			visibility = visibilityInWaterCell;
		} else {
			visibility = defaultVisibility;
		}

		if ((pieceX + visibility == affectedPieceX || pieceX == affectedPieceX || affectedPieceX - pieceX == visibility)
				&& (pieceY + visibility == affectedPieceY || pieceY == affectedPieceY
						|| affectedPieceY - pieceY == visibility)) {
			/*
			 * IF visibility = 2 THEN check if the affected piece is next to leadership
			 * eagle
			 */
			return true;
		}

		return false;
	}
}
