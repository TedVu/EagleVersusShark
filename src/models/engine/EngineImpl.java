package models.engine;

import java.util.EnumMap;
import java.util.Map;
import java.util.Random;
import java.util.Timer;

import asset.PieceType;
import asset.TeamType;
import controller.MakingMovePropertyChangeListener;
import controller.TimerPropertyChangeListener;
import models.board.Board;
import models.pieces.Piece;
import models.pieces.PieceFactory;
import models.player.Player;
import models.player.PlayerImpl;
import view.callbacks.GameEngineCallbackImpl;
import view.interfaces.GameEngineCallback;

/**
 *
 * @author Sefira
 *
 */
public class EngineImpl implements Engine {
	private static Engine engine = null;

	/**
	 * @return
	 */
	public static Engine getSingletonInstance() {
		if (engine == null) {
			engine = new EngineImpl();
		}
		return engine;
	}
	private boolean startGame = false;

	private Map<PieceType, Piece> pieces = new EnumMap<PieceType, Piece>(PieceType.class);
	private PieceFactory pieceFactory = new PieceFactory();
	private Player eaglePlayer = new PlayerImpl(TeamType.EAGLE);

	private Player sharkPlayer = new PlayerImpl(TeamType.SHARK);

	private Timer gameTimer;

	private GameEngineCallback geCallback = new GameEngineCallbackImpl();

	private Board board;

	private EngineImpl() {
		board = new Board();
		initializePiece();
		geCallback.addProperytChangeListener(new TimerPropertyChangeListener());
		geCallback.addProperytChangeListener(new MakingMovePropertyChangeListener());
	}

	@Override
	public void cancelTimer() {
		TeamType currentPlayer = eaglePlayer.getActive() ? TeamType.SHARK : TeamType.EAGLE;
		geCallback.nextMove(currentPlayer);
		if (gameTimer != null) {
			gameTimer.cancel();
		}
	}

	@Override
	public boolean checkSelectPiece(PieceType occupiedPieceType) {
		if (!pieces.containsKey(occupiedPieceType)) {
			return false;
		}
		return true;
	}

	@Override
	public Map<PieceType, Piece> getAllPieces() {
		return pieces;
	}

	/**
	 *
	 */
	@Override
	public Board getBoard() {
		return board;
	}

	@Override
	public Player getCurrentActivePlayer() {

		if (eaglePlayer.getActive())
			return eaglePlayer;
		else
			return sharkPlayer;

	}

	@Override
	public GameEngineCallback getGameEngineCallback() {
		return geCallback;
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

	@Override
	public boolean getStartGame() {
		return startGame;
	}

	/**
	 * 
	 */
	public void initializePiece() {
		PieceType.onBoardSize(9);
		for (PieceType pt : PieceType.values()) {
			Piece piece = pieceFactory.generatePiece(pt);
			board.addPiece(piece.getPosition().get("x"), piece.getPosition().get("y"));
			pieces.put(pt, piece);
		}
	}

	@Override
	public void movePiece(Piece piece, int x, int y) {
		board.removePiece(piece.getPosition().get("x"), piece.getPosition().get("y"));
		board.addPiece(x, y);
		piece.movePiece(x, y);
	}

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
			throw new IllegalArgumentException("invalid player type, must be eagle or shark");
		}
		if (turnOnTimer) {
			setActivePlayerTimer(nextPlayer);
		}
	}

	@Override
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
	public boolean setPieceActiveStatus(Piece piece, boolean isActive) {
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
