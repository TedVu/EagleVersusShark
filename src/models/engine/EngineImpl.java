package models.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;

import models.board.Board;
import models.pieces.AbstractPiece;
import models.pieces.Piece;
import models.pieces.PieceFactory;
import models.player.Player;
import models.player.PlayerImpl;

/**
 * @originalauthor Sefira
 * 
 * @author Ted
 *
 */
public class EngineImpl implements Engine {

	private static Engine engine = null;
	// private Map<UUID, Piece> pieces = new HashMap<UUID, Piece>();
	private List<Piece> pieces = new ArrayList<Piece>();
	private Map<String, Piece> piecesTest = new HashMap<String, Piece>();
	private PieceFactory pieceFactory = new PieceFactory();

	private List<Piece> activeEagles = new ArrayList<Piece>();
	private Player eaglePlayer = new PlayerImpl("eaglePlayer");
	private Player sharkPlayer = new PlayerImpl("sharkPlayer");

	private Board board;

	/*
	 * default constructor
	 */
	public EngineImpl() {
		board = new Board();
		seedData();
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

	@Override
	public boolean movePiece(Piece piece, int newX, int newY) {
		// Doing validation for each piece in each piece object to allow polymorphic
		// behaviour
		// If pass validation => updateBoard and set position for piece here
		if (piece.movePiece(newX, newY)) {

			board.removePiece(piece.getPosition().get("x"), piece.getPosition().get("y"));
			board.addPiece(newX, newY);
			return true;
		}
		return false;
	}

	/*
	 * @return List<Piece> - all pieces
	 */
	@Override
	public List<Piece> getAllPieces() {

		return this.pieces;
	}

	/*
	 * @return List<Piece> all active eagles
	 */
	@Override
	public List<Piece> getActiveEagles() {
		System.out.println(pieces);
		for (Piece piece : pieces) {
			if (piece != null && piece instanceof AbstractPiece && piece.isActive()) {
				activeEagles.add(piece);
			}
		}
		return activeEagles;
	}

	/*
	 * @return List<Piece> - all active sharks
	 */
	@Override
	public List<Piece> getActiveSharks() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * contructor for initial eagle creation
	 * 
	 * @param Piece piece - the piece that the active status will change
	 * 
	 * @param bool isActive - want to set to active ? true : false
	 */
	@Override
	public boolean setPieceActiveStatus(Piece piece, boolean isActive) {
		try {
			piece.setActive(isActive);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/*
	 * @return the current active player (eaglePlayer || sharkPlayer)
	 */

	/*
	 * Add new set of piece Should allow to add two pieces at a time in later
	 * implementation
	 */
	// @Override
	// public void addNewPiece(List<Piece> newPiece, Piece type) {
	//
	// if (type.equals("ATTACKER EAGLE")) {
	//
	// Piece piece = pieceFactory.generatePiece("AttackingEagle", 0, 0);
	// pieces.add(piece);
	// System.out.println("Added A New Attacker Eagle Piece");
	//
	// } else if (type.equals("VISIONARY EAGLE")) {
	//
	// Piece piece = pieceFactory.generatePiece("VisionaryEagle", 0, 0);
	// pieces.add(piece);
	// System.out.println("Added A New Visionary Eagle");
	// }
	//
	// }

	@Override
	public Player getCurrentActivePlayer() {

		if (eaglePlayer.getActive())
			return eaglePlayer;
		else
			return sharkPlayer;

	}

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

	/*
	 * return the initial active player, call this at the beginning of the program
	 * 
	 * @return (eaglePlayer || sharkPlayer)
	 */
	@Override
	public Player getInitialPlayerActivePlayer() {

		Player activePlayer;
		Random rand = new Random();
		int randomValue = rand.nextInt() % 2;

		if (randomValue == 0) {
			this.eaglePlayer.setActive(true);
			this.sharkPlayer.setActive(false);
			activePlayer = eaglePlayer;
		} else {
			this.eaglePlayer.setActive(false);
			this.sharkPlayer.setActive(true);
			activePlayer = sharkPlayer;
		}
		return activePlayer;
	}

	/*
	 * set active player
	 * 
	 * @param String playerType - must be "eagle" or "shark"
	 * 
	 * @param bool turnOnTimer - set interval to change player every n seconds or
	 * not
	 */
	@Override
	public void setActivePlayer(String playerType, boolean turnOnTimer) {
		System.out.println("current active player: " + getCurrentActivePlayer().getPlayerType());
		String nextPlayer;
		if (playerType.equals("eagle")) {
			this.eaglePlayer.setActive(true);
			this.sharkPlayer.setActive(false);
			nextPlayer = "shark";
		} else if (playerType.equals("shark")) {
			this.eaglePlayer.setActive(false);
			this.sharkPlayer.setActive(true);
			nextPlayer = "eagle";
		} else {
			throw new IllegalArgumentException("invalid player type, must be eagle or shark");
		}

		if (turnOnTimer) {
			setActivePlayerTimer(nextPlayer);
		}
	}

	/*
	 * schedule timer to call setActivePlayer(String playerType, boolean
	 * turnOnTimer)
	 * 
	 * @param - String playerType - must be "eagle" or "shark"
	 */
	@Override
	public void setActivePlayerTimer(String playerType) {

		Timer t = new java.util.Timer();
		t.schedule(new java.util.TimerTask() {
			@Override
			public void run() {
				setActivePlayer(playerType, true);
			}
		}, 7000);

	}

	@Override
	public void addNewPiece(List<Piece> newPiece, Piece type) {
		// TODO Auto-generated method stub

	}

}
