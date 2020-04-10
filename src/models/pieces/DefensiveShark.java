package models.pieces;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import models.engine.EngineImpl;

public class DefensiveShark extends AbstractPieceMoveDiagonal {
	public DefensiveShark(int x, int y) {
		super(x, y);
	}

	// Movement same as VisionaryEagle
	@Override
	public boolean movePiece(int newX, int newY) {
		setPosition(newX, newY);
		return true;
	}

	// TO BE reimplemented as interface on refactoring stage
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

	

	@Override
	public boolean useAbility(String abilityName, Piece piece, Piece affectedPiece) {
		if (!abilityName.equals("DEFEND SHARK"))
			return false;

		return defendShark(piece, affectedPiece);
	}

	private boolean defendShark(Piece piece, Piece affectedPiece) {
		try {
			if (!isSurrounding(piece, affectedPiece))
				return false;

			if (!affectedPiece.isImmune()) {
				affectedPiece.setImmune(true);
			} else {
				System.out.println("Piece is already protected!");
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