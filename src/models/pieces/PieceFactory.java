package models.pieces;

import asset.PieceType;

/**
 * @author sefira
 *
 */
public class PieceFactory {

	/**
	 * 
	 */
	public PieceFactory() {
	}

	/**
	 * @param pieceType
	 * @return
	 */
	public Piece generatePiece(PieceType pieceType) {
		if (pieceType.toString().equalsIgnoreCase("AttackingEagle")) {
			return new AttackingEagle(pieceType.x(), pieceType.y());
		}
		if (pieceType.toString().equalsIgnoreCase("VisionaryEagle")) {
			return new VisionaryEagle(pieceType.x(), pieceType.y());
		}
		if (pieceType.toString().equalsIgnoreCase("LeadershipEagle")) {
			return new LeadershipEagle(pieceType.x(), pieceType.y());
		}
		if (pieceType.toString().equalsIgnoreCase("AggressiveShark")) {
			return new AggressiveShark(pieceType.x(), pieceType.y());
		}
		if (pieceType.toString().equalsIgnoreCase("DefensiveShark")) {
			return new DefensiveShark(pieceType.x(), pieceType.y());
		}
		if (pieceType.toString().equalsIgnoreCase("HealingShark")) {
			return new HealingShark(pieceType.x(), pieceType.y());
		}
		return null;
	}
}
