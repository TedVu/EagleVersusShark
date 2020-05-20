package model.engine;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.concurrent.ThreadLocalRandom;

import model.board.Cell;
import model.board.GameBoard;
import model.contract.Engine;
import model.contract.Piece;
import model.enumtype.TeamType;
import model.piece.GamePiece;
import model.piece.commands.PieceCommands;
import model.player.GameTurn;
import model.player.Player;
import model.player.PlayerImpl;

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
		this.board = new GameBoard(boardSize);
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
		pieceCommands = e.getPieceCommands();

	}

	public GamePiece getPieceOperator() {
		return gamePiece;
	}

	@Override
	public GameBoard gameBoard() {
		return this.board;
	}

	@Override
	public GamePiece pieceOperator() {
		return gamePiece;
	}

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
