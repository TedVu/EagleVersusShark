package models.pieces;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import models.engine.EngineImpl;

public class AttackerEagle extends AbstractExtendedOneMove {

	public AttackerEagle(int x, int y) {
		super(x, y);
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

	// usage: piece.useAbility("capture", piece, affectedPiece)
	@Override
	public boolean useAbility(String abilityName, Piece piece, Piece affectedPiece) {
		if (abilityName.equals("capture"))
			return capture(piece, affectedPiece);

		return false;
	}

	private boolean capture(Piece piece, Piece affectedPiece) {
		try {

			List<Piece> activeEagles = EngineImpl.getSingletonInstance().getActiveEagles();

			int distance = 1;

			int pieceX = piece.getPosition().get("x");
			int pieceY = piece.getPosition().get("y");

			int affectedPieceX = affectedPiece.getPosition().get("x");
			int affectedPieceY = affectedPiece.getPosition().get("y");

			for (Piece activePiece : activeEagles) {
				if (activePiece instanceof LeadershipEagle) {
					int x = activePiece.getPosition().get("x");
					int y = activePiece.getPosition().get("y");

					if (isSurrounding(pieceX, x, pieceY, y, 1)) {
						distance = 2;
					}
				}
			}

			if (!isSurrounding(pieceX, affectedPieceX, pieceY, affectedPieceY, distance))
				return false;

			affectedPiece.setActive(false);

			return true;

		} catch (Exception e) {
			return false;
		}
	}

	private boolean isSurrounding(int x1, int x2, int y1, int y2, int distance) {
		if (x2 > x1 + distance || y2 > y1 + distance || x2 < x1 - distance || y2 < y1 - distance) {
			return false;
		}
		return true;
	}
}
