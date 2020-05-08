package model.engine;

import java.io.Serializable;
import java.util.Random;
import java.util.Timer;

import com.google.java.contract.Requires;

import controller.playinggamecontroller.MakingMovePropertyChangeListener;
import controller.playinggamecontroller.TimerPropertyChangeListener;
import model.board.Board;
import model.contract.EngineInterface;
import model.enumtype.TeamType;
import model.piece.commands.PieceOperator;
import model.player.Player;
import model.player.PlayerImpl;
import view.callback.GameEngineCallbackImpl;
import view.contract.GameEngineCallbackInterface;

/**
 *
 * @author Sefira
 *
 */
public class EngineImpl implements EngineInterface, Serializable {

	private static final long serialVersionUID = -5482363859150486331L;

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

	private Player eaglePlayer = new PlayerImpl(TeamType.EAGLE);

	private Player sharkPlayer = new PlayerImpl(TeamType.SHARK);

	private transient Timer gameTimer;

	private GameEngineCallbackInterface geCallback = new GameEngineCallbackImpl();

	private Board board;

	private PieceOperator pieceOperator;

	private int turn = 1;

	private int round;

	private boolean gameRunning = false;

	/**
	 * @return the singleton instance of the engine
	 */
	private EngineImpl() {
		board = new Board();
		pieceOperator = new PieceOperator(board, this);
		pieceOperator.initializePiece();
		geCallback.addProperytChangeListener(new TimerPropertyChangeListener());
		geCallback.addProperytChangeListener(new MakingMovePropertyChangeListener());
	}

	@Override
	public void loadGame(EngineImpl e) {
		engine = e;
	}

	/**
	 * stop the timer of the game
	 */
	@Override
	@Requires({ "eaglePlayer.getActive() == true || eaglePlayer.getActive() == false" })
	public void cancelTimer() {
		TeamType currentPlayer = eaglePlayer.getActive() ? TeamType.SHARK : TeamType.EAGLE;
		geCallback.nextMove(currentPlayer);
		if (gameTimer != null) {
			gameTimer.cancel();
		}
	}

	@Override
	public Board getBoard() {
		return board;
	}

	/**
	 * get the current player turn
	 * 
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
	 * return the initial active player, call this at the beginning of the program
	 * 
	 * @return (eaglePlayer || sharkPlayer)
	 */
	@Requires({ "eaglePlayer!= null", "sharkPlayer != null" })
	@Override
	public Player getInitialPlayerActivePlayer() {
		gameRunning = true;
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

	/**
	 * set the turn to the specified team in parameter
	 * 
	 * @param playerType  - the player to be activated
	 * @param turnOnTimer - whether to begin countdown or not
	 */
	@Override
	@Requires({ "playerType != null", "turnOnTimer == true || turnOnTimer == false" })
	public void setActivePlayer(TeamType playerType, boolean turnOnTimer) {

		TeamType nextPlayer;
		turn++;
		round = turn / 2;

		// System.out.println("turn: " + turn);
		System.out.println("round: " + round);

		if (playerType == TeamType.EAGLE) {
			this.eaglePlayer.setActive(true);
			this.sharkPlayer.setActive(false);
			nextPlayer = TeamType.SHARK;
		} else if (playerType == TeamType.SHARK) {
			this.eaglePlayer.setActive(false);
			this.sharkPlayer.setActive(true);
			nextPlayer = TeamType.EAGLE;
		} else {
			throw new IllegalArgumentException("invalid player type, must be eagle or shark");
		}
		if (turnOnTimer) {
			setActivePlayerTimer(nextPlayer);
		}
	}

	/**
	 * turn on the timer and loop call setActivePlayer to change every interval
	 * 
	 * @param playerType - the player to be activated next
	 */
	@Override
	@Requires({ "playerType != null" })
	public void setActivePlayerTimer(TeamType playerType) {
		gameTimer = new java.util.Timer();
		gameTimer.schedule(new java.util.TimerTask() {
			@Override
			public void run() {
				setActivePlayer(playerType, true);
				TeamType currentPlayer = eaglePlayer.getActive() ? TeamType.EAGLE : TeamType.SHARK;
				geCallback.timerNextMove(playerType, currentPlayer);
			}
		}, 10000);
	}

	@Override
	public PieceOperator pieceOperator() {
		return pieceOperator;
	}

	@Override
	public void cancelTimerPauseGame() {
		gameRunning = false;
		gameTimer.cancel();
	}

	@Override
	public void incrementUndo(TeamType teamType) {

		Player player = playerType(teamType);

		player.undoCounter(round);
	}

	@Override
	public boolean ableToUndo(TeamType teamType) {

		Player player = playerType(teamType);

		return player.ableToUndo(round);
	}

	private Player playerType(TeamType teamType) {

		if (teamType.equals(TeamType.EAGLE)) {
			return eaglePlayer;
		} else if (teamType.equals(TeamType.SHARK)) {
			return sharkPlayer;
		} else {
			throw new IllegalArgumentException("Invalid team type");
		}

	}

	@Override
	public boolean getGameCurrentlyRunning() {
		return gameRunning;
	}

	@Override
	public void setResumeGame() {
		gameRunning = true;
	}

	@Override
	public void configBoardSize(int boardSize) {
		board = new Board(boardSize);
		pieceOperator.initializePiece();
	}

	@Override
	public void configNumPiece(int numPiece) {

	}

}
