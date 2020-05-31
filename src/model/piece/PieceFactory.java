package model.piece;

import com.google.java.contract.Requires;

import model.contract.Engine;
import model.contract.Piece;
import model.enumtype.PieceType;

/**
 * @author sefira
 *
 */
public class PieceFactory {

	@Requires({ "boardSize== 9 || boardSize== 11 || boardSize== 13 || boardSize== 15", "engine!=null" })
	private static Piece createAggressiveShark(int boardSize, Engine engine) {
		return new AggressiveShark(PieceType.AGGRESSIVESHARK.xCoordinate(boardSize),
				PieceType.AGGRESSIVESHARK.yCoordinate(boardSize), engine);
	}

	@Requires({ "boardSize== 9 || boardSize== 11 || boardSize== 13 || boardSize== 15", "engine!=null" })
	private static Piece createAttackingEagle(int boardSize, Engine engine) {
		return new AttackingEagle(PieceType.ATTACKINGEAGLE.xCoordinate(boardSize),
				PieceType.ATTACKINGEAGLE.yCoordinate(boardSize), engine);
	}

	@Requires({ "boardSize== 9 || boardSize== 11 || boardSize== 13 || boardSize== 15", "engine!=null" })
	private static Piece createDesensiveShark(int boardSize, Engine engine) {
		return new DefensiveShark(PieceType.DEFENSIVESHARK.xCoordinate(boardSize),
				PieceType.DEFENSIVESHARK.yCoordinate(boardSize), engine);
	}

	@Requires({ "boardSize== 9 || boardSize== 11 || boardSize== 13 || boardSize== 15", "engine!=null" })
	private static Piece createHealingShark(int boardSize, Engine engine) {
		return new HealingShark(PieceType.HEALINGSHARK.xCoordinate(boardSize),
				PieceType.HEALINGSHARK.yCoordinate(boardSize), engine);
	}

	@Requires({ "boardSize== 9 || boardSize== 11 || boardSize== 13 || boardSize== 15"})
	private static Piece createLeadershipEagle(int boardSize) {
		return new LeadershipEagle(PieceType.LEADERSHIPEAGLE.xCoordinate(boardSize),
				PieceType.LEADERSHIPEAGLE.yCoordinate(boardSize));
	}
	
	@Requires({ "boardSize== 9 || boardSize== 11 || boardSize== 13 || boardSize== 15", "engine!=null" })
	private static Piece createVisionaryEagle(int boardSize, Engine engine) {
		return new VisionaryEagle(PieceType.VISIONARYEAGLE.xCoordinate(boardSize),
				PieceType.VISIONARYEAGLE.yCoordinate(boardSize), engine);
	}

	@Requires({ "boardSize== 9 || boardSize== 11 || boardSize== 13 || boardSize== 15", "engine!=null", "pieceType!=null" })
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

	/**
	 * Don't let anyone instantiate this class
	 */
	private PieceFactory() {
	}
}
