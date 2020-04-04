package models.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;

import asset.PieceType;
import controller.MakingMovePropertyChangeListener;
import controller.TimerPropertyChangeListener;
import models.board.Board;
import models.pieces.AttackerEagle;
import models.pieces.LeadershipEagle;
import models.pieces.Piece;
import models.pieces.PieceFactory;
import models.pieces.VisionaryEagle;
import models.player.Player;
import models.player.PlayerImpl;
import view.callbacks.GameEngineCallbackImpl;
import view.interfaces.GameEngineCallback;

/**
 * @author Sefira
 * 
 *
 */
public class EngineImpl implements Engine {
	private boolean startGame = false;
	private static Engine engine = null;
	private Map<String, Piece> pieces = new HashMap<String, Piece>();
	private PieceFactory pieceFactory = new PieceFactory();

	private List<Piece> activeEagles = new ArrayList<Piece>();
	private List<Piece> activeSharks = new ArrayList<Piece>();
	private Player eaglePlayer = new PlayerImpl("eaglePlayer");
	private Player sharkPlayer = new PlayerImpl("sharkPlayer");
	private Timer gameTimer;

	private GameEngineCallback geCallback = new GameEngineCallbackImpl();

	private Board board;

	public EngineImpl() {
		board = new Board();
		initializePiece();
		geCallback.addProperytChangeListener(new TimerPropertyChangeListener());
		geCallback.addProperytChangeListener(new MakingMovePropertyChangeListener());

	}

	public static Engine getSingletonInstance() {
		if (engine == null) {
			engine = new EngineImpl();

		}
		return engine;
	}

	public void initializePiece() {

		Piece attackingEagle = pieceFactory.generatePiece(PieceType.ATTACKINGEAGLE.toString(), (board.getCol() / 2 - 1),
				0);
		Piece leadershipEagle = pieceFactory.generatePiece(PieceType.LEADERSHIPEAGLE.toString(), board.getCol() / 2, 1);
		Piece visionaryEagle = pieceFactory.generatePiece(PieceType.VISIONARYEAGLE.toString(), board.getCol() / 2 + 1,
				0);

		Piece aggressiveShark = pieceFactory.generatePiece(PieceType.AGGRESSIVESHARK.toString(), board.getCol() / 2 - 1,
				board.getRow() - 1);
		Piece defensiveShark = pieceFactory.generatePiece(PieceType.DEFENSIVESHARK.toString(), board.getCol() / 2,
				board.getRow() - 2);
		Piece healingShark = pieceFactory.generatePiece(PieceType.HEALINGSHARK.toString(), board.getCol() / 2 + 1,
				board.getRow() - 1);

		board.addPiece(attackingEagle.getPosition().get("x"), attackingEagle.getPosition().get("y"));
		board.addPiece(leadershipEagle.getPosition().get("x"), leadershipEagle.getPosition().get("y"));
		board.addPiece(visionaryEagle.getPosition().get("x"), visionaryEagle.getPosition().get("y"));

		board.addPiece(aggressiveShark.getPosition().get("x"), aggressiveShark.getPosition().get("y"));
		board.addPiece(defensiveShark.getPosition().get("x"), defensiveShark.getPosition().get("y"));
		board.addPiece(healingShark.getPosition().get("x"), healingShark.getPosition().get("y"));

		pieces.put(PieceType.ATTACKINGEAGLE.toString(), attackingEagle);
		pieces.put(PieceType.LEADERSHIPEAGLE.toString(), leadershipEagle);
		pieces.put(PieceType.VISIONARYEAGLE.toString(), visionaryEagle);

		pieces.put(PieceType.AGGRESSIVESHARK.toString(), aggressiveShark);
		pieces.put(PieceType.DEFENSIVESHARK.toString(), defensiveShark);
		pieces.put(PieceType.HEALINGSHARK.toString(), healingShark);

	}

	@Override
	public void movePiece(Piece piece, int newX, int newY) {

		board.removePiece(piece.getPosition().get("x"), piece.getPosition().get("y"));
		board.addPiece(newX, newY);

		piece.movePiece(newX, newY);
	}

	/*
	 * @return List<Piece> all active eagles
	 */
	@Override
	public List<Piece> getActiveEagles() {
		for (Piece piece : pieces.values()) {
			if (piece != null && piece.isActive() && (piece instanceof AttackerEagle || piece instanceof LeadershipEagle
					|| piece instanceof VisionaryEagle)) {
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

		return activeSharks;
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
	public Map<String, Piece> getAllPieces() {
		return pieces;
	}

	@Override
	public boolean checkSelectPiece(String occupiedPiece) {
		if (!pieces.containsKey(occupiedPiece)) {
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
		startGame = true;

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
	 * @param String playerType
	 * 
	 * @param bool turnOnTimer - set interval to change player every n seconds or
	 * not
	 */
	@Override
	public void setActivePlayer(String playerType, boolean turnOnTimer) {
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

		gameTimer = new java.util.Timer();
		gameTimer.schedule(new java.util.TimerTask() {
			@Override
			public void run() {
				setActivePlayer(playerType, true);
				String currentPlayerTurn = eaglePlayer.getActive() ? "Eagle" : "Shark";
				geCallback.timerNextMove(playerType, currentPlayerTurn);
			}
		}, 10000);

	}

	@Override
	public void cancelTimer() {
		String currentPlayerTurn = eaglePlayer.getActive() ? "Shark" : "Eagle";
		geCallback.nextMove(currentPlayerTurn);
		gameTimer.cancel();
	}

	@Override
	public boolean useAbility(String abilityName, Piece piece, Piece affectedPiece) {
		return piece.useAbility(abilityName, piece, affectedPiece);
	}

	@Override
	public boolean getStartGame() {
		return startGame;
	}

	@Override
	public GameEngineCallback getGameEngineCallback() {
		return geCallback;
	}

	@Override
	public void configBoardSize(int row, int col) {
		board = new Board(row, col);
		initializePiece();
	}

	@Override
	public void configNumPiece(int numPiece) {
		// TODO Auto-generated method stub
		if (numPiece == 4) {
			pieces = new HashMap<String, Piece>();

			Piece attackingEagle = pieceFactory.generatePiece(PieceType.ATTACKINGEAGLE.toString(),
					(board.getCol() / 2 - 1), 0);
			Piece visionaryEagle = pieceFactory.generatePiece(PieceType.VISIONARYEAGLE.toString(),
					board.getCol() / 2 + 1, 0);

			Piece aggressiveShark = pieceFactory.generatePiece(PieceType.AGGRESSIVESHARK.toString(),
					board.getCol() / 2 - 1, board.getRow() - 1);
			Piece healingShark = pieceFactory.generatePiece(PieceType.HEALINGSHARK.toString(), board.getCol() / 2 + 1,
					board.getRow() - 1);
			board.addPiece(attackingEagle.getPosition().get("x"), attackingEagle.getPosition().get("y"));
			board.addPiece(visionaryEagle.getPosition().get("x"), visionaryEagle.getPosition().get("y"));
			board.addPiece(aggressiveShark.getPosition().get("x"), aggressiveShark.getPosition().get("y"));
			board.addPiece(healingShark.getPosition().get("x"), healingShark.getPosition().get("y"));

			pieces.put(PieceType.ATTACKINGEAGLE.toString(), attackingEagle);
			pieces.put(PieceType.VISIONARYEAGLE.toString(), visionaryEagle);

			pieces.put(PieceType.AGGRESSIVESHARK.toString(), aggressiveShark);
			pieces.put(PieceType.HEALINGSHARK.toString(), healingShark);

		} else if (numPiece == 2) {
			pieces = new HashMap<String, Piece>();

			Piece leadershipEagle = pieceFactory.generatePiece(PieceType.LEADERSHIPEAGLE.toString(), board.getCol() / 2,
					1);
			Piece defensiveShark = pieceFactory.generatePiece(PieceType.DEFENSIVESHARK.toString(), board.getCol() / 2,
					board.getRow() - 2);
			pieces.put(PieceType.LEADERSHIPEAGLE.toString(), leadershipEagle);
			pieces.put(PieceType.DEFENSIVESHARK.toString(), defensiveShark);

		}
	}

}
