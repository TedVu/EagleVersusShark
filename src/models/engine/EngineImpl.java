package models.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;

import controller.AssetHelper;
import controller.MakingMovePropertyChangeListener;
import controller.TimerPropertyChangeListener;
import models.board.Board;
import models.pieces.AttackerEagle;
import models.pieces.LeadershipEagle;
import models.pieces.Piece;
import models.pieces.PieceFactory;
import models.pieces.VisionaryEagle;
import models.enumeration.Player;
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
	private boolean eagleActive = false;
	private boolean sharkActive = false;
	private Map<String, Piece> pieces = new HashMap<String, Piece>();
	private PieceFactory pieceFactory = new PieceFactory();

	private List<Piece> activeEagles = new ArrayList<Piece>();
	private List<Piece> activeSharks = new ArrayList<Piece>();
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
		AssetHelper.populate();
		Piece eaglePiece1 = pieceFactory.generatePiece("AttackingEagle", AssetHelper.initialPosAttackingEagle.get("x"),
				AssetHelper.initialPosAttackingEagle.get("y"));
		Piece eaglePiece2 = pieceFactory.generatePiece("LeadershipEagle", AssetHelper.initialPosLeadershipEagle.get("x"),
				AssetHelper.initialPosLeadershipEagle.get("y"));
		Piece eaglePiece3 = pieceFactory.generatePiece("VisionaryEagle", AssetHelper.initialPosVisionaryEagle.get("x"),
				AssetHelper.initialPosVisionaryEagle.get("y"));

		Piece sharkPiece1 = pieceFactory.generatePiece("AggressiveShark", AssetHelper.initialPosAggressiveShark.get("x"),
				AssetHelper.initialPosAggressiveShark.get("y"));
		Piece sharkPiece2 = pieceFactory.generatePiece("DefensiveShark", AssetHelper.initialPosDefensiveShark.get("x"),
				AssetHelper.initialPosDefensiveShark.get("y"));
		Piece sharkPiece3 = pieceFactory.generatePiece("HealingShark", AssetHelper.initialPosHealingShark.get("x"),
				AssetHelper.initialPosHealingShark.get("y"));

		board.addPiece(0, 3);
		board.addPiece(1, 4);
		board.addPiece(0, 5);

		board.addPiece(8, 3);
		board.addPiece(7, 4);
		board.addPiece(8, 5);

		pieces.put("AttackingEagle", eaglePiece1);
		pieces.put("LeadershipEagle", eaglePiece2);
		pieces.put("VisionaryEagle", eaglePiece3);

		pieces.put("AggressiveShark", sharkPiece1);
		pieces.put("DefensiveShark", sharkPiece2);
		pieces.put("HealingShark", sharkPiece3);

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

		if (eagleActive)
			return Player.EAGLE;
		else
			return Player.SHARK;

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
			eagleActive = true;
			sharkActive = false;
			activePlayer = Player.EAGLE;
		} else {
			eagleActive = false;
			sharkActive = true;
			activePlayer = Player.SHARK;
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
	public void setActivePlayer(Player playerType, boolean turnOnTimer) {
		Player nextPlayer;
		if (playerType == Player.EAGLE) {
			eagleActive = true;
			sharkActive = false;
			nextPlayer = Player.SHARK;
		} else if (playerType == Player.SHARK) {
			eagleActive = false;
			sharkActive = true;
			nextPlayer = Player.SHARK;
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
	public void setActivePlayerTimer(Player playerType) {

		gameTimer = new java.util.Timer();
		gameTimer.schedule(new java.util.TimerTask() {
			@Override
			public void run() {
				setActivePlayer(playerType, true);
				Player currentPlayerTurn = eagleActive ? Player.EAGLE : Player.SHARK;
				geCallback.timerNextMove(playerType, currentPlayerTurn);
			}
		}, 10000);

	}

	@Override
	public void cancelTimer() {
		Player currentPlayerTurn = sharkActive ? Player.EAGLE : Player.SHARK;
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

}
