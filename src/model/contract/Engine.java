package model.contract;

import model.board.GameBoard;
import model.engine.EngineImpl;
import model.engine.GamePiece;
import model.piece.commands.PieceCommands;
import model.player.GameTurn;

/**
 * @author Sefira
 *
 */
public interface Engine {

	/**
	 * @param boardSize
	 * @param hasObstacle
	 */
	public void configBoard(int boardSize, boolean hasObstacle);

	/**
	 * @param pieceNumberSelection
	 */
	public void configNumPiece(int pieceNumberSelection);

	/**
	 * @return
	 */
	public boolean endGame();

	/**
	 * @return
	 */
	public GameBoard gameBoard();

	/**
	 * @return
	 */
	public GameTurn gameTurn();

	/**
	 * @return
	 */
	public PieceCommands getPieceCommands();

	/**
	 * @param e
	 */
	public void loadGame(EngineImpl e);

	/**
	 * @return
	 */
	public GamePiece pieceOperator();

}
