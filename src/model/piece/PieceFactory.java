package model.piece;

import model.contract.Engine;
import model.contract.Piece;
import model.enumtype.PieceType;

/**
 * @author sefira
 *
 */
public class PieceFactory {

	public static Piece generatePiece(PieceType pieceType, int boardSize, Engine engine) {
		if (pieceType == PieceType.ATTACKINGEAGLE) {
			return createAttackingEagle(boardSize, engine);
		} else if (pieceType == PieceType.LEADERSHIPEAGLE) {
			return createLeadershipEagle(boardSize);
		} else if (pieceType == PieceType.VISIONARYEAGLE) {
			return createVisionaryEagle(boardSize, engine);
		} else if (pieceType == PieceType.AGGRESSIVESHARK) {
			return createAggressiveShark(boardSize, engine);
		} else if (pieceType == PieceType.DEFENSIVESHARK) {
			return createDesensiveShark(boardSize, engine);
		} else if (pieceType == PieceType.HEALINGSHARK) {
			return createHealingShark(boardSize, engine);
		} else {
			return null;
		}
	}


	private static Piece createAggressiveShark(int boardSize, Engine engine) {
		return new AggressiveShark(PieceType.AGGRESSIVESHARK.xCoordinate(boardSize),
				PieceType.AGGRESSIVESHARK.yCoordinate(boardSize), engine);
	}

	private static Piece createAttackingEagle(int boardSize, Engine engine) {
		return new AttackingEagle(PieceType.ATTACKINGEAGLE.xCoordinate(boardSize),
				PieceType.ATTACKINGEAGLE.yCoordinate(boardSize), engine);
	}

	private static Piece createDesensiveShark(int boardSize, Engine engine) {
		return new DefensiveShark(PieceType.DEFENSIVESHARK.xCoordinate(boardSize),
				PieceType.DEFENSIVESHARK.yCoordinate(boardSize), engine);
	}

	private static Piece createHealingShark(int boardSize, Engine engine) {
		return new HealingShark(PieceType.HEALINGSHARK.xCoordinate(boardSize),
				PieceType.HEALINGSHARK.yCoordinate(boardSize), engine);
	}

	private static Piece createLeadershipEagle(int boardSize) {
		return new LeadershipEagle(PieceType.LEADERSHIPEAGLE.xCoordinate(boardSize),
				PieceType.LEADERSHIPEAGLE.yCoordinate(boardSize));
	}

	private static Piece createVisionaryEagle(int boardSize, Engine engine) {
		return new VisionaryEagle(PieceType.VISIONARYEAGLE.xCoordinate(boardSize),
				PieceType.VISIONARYEAGLE.yCoordinate(boardSize), engine);
	}

	/**
	 * Don't let anyone instantiate this class
	 */
	private PieceFactory() {
	}
}
