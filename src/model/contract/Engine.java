package model.contract;

import model.board.GameBoard;
import model.engine.EngineImpl;
import model.enumtype.TeamType;
import model.piece.GamePiece;
import model.piece.commands.PieceCommands;
import model.player.GameTurn;
import model.player.Player;
import view.contract.GameEngineCallbackInterface;

/**
 * @author sefira
 *
 */
public interface Engine {
	
	public GameTurn gameTurn();

	/**
	 * stop the timer of the game
	 */
//	public void cancelTimer();

	// /**
	// * @param occupiedPieceType
	// * @return true if the piece is occupied, else false
	// */
	// public boolean checkSelectPiece(PieceType occupiedPieceType);

	/**
	 * @return all pieces
	 */
	// public Map<PieceType, PieceInterface> getAllPieces();

	public GameBoard getBoard();

	/**
	 * get the current player turn
	 * 
	 * @return the active team
	 */
//	public Player getCurrentActivePlayer();

//	public GameEngineCallbackInterface getGameEngineCallback();

	/*
	 * return the initial active player, call this at the beginning of the program
	 * 
	 * @return (eaglePlayer || sharkPlayer)
	 */
//	public Player getInitialPlayerActivePlayer();

	/*
	 * schedule timer to call setActivePlayer(String playerType, boolean
	 * turnOnTimer)
	 * 
	 * @return
	 */

	/**
	 * @param piece - the piece to be moved
	 * @param newX  - new x position
	 * @param newY  - new y position Generate the pieces and put them on the board
	 */
	// public void movePiece(PieceInterface piece, int newX, int newY);

	/**
	 * set the turn to the specified team in parameter
	 * 
	 * @param playerType  - the player to be activated
	 * @param turnOnTimer - whether to begin countdown or not
	 */
//	public void setActivePlayer(TeamType playerType, boolean turnOnTimer);

	/**
	 * turn on the timer and loop call setActivePlayer to change every interval
	 * 
	 * @param playerType - the player to be activated next
	 */
//	public void setActivePlayerTimer(TeamType playerType);

	/*
	 * Set the selected piece status to active
	 * 
	 * @return boolean
	 */
	// public boolean setPieceActiveStatus(PieceInterface piece, boolean isActive);

	public GamePiece pieceOperator();

	// TED
//	public void cancelTimerPauseGame();

//	public boolean ableToUndo(TeamType teamType);
//
//	public void incrementUndo(TeamType teamType);
//
//	public boolean getGameCurrentlyRunning();
//
//	public void setResumeGame();
	
	
	//total num piece for 
	public int getTotalNumPiece();

	// Board Set up

	public void configBoardSize(int boardSize);

	public void configNumPiece(int pieceNumberSelection);

	public void configObstacle(boolean hasObstacle);
	
	public void loadGame(EngineImpl e);
	
	public PieceCommands getPieceCommands();
	
	
}
