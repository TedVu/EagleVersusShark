package models.pieces;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import models.engine.EngineImpl;

public class HealingShark extends AbstractPieceMoveDiagonal {

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
