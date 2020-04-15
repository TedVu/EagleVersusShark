package model.piece;

import model.contract.PieceInterface;
import model.enumtype.PieceType;

/**
 * @author sefira
 *
 */
public class PieceFactory {

	public static PieceInterface generatePiece(PieceType pieceType,
			int boardSize) {
		if (pieceType == PieceType.ATTACKINGEAGLE) {
			return createAttackingEagle(boardSize);
		} else if (pieceType == PieceType.LEADERSHIPEAGLE) {
			return createLeadershipEagle(boardSize);
		} else if (pieceType == PieceType.VISIONARYEAGLE) {
			return createVisionaryEagle(boardSize);
		} else if (pieceType == PieceType.AGGRESSIVESHARK) {
			return createAggressiveShark(boardSize);
		} else if (pieceType == PieceType.DEFENSIVESHARK) {
			return createDesensiveShark(boardSize);
		} else if (pieceType == PieceType.HEALINGSHARK) {
			return createHealingShark(boardSize);
		} else {
			return null;
		}
	}

	private static PieceInterface createAggressiveShark(int boardSize) {
		return new AggressiveShark(
				PieceType.AGGRESSIVESHARK.xCoordinate(boardSize),
				PieceType.AGGRESSIVESHARK.yCoordinate(boardSize));
	}

	private static PieceInterface createAttackingEagle(int boardSize) {
		return new AttackingEagle(
				PieceType.ATTACKINGEAGLE.xCoordinate(boardSize),
				PieceType.ATTACKINGEAGLE.yCoordinate(boardSize));
	}

	private static PieceInterface createDesensiveShark(int boardSize) {
		return new DefensiveShark(
				PieceType.DEFENSIVESHARK.xCoordinate(boardSize),
				PieceType.DEFENSIVESHARK.yCoordinate(boardSize));
	}

	private static PieceInterface createHealingShark(int boardSize) {
		return new HealingShark(PieceType.HEALINGSHARK.xCoordinate(boardSize),
				PieceType.HEALINGSHARK.yCoordinate(boardSize));
	}

	private static PieceInterface createLeadershipEagle(int boardSize) {
		return new LeadershipEagle(
				PieceType.LEADERSHIPEAGLE.xCoordinate(boardSize),
				PieceType.LEADERSHIPEAGLE.yCoordinate(boardSize));
	}

	private static PieceInterface createVisionaryEagle(int boardSize) {
		return new VisionaryEagle(
				PieceType.VISIONARYEAGLE.xCoordinate(boardSize),
				PieceType.VISIONARYEAGLE.yCoordinate(boardSize));
	}

	/**
	 * Don't let anyone instantiate this class
	 */
	private PieceFactory() {
	}
}
