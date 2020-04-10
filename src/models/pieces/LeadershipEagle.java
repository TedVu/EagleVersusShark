
package models.pieces;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import models.engine.EngineImpl;

public class LeadershipEagle extends AbstractPieceMove {

	public LeadershipEagle(int x, int y) {
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

	// usage: piece.useAbility("protect", piece, affectedPiece)
	@Override
	public boolean useAbility(String abilityName, Piece piece, Piece affectedPiece) {
		if (abilityName.equals("protect"))
			return giveProtection(piece, affectedPiece);

		return false;
	}

	public boolean giveProtection(Piece piece, Piece affectedPiece) {

		try {

			int pieceX = piece.getPosition().get("x");
			int pieceY = piece.getPosition().get("y");

			int affectedPieceX = affectedPiece.getPosition().get("x");
			int affectedPieceY = affectedPiece.getPosition().get("y");

			if (affectedPieceX > pieceX + 1 || affectedPieceY > pieceY + 1 || affectedPieceX < pieceX - 1
					|| affectedPieceY < pieceY - 1) {
				return false;
			}

			affectedPiece.setImmune(true);

			return true;

		} catch (Exception e) {
			return false;
		}
	}

}
