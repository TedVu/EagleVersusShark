package model.player;

import java.io.Serializable;
import java.util.Random;
import java.util.Timer;

import com.google.java.contract.Requires;

import controller.playinggamecontroller.MakingMovePropertyChangeListener;
import controller.playinggamecontroller.TimerPropertyChangeListener;
import model.contract.Engine;
import model.contract.Player;
import model.enumtype.TeamType;
import view.callback.GameEngineCallbackImpl;
import view.contract.GameEngineCallbackInterface;

public class GameTurn implements Serializable {

	private static final long serialVersionUID = -2279064566555330259L;

	private Player eaglePlayer = new PlayerImpl(TeamType.EAGLE);
	private Player sharkPlayer = new PlayerImpl(TeamType.SHARK);
	private transient Timer gameTimer;
	private GameEngineCallbackInterface geCallback = new GameEngineCallbackImpl();
	private int turn = 1;
	private int round;
	private boolean gameRunning = false;

	public GameTurn(Engine engine) {
		geCallback.addProperytChangeListener(new TimerPropertyChangeListener());
		geCallback.addProperytChangeListener(new MakingMovePropertyChangeListener());
	}

	public GameEngineCallbackInterface getGameEngineCallback() {
		return geCallback;
	}

	/**
	 * stop the timer of the game
	 */
	@Requires({ "eaglePlayer.getActive() == true || eaglePlayer.getActive() == false" })
	public void cancelTimer() {
		TeamType currentPlayer = eaglePlayer.getActive() ? TeamType.SHARK : TeamType.EAGLE;
		geCallback.nextMove(currentPlayer);
		if (gameTimer != null) {
			gameTimer.cancel();
		}
	}

	/**
	 * get the current player turn
	 * 
	 * @return the active team
	 */
	public Player getCurrentActivePlayer() {
		if (eaglePlayer.getActive()) {
			return eaglePlayer;
		} else {
			return sharkPlayer;
		}
	}

	/**
	 * return the initial active player, call this at the beginning of the program
	 * 
	 * @return (eaglePlayer || sharkPlayer)
	 */
	@Requires({ "eaglePlayer!= null", "sharkPlayer != null" })
	public Player getInitialPlayerActivePlayer() {
		gameRunning = true;
		Player activePlayer;
		Random rand = new Random();
		int randomValue = 0;
		if (eaglePlayer.getActive() == true) {
			randomValue = 0;
		} else if (sharkPlayer.getActive() == true) {
			randomValue = 1;
		} else {
			randomValue = rand.nextInt() % 2;
		}
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
	@Requires({ "playerType != null", "turnOnTimer == true || turnOnTimer == false" })
	public void setActivePlayer(TeamType playerType, boolean turnOnTimer) {

		TeamType nextPlayer;
		turn++;
		round = turn / 2;

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

	public void endGame(TeamType teamWin) {
		cancelTimerPauseGame();
		geCallback.endGame(teamWin);
	}

	public void cancelTimerPauseGame() {
		gameRunning = false;
		gameTimer.cancel();
	}

	public boolean getGameCurrentlyRunning() {
		return gameRunning;
	}

	public void setResumeGame() {
		gameRunning = true;
	}

	public void incrementUndo(TeamType teamType) {
		Player player = playerType(teamType);
		player.undoCounter(round);
	}

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

}
