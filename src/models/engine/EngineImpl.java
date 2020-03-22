package models.engine;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


import model.board.Board;
import models.pieces.Piece;
import models.pieces.PieceFactory;

/**
 * @originalauthor Sefira
 * 
 * @author Ted
 *
 */
public class EngineImpl implements Engine {

	private static Engine engine = null;
	private Map<UUID, Piece> pieces = new HashMap<UUID, Piece>();
	private Map<String, Piece> piecesTest = new HashMap<String, Piece>();
	private PieceFactory pieceFactory = new PieceFactory();
	private Board board;

	/*
	 * default constructor
	 */
	public EngineImpl() {
		board = new Board();
	}

	/*
	 * seed data for testing purpose
	 */
	public void seedData() {
		Piece eaglePiece1 = pieceFactory.generatePiece("AttackingEagle", 0, 3);
		Piece eaglePiece2 = pieceFactory.generatePiece("LeadershipEagle", 1, 4);
		Piece eaglePiece3 = pieceFactory.generatePiece("VisionaryEagle", 0, 5);

		board.addPiece(0, 3);
		board.addPiece(1, 4);
		board.addPiece(0, 5);

		piecesTest.put("AttackingEagle", eaglePiece1);
		piecesTest.put("LeadershipEagle", eaglePiece2);
		piecesTest.put("VisionaryEagle", eaglePiece3);

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

	@Override
	public boolean movePiece(Piece piece, int newX, int newY) {
		// Doing validation for each piece in each piece object to allow polymorphic
		// behaviour
		// If pass validation => updateBoard and set position for piece here
		if (piece.movePieceTed(newX, newY)) {

			board.removePiece(piece.getPosition().get("x"), piece.getPosition().get("y"));
			board.addPiece(newX, newY);
			piece.setPosition(newX, newY);
		}
		return true;
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

	/*
     * Add new set of piece
     * Should allow to add two pieces at a time in later implementation
     */
	@Override
	public void addNewPiece(Map<UUID, Piece> newPiece, Piece type) {
		String newPieceType = type.getType().toUpperCase();
		
		if(newPieceType.equals("ATTACKER EAGLE")) {
			
			Piece piece = pieceFactory.generatePiece("attackerEagle", 0, 0);
			pieces.put(piece.getId(), piece);
			System.out.println("Added A New Attacker Eagle Piece");
			
		} else if (newPieceType.equals("VISIONARY EAGLE")){
			
			Piece piece = pieceFactory.generatePiece("visionaryEagle", 0, 0);
			pieces.put(piece.getId(), piece);
			System.out.println("Added A New Visionary Eagle");
		}
		
	}

	@Override
	public Board getBoard() {
		return board;
	}

	@Override
	public Map<String, Piece> getAllPiecesTed() {
		return piecesTest;
	}

	@Override
	public boolean checkSelectPiece(String occupiedPiece) {
		if (!piecesTest.containsKey(occupiedPiece)) {
			return false;
		}
		return true;
	}

}
