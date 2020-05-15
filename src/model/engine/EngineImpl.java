package model.engine;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.concurrent.ThreadLocalRandom;

import com.google.java.contract.Requires;

import controller.playinggamecontroller.MakingMovePropertyChangeListener;
import controller.playinggamecontroller.TimerPropertyChangeListener;
import model.board.GameBoard;
import model.board.Cell;
import model.contract.Engine;
import model.contract.Piece;
import model.enumtype.TeamType;
import model.piece.GamePiece;
import model.piece.commands.PieceCommands;
import model.player.GameTurn;
import model.player.Player;
import model.player.PlayerImpl;
import view.callback.GameEngineCallbackImpl;
import view.contract.GameEngineCallbackInterface;

/**
 *
 * @author Sefira
 *
 */
public class EngineImpl implements Engine, Serializable {

	private static final long serialVersionUID = -5482363859150486331L;

	private static Engine engine = null;

	/**
	 * @return the singleton instance of the engine
	 */
	public static Engine getSingletonInstance() {
		if (engine == null) {
			engine = new EngineImpl();
		}
		return engine;
	}

	private Player eaglePlayer = new PlayerImpl(TeamType.EAGLE);

	private Player sharkPlayer = new PlayerImpl(TeamType.SHARK);

	private transient Timer gameTimer;

//	private GameEngineCallbackInterface geCallback = new GameEngineCallbackImpl();

	private GameBoard board;

	private GamePiece gamePiece;

	private int turn = 1;

	private int round;

	private boolean gameRunning = false;

	private int totalNumPiece;

	private PieceCommands pieceCommands;
	
	private GameTurn gameTurn;

	/**
	 * @return the singleton instance of the engine
	 */
	private EngineImpl() {

		// default game when hitting start without config
		totalNumPiece = 6;
		board = new GameBoard(9);
		gamePiece = new GamePiece(this);
		pieceCommands = new PieceCommands(this);
		gameTurn = new GameTurn(this);
		
	}
	
	

	@Override
	public GameTurn gameTurn() {
		// TODO Auto-generated method stub
		return gameTurn;
	}



	@Override
	public PieceCommands getPieceCommands() {
		return pieceCommands;
	}

	@Override
	public void configBoardSize(int boardSize) {
		board = new GameBoard(boardSize);
		gamePiece.setBoard(board);

	}

	@Override
	public void configNumPiece(int numPiece) {
		gamePiece = new GamePiece(this);

		if (numPiece == 6) {
			gamePiece.initializeDefaultPiece();
		} else if (numPiece == 4) {
			gamePiece.initialize4Piece();
			totalNumPiece = 4;
		} else if (numPiece == 2) {
			gamePiece.initialize2Piece();
			totalNumPiece = 2;
		}

	}

	@Override
	public void loadGame(EngineImpl e) {
		board = e.gameBoard();
		gamePiece = e.getPieceOperator();
		pieceCommands =e.getPieceCommands();

	}

	public GamePiece getPieceOperator() {
		return gamePiece;
	}

//	/**
//	 * stop the timer of the game
//	 */
//	@Override
//	@Requires({ "eaglePlayer.getActive() == true || eaglePlayer.getActive() == false" })
//	public void cancelTimer() {
//		TeamType currentPlayer = eaglePlayer.getActive() ? TeamType.SHARK : TeamType.EAGLE;
//		geCallback.nextMove(currentPlayer);
//		if (gameTimer != null) {
//			gameTimer.cancel();
//		}
//	}

	@Override
	public GameBoard gameBoard() {
		return board;
	}

//	/**
//	 * get the current player turn
//	 * 
//	 * @return the active team
//	 */
//	@Override
//	public Player getCurrentActivePlayer() {
//		if (eaglePlayer.getActive())
//			return eaglePlayer;
//		else
//			return sharkPlayer;
//
//	}

//	@Override
//	public GameEngineCallbackInterface getGameEngineCallback() {
//		return geCallback;
//	}

//	/*
//	 * return the initial active player, call this at the beginning of the program
//	 * 
//	 * @return (eaglePlayer || sharkPlayer)
//	 */
//	@Requires({ "eaglePlayer!= null", "sharkPlayer != null" })
//	@Override
//	public Player getInitialPlayerActivePlayer() {
//		gameRunning = true;
//		Player activePlayer;
//		Random rand = new Random();
//		int randomValue = 0;
//		if (eaglePlayer.getActive() == true) {
//			randomValue = 0;
//		} else if (sharkPlayer.getActive() == true) {
//			randomValue = 1;
//		} else {
//			randomValue = rand.nextInt() % 2;
//		}
//		if (randomValue == 0) {
//			this.eaglePlayer.setActive(true);
//			this.sharkPlayer.setActive(false);
//			activePlayer = eaglePlayer;
//		} else {
//			this.eaglePlayer.setActive(false);
//			this.sharkPlayer.setActive(true);
//			activePlayer = sharkPlayer;
//		}
//		return activePlayer;
//	}

//	/**
//	 * set the turn to the specified team in parameter
//	 * 
//	 * @param playerType
//	 *            - the player to be activated
//	 * @param turnOnTimer
//	 *            - whether to begin countdown or not
//	 */
//	@Override
//	@Requires({ "playerType != null", "turnOnTimer == true || turnOnTimer == false" })
//	public void setActivePlayer(TeamType playerType, boolean turnOnTimer) {
//
//		TeamType nextPlayer;
//		turn++;
//		round = turn / 2;
//
//		// System.out.println("turn: " + turn);
//		System.out.println("round: " + round);
//
//		if (playerType == TeamType.EAGLE) {
//			this.eaglePlayer.setActive(true);
//			this.sharkPlayer.setActive(false);
//			nextPlayer = TeamType.SHARK;
//		} else if (playerType == TeamType.SHARK) {
//			this.eaglePlayer.setActive(false);
//			this.sharkPlayer.setActive(true);
//			nextPlayer = TeamType.EAGLE;
//		} else {
//			throw new IllegalArgumentException("invalid player type, must be eagle or shark");
//		}
//		if (turnOnTimer) {
//			setActivePlayerTimer(nextPlayer);
//		}
//	}

//	/**
//	 * turn on the timer and loop call setActivePlayer to change every interval
//	 * 
//	 * @param playerType
//	 *            - the player to be activated next
//	 */
//	@Override
//	@Requires({ "playerType != null" })
//	public void setActivePlayerTimer(TeamType playerType) {
//		gameTimer = new java.util.Timer();
//		gameTimer.schedule(new java.util.TimerTask() {
//			@Override
//			public void run() {
//				setActivePlayer(playerType, true);
//				TeamType currentPlayer = eaglePlayer.getActive() ? TeamType.EAGLE : TeamType.SHARK;
//				geCallback.timerNextMove(playerType, currentPlayer);
//			}
//		}, 10000);
//	}

	@Override
	public GamePiece pieceOperator() {
		return gamePiece;
	}

//	@Override
//	public void cancelTimerPauseGame() {
//		gameRunning = false;
//		gameTimer.cancel();
//	}

//	@Override
//	public void incrementUndo(TeamType teamType) {
//
//		Player player = playerType(teamType);
//
//		player.undoCounter(round);
//	}

//	@Override
//	public boolean ableToUndo(TeamType teamType) {
//
//		Player player = playerType(teamType);
//
//		return player.ableToUndo(round);
//	}
//
//	private Player playerType(TeamType teamType) {
//
//		if (teamType.equals(TeamType.EAGLE)) {
//			return eaglePlayer;
//		} else if (teamType.equals(TeamType.SHARK)) {
//			return sharkPlayer;
//		} else {
//			throw new IllegalArgumentException("Invalid team type");
//		}
//
//	}

//	@Override
//	public boolean getGameCurrentlyRunning() {
//		return gameRunning;
//	}
//
//	@Override
//	public void setResumeGame() {
//		gameRunning = true;
//	}

	@Override
	public int getTotalNumPiece() {
		return this.totalNumPiece;
	}

	@Override
	public void configObstacle(boolean hasObstacle) {
		
		Set<Cell> specialPos = new HashSet<>();
		
		if (hasObstacle) {
			for (Piece pieces : this.gamePiece.getAllPieces().values()) {
				specialPos.add(board.getCell(pieces.getPosition().get("x"), pieces.getPosition().get("y")));
			}
			specialPos.add(board.getCell(board.getSize() / 2, 0));
			specialPos.add(board.getCell(board.getSize() / 2, board.getSize() - 1));

			int min = 0, max = board.getSize();
			// 4 obstacles
			int numObstacle = 1;
			while (numObstacle <= 4) {
				// exclusive the ceiling
				int randomX = ThreadLocalRandom.current().nextInt(min, max);
				int randomY = ThreadLocalRandom.current().nextInt(min, max);

				// generate random until not either the special pos
				if (!specialPos.contains(board.getCell(randomX, randomY))
						&& !board.getCell(randomX, randomY).isObstacle()) {
					board.getCell(randomX, randomY).setObstacle();
					++numObstacle;
				}

			}

		}
	}
}
