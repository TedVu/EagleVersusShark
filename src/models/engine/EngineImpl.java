package models.engine;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import models.pieces.Piece;
import models.pieces.PieceFactory;

public class EngineImpl implements Engine {

	private static Engine engine = null;
	private Map<UUID, Piece> pieces = new HashMap<UUID, Piece>();
	private PieceFactory pieceFactory = new PieceFactory();
	public static int BOARD_SIZE = 81;

	/*
	 * default constructor
	 */
	public EngineImpl() {
	}

	/*
	 * 
	 */
	public static Engine getSingletonInstance() {
		if (engine == null) {
			engine = new EngineImpl();

		}
		return engine;
	}

	/*
	 * 
	 */
	@Override
	public Map<UUID, Piece> getAllPieces() {

		Piece piece = pieceFactory.generatePiece("attackerEagle", 0, 0);
		Piece piece2 = pieceFactory.generatePiece("visionaryEagle", 0, 0);

		pieces.put(piece.getId(), piece);
		pieces.put(piece2.getId(), piece2);

		return this.pieces;
	}

}
