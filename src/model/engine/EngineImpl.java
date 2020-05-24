package model.engine;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import model.board.Cell;
import model.contract.Engine;
import model.contract.Piece;
import model.enumtype.TeamType;
import model.piece.commands.PieceCommands;
import model.player.GameTurn;

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


	private GameBoard board;

	private GamePiece gamePiece;

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
		return gameTurn;
	}

	@Override
	public PieceCommands getPieceCommands() {
		return pieceCommands;
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

	private boolean checkPiecesActiveWinningCondition() {
		if (pieceOperator().getActiveEagles().size() == 0) {
			gameTurn.endGame(TeamType.SHARK);
			return true;
		} else if (pieceOperator().getActiveSharks().size() == 0) {
			gameTurn.endGame(TeamType.EAGLE);
			return true;
		}
		return false;
	}

	private boolean checkPieceEnterMasterCellWinningCondition() {
		for (Piece sharks : pieceOperator().getActiveSharks()) {
			Cell cntPos = board.getCell(sharks.getPosition().get("x"), sharks.getPosition().get("y"));
			if (cntPos.equals(board.getEagleMasterCell())) {
				gameTurn.endGame(TeamType.SHARK);
				return true;
			}
		}

		for (Piece eagle : pieceOperator().getActiveEagles()) {
			Cell cntPos = board.getCell(eagle.getPosition().get("x"), eagle.getPosition().get("y"));
			if (cntPos.equals(board.getSharkMasterCell())) {
				gameTurn.endGame(TeamType.EAGLE);
				return true;
			}
		} 
		return false;
	}

	/*
	 * called at controllermodelfacade inside updateStateModelForNextTurn()
	 */
	@Override
	public boolean endGame() {
		if (checkPiecesActiveWinningCondition()) {
			return true;
		}

		if (checkPieceEnterMasterCellWinningCondition()) {
			return true;
		}

		return false;
	}
}
