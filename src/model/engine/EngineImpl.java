package model.engine;

import java.util.Random;
import java.util.Timer;

import com.google.java.contract.Requires;

import controller.MakingMovePropertyChangeListener;
import controller.TimerPropertyChangeListener;
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

	private Player eaglePlayer = new PlayerImpl(TeamType.EAGLE);

	private Player sharkPlayer = new PlayerImpl(TeamType.SHARK);

	private Timer gameTimer;

	private GameEngineCallbackInterface geCallback = new GameEngineCallbackImpl();

	private Board board;

	private PieceOperator pieceOperator;

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
	 * set the turn to the specified team in parameter
	 * 
	 * @param playerType
	 *            - the player to be activated
	 * @param turnOnTimer
	 *            - whether to begin countdown or not
	 */
	@Override
	@Requires({ "playerType != null", "turnOnTimer == true || turnOnTimer == false" })
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
			throw new IllegalArgumentException("invalid player type, must be eagle or shark");
		}
		if (turnOnTimer) {
			setActivePlayerTimer(nextPlayer);
		}
	}

	/**
	 * turn on the timer and loop call setActivePlayer to change every interval
	 * 
	 * @param playerType
	 *            - the player to be activated next
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
	public void setStartGame() {
		startGame = true;
	}

	@Override
	public PieceOperator pieceOperator() {

		return pieceOperator;
	}

	@Override
	public void cancelTimerPauseGame() {
		// TODO Auto-generated method stub
		gameTimer.cancel();
	}


}
