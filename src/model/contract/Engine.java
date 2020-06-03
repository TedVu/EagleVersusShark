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
	 * board size configuration based on user's preference
	 */
	public void configBoardSize(int boardSize, boolean hasObstacle);

	/**
	 * number of piece configuration based on user's preference
	 */
	public void configNumPiece(int pieceNumberSelection);

	/**
	 * end the current gameplay
	 */
	public boolean endGame();

	/**
	 * @return the object that do board related operations
	 */
	public GameBoard gameBoard();

	/**
	 * @return the object that controls game turn and player
	 */
	public GameTurn gameTurn();

	/**
	 * @return the object that contains the command operations of the piece
	 */
	public PieceCommands getPieceCommands();

	/**
	 * load the chosen saved gameplay
	 */
	public void loadGame(EngineImpl e);

	/**
	 * @return the object that controls piece related operations
	 */
	public GamePiece pieceOperator();

}
