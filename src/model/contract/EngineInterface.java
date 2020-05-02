package model.contract;

import model.board.Board;
import model.enumtype.TeamType;
import model.piece.commands.PieceOperator;
import model.player.Player;
import view.contract.GameEngineCallbackInterface;

/**
 * @author sefira
 *
 */
public interface EngineInterface {

	/**
	 * stop the timer of the game
	 */
	public void cancelTimer();

	// /**
	// * @param occupiedPieceType
	// * @return true if the piece is occupied, else false
	// */
	// public boolean checkSelectPiece(PieceType occupiedPieceType);

	/**
	 * @return all pieces
	 */
	// public Map<PieceType, PieceInterface> getAllPieces();

	public Board getBoard();

	/**
	 * get the current player turn
	 * 
	 * @return the active team
	 */
	public Player getCurrentActivePlayer();

	public GameEngineCallbackInterface getGameEngineCallback();

	/*
	 * return the initial active player, call this at the beginning of the program
	 * 
	 * @return (eaglePlayer || sharkPlayer)
	 */
	public Player getInitialPlayerActivePlayer();

	/*
	 * schedule timer to call setActivePlayer(String playerType, boolean
	 * turnOnTimer)
	 * 
	 * @return
	 */

	/**
	 * @param piece
	 *            - the piece to be moved
	 * @param newX
	 *            - new x position
	 * @param newY
	 *            - new y position Generate the pieces and put them on the board
	 */
	// public void movePiece(PieceInterface piece, int newX, int newY);

	/**
	 * set the turn to the specified team in parameter
	 * 
	 * @param playerType
	 *            - the player to be activated
	 * @param turnOnTimer
	 *            - whether to begin countdown or not
	 */
	public void setActivePlayer(TeamType playerType, boolean turnOnTimer);

	/**
	 * turn on the timer and loop call setActivePlayer to change every interval
	 * 
	 * @param playerType
	 *            - the player to be activated next
	 */
	public void setActivePlayerTimer(TeamType playerType);

	/*
	 * Set the selected piece status to active
	 * 
	 * @return boolean
	 */
	// public boolean setPieceActiveStatus(PieceInterface piece, boolean isActive);


	public PieceOperator pieceOperator();

	// TED
	public void cancelTimerPauseGame();

	public boolean ableToUndo(TeamType teamType);

	public void incrementUndo(TeamType teamType);

	public boolean getGameCurrentlyRunning();

	public void setResumeGame();

	/**
	 * Intention: to keep track of whether the HealingShark can use healing ability or not
	 * 0 = HealingShark can perform healing ability
	 * 1 && 2 = HealingShark unable to perform healing ability
	 * @return 0 || 1 || 2
	 * @author Chanboth Som
	 */
	public int getHealingAbilityCounter();

	/**
	 * Intention: increment the counter when the HealingShark has performed a healing ability
	 * @author Chanboth Som
	 */
	public void incrementHealingAbilityCounter();

	/**
	 * Intention: reset the counter back to 0 to allow the HealingShark to perform
	 * 			  the ability in the future
	 * @author Chanboth Som
	 */
	public void resetHealingAbilityCounter();

	/**
	 * Intention: to be used across all 3 eagles.
	 * To keep track when to give Healing Shark the ability to heal on its turn.
	 * @author Chanboth Som
	 */
	public void eagleCheckingHealingSharkAbility();
}
