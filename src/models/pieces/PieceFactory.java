package models.pieces;

import asset.PieceType;

/**
 * @author sefira
 *
 */
public class PieceFactory {

	/**
	 * Don't let anyone instantiate this class
	 */
	private PieceFactory() {
	}

	public static Piece generatePiece(PieceType pieceType) {
		if (pieceType == PieceType.ATTACKINGEAGLE) {
			return createAttackingEagle();
		} else if (pieceType == PieceType.LEADERSHIPEAGLE) {
			return createLeadershipEagle();
		} else if (pieceType == PieceType.VISIONARYEAGLE) {
			return createVisionaryEagle();
		} else if (pieceType == PieceType.AGGRESSIVESHARK) {
			return createAggressiveShark();
		} else if (pieceType == PieceType.DEFENSIVESHARK) {
			return createDesensiveShark();
		} else if (pieceType == PieceType.HEALINGSHARK) {
			return createHealingShark();
		} else {
			return null;
		}
	}

	private static Piece createAttackingEagle() {
		return new AttackingEagle(PieceType.ATTACKINGEAGLE.xCoordinate(), PieceType.ATTACKINGEAGLE.yCoordinate());
	}

	private static Piece createLeadershipEagle() {
		return new LeadershipEagle(PieceType.LEADERSHIPEAGLE.xCoordinate(), PieceType.LEADERSHIPEAGLE.yCoordinate());
	}

	private static Piece createVisionaryEagle() {
		return new VisionaryEagle(PieceType.VISIONARYEAGLE.xCoordinate(), PieceType.VISIONARYEAGLE.yCoordinate());
	}

	private static Piece createAggressiveShark() {
		return new AggressiveShark(PieceType.AGGRESSIVESHARK.xCoordinate(), PieceType.AGGRESSIVESHARK.yCoordinate());
	}

	private static Piece createDesensiveShark() {
		return new DefensiveShark(PieceType.DEFENSIVESHARK.xCoordinate(), PieceType.DEFENSIVESHARK.yCoordinate());
	}

	private static Piece createHealingShark() {
		return new HealingShark(PieceType.HEALINGSHARK.xCoordinate(), PieceType.HEALINGSHARK.yCoordinate());
	}
}
