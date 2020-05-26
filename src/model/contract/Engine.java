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
	 * @return
	 */
	public GameTurn gameTurn();

	/**
	 * @return
	 */
	public GameBoard gameBoard();

	/**
	 * @return
	 */
	public GamePiece pieceOperator();

	/**
	 * @param boardSize
	 * @param hasObstacle
	 */
	public void configBoardSize(int boardSize, boolean hasObstacle);

	/**
	 * @param pieceNumberSelection
	 */
	public void configNumPiece(int pieceNumberSelection);

	/**
	 * @param e
	 */
	public void loadGame(EngineImpl e);

	/**
	 * @return
	 */
	public PieceCommands getPieceCommands();

	/**
	 * @return
	 */
	public boolean endGame();

}
