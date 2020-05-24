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

	public GameTurn gameTurn();

	public GameBoard gameBoard();

	public GamePiece pieceOperator();

	public int getTotalNumPiece();

	public void configBoardSize(int boardSize);

	public void configNumPiece(int pieceNumberSelection);

	public void loadGame(EngineImpl e);

	public PieceCommands getPieceCommands();

	public boolean endGame();

}
