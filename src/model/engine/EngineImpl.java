package model.engine;

import java.util.EnumMap;
import java.util.Map;
import java.util.Random;
import java.util.Timer;

import controller.MakingMovePropertyChangeListener;
import controller.TimerPropertyChangeListener;
import model.board.Board;
import model.contract.EngineInterface;
import model.contract.PieceInterface;
import model.enumtype.PieceType;
import model.enumtype.TeamType;
import model.piece.PieceFactory;
import model.player.Player;
import model.player.PlayerImpl;
import view.callback.GameEngineCallbackImpl;
import view.contract.GameEngineCallbackInterface;

/**
 *
 * @author Sefira
 *
 */
public class EngineImpl implements EngineInterface {
	private static EngineInterface engine = null;

	/**
	 * @return the singleton instance of the engine
	 */
	public static EngineInterface getSingletonInstance() {
		if (engine == null) {
			engine = new EngineImpl();
		}
		return engine;
	}

	private boolean startGame = false;

	private Map<PieceType, PieceInterface> pieces = new EnumMap<PieceType, PieceInterface>(
			PieceType.class);
	private Player eaglePlayer = new PlayerImpl(TeamType.EAGLE);

	private Player sharkPlayer = new PlayerImpl(TeamType.SHARK);

	private Timer gameTimer;

	private GameEngineCallbackInterface geCallback = new GameEngineCallbackImpl();

	private Board board;
	
	
	/**
	 * @return the singleton instance of the engine
	 */
	private EngineImpl() {
		board = new Board();
		initializePiece();
		geCallback.addProperytChangeListener(new TimerPropertyChangeListener());
		geCallback.addProperytChangeListener(
				new MakingMovePropertyChangeListener());
	}
	
	/**
	 *  stop the timer of the game
	 */
	@Override
	public void cancelTimer() {
		TeamType currentPlayer = eaglePlayer.getActive()
				? TeamType.SHARK
				: TeamType.EAGLE;
		geCallback.nextMove(currentPlayer);
		if (gameTimer != null) {
			gameTimer.cancel();
		}
	}
	
	/**
	 * @param occupiedPieceType 
	 * @return true if the piece is occupied, else false
	 */
	@Override
	public boolean checkSelectPiece(PieceType occupiedPieceType) {
		if (!pieces.containsKey(occupiedPieceType)) {
			return false;
		}
		return true;
	}
	
	/**
	 * @return all pieces
	 */
	@Override
	public Map<PieceType, PieceInterface> getAllPieces() {
		return pieces;
	}

	
	@Override
	public Board getBoard() {
		return board;
	}
	
	/**
	 * get the current player turn
	 * @return the active team
	 */
	@Override
	public Player getCurrentActivePlayer() {

		if (eaglePlayer.getActive())
			return eaglePlayer;
		else
			return sharkPlayer;

	}
	
	@Override
	public GameEngineCallbackInterface getGameEngineCallback() {
		return geCallback;
	}

	/*
	 * return the initial active player, call this at the beginning of the
	 * program
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

	@Override
	public boolean getStartGame() {
		return startGame;
	}

	/**
	 *  Generate the pieces and put them on the board
	 */
	public void initializePiece() {
		int boardSize = getBoard().getSize();
		for (PieceType pt : PieceType.values()) {
			PieceInterface piece = PieceFactory.generatePiece(pt, boardSize);
			board.addPiece(piece.getPosition().get("x"),
					piece.getPosition().get("y"));
			pieces.put(pt, piece);
		}
	}
	
	/**
	 * @param piece - the piece to be moved
	 * @param newX - new x position
	 * @param newY - new y position
	 *  Generate the pieces and put them on the board
	 */
	@Override
	public void movePiece(PieceInterface piece, int x, int y) {
		board.removePiece(piece.getPosition().get("x"),
				piece.getPosition().get("y"));
		board.addPiece(x, y);
		piece.movePiece(x, y);
	}
	
	/**
	 * set the turn to the specified team in parameter
	 * 
	 * @param playerType - the player to be activated
	 * @param turnOnTimer - whether to begin countdown or not
	 */
	@Override
	public void setActivePlayer(TeamType playerType, boolean turnOnTimer) {
		TeamType nextPlayer;
		if (playerType == TeamType.EAGLE) {
			this.eaglePlayer.setActive(true);
			this.sharkPlayer.setActive(false);
			nextPlayer = TeamType.SHARK;
		} else if (playerType == TeamType.SHARK) {
			this.eaglePlayer.setActive(false);
			this.sharkPlayer.setActive(true);
			nextPlayer = TeamType.EAGLE;
		} else {
			throw new IllegalArgumentException(
					"invalid player type, must be eagle or shark");
		}
		if (turnOnTimer) {
			setActivePlayerTimer(nextPlayer);
		}
	}
	
	/**
	 * turn on the timer and loop call setActivePlayer to change every interval
	 * @param playerType - the player to be activated next
	 */
	@Override
	public void setActivePlayerTimer(TeamType playerType) {
		gameTimer = new java.util.Timer();
		gameTimer.schedule(new java.util.TimerTask() {
			@Override
			public void run() {
				setActivePlayer(playerType, true);
				TeamType currentPlayer = eaglePlayer.getActive()
						? TeamType.EAGLE
						: TeamType.SHARK;
				geCallback.timerNextMove(playerType, currentPlayer);
			}
		}, 10000);
	}
	
	/*
	 * Set the selected piece status to active
	 * @return boolean
	 */
	@Override
	public boolean setPieceActiveStatus(PieceInterface piece,
			boolean isActive) {
		try {
			piece.setActive(isActive);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public void setStartGame() {
		startGame = true;
	}
}
