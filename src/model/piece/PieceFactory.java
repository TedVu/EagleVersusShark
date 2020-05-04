package model.piece;

import model.contract.EngineInterface;
import model.contract.PieceInterface;
import model.enumtype.PieceType;

/**
 * @author sefira
 *
 */
public class PieceFactory {

	public static PieceInterface generatePiece(PieceType pieceType, int boardSize, EngineInterface engine) {
		if (pieceType == PieceType.ATTACKINGEAGLE) {
			return createAttackingEagle(boardSize, engine);
		} else if (pieceType == PieceType.LEADERSHIPEAGLE) {
			return createLeadershipEagle(boardSize);
		} else if (pieceType == PieceType.VISIONARYEAGLE) {
			return createVisionaryEagle(boardSize, engine);
		} else if (pieceType == PieceType.AGGRESSIVESHARK) {
			return createAggressiveShark(boardSize);
		} else if (pieceType == PieceType.DEFENSIVESHARK) {
			return createDesensiveShark(boardSize, engine);
		} else if (pieceType == PieceType.HEALINGSHARK) {
			return createHealingShark(boardSize, engine);
		} else {
			return null;
		}
	}

	private static PieceInterface createAggressiveShark(int boardSize) {
		return new AggressiveShark(PieceType.AGGRESSIVESHARK.xCoordinate(boardSize),
				PieceType.AGGRESSIVESHARK.yCoordinate(boardSize));
	}

	private static PieceInterface createAttackingEagle(int boardSize, EngineInterface engine) {
		return new AttackingEagle(PieceType.ATTACKINGEAGLE.xCoordinate(boardSize),
				PieceType.ATTACKINGEAGLE.yCoordinate(boardSize), engine);
	}

	private static PieceInterface createDesensiveShark(int boardSize, EngineInterface engine) {
		return new DefensiveShark(PieceType.DEFENSIVESHARK.xCoordinate(boardSize),
				PieceType.DEFENSIVESHARK.yCoordinate(boardSize), engine);
	}

	private static PieceInterface createHealingShark(int boardSize, EngineInterface engine) {
		return new HealingShark(PieceType.HEALINGSHARK.xCoordinate(boardSize),
				PieceType.HEALINGSHARK.yCoordinate(boardSize), engine);
	}

	private static PieceInterface createLeadershipEagle(int boardSize) {
		return new LeadershipEagle(PieceType.LEADERSHIPEAGLE.xCoordinate(boardSize),
				PieceType.LEADERSHIPEAGLE.yCoordinate(boardSize));
	}

	private static PieceInterface createVisionaryEagle(int boardSize, EngineInterface engine) {
		return new VisionaryEagle(PieceType.VISIONARYEAGLE.xCoordinate(boardSize),
				PieceType.VISIONARYEAGLE.yCoordinate(boardSize), engine);
	}

	/**
	 * Don't let anyone instantiate this class
	 */
	private PieceFactory() {
	}
}
