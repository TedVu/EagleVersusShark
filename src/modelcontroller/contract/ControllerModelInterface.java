package modelcontroller.contract;

import java.util.Map;

import model.contract.Player;
import model.enumtype.PieceType;
import model.enumtype.TeamType;

/**
 * @author ted &#38; kevin
 *
 */
public interface ControllerModelInterface {

	/**
	 * @return
	 */
	public Player getCurrentActivePlayer();

	/**
	 * @return
	 */
	public Player getInitialActivePlayer();

	/**
	 * @return
	 */
	public boolean getGameCurrentlyRunning();

	/**
	 * 
	 */
	public void cancelTimerPauseGame();

	/**
	 * @param teamType
	 */
	public void setTurnStartingGame(TeamType teamType);

	/**
	 * 
	 */
	public void setResumeGame();

	/**
	 * 
	 */
	public void setAlreadyUseUndo();

	/**
	 * @param pieceType
	 * @return
	 */
	public boolean checkCorrectTurnOfSelectedPiece(PieceType pieceType);

	/**
	 * @param newPos
	 * @param pieceType
	 */
	public void updateModelAfterMovingPiece(Map<String, Integer> newPos, PieceType pieceType);

	/**
	 * @param teamName
	 */
	public void updateModelStateForNextTurn(TeamType teamName);

	/**
	 * @param affectedPieceEnum
	 */
	public void updateModelStateSwapPiece(PieceType affectedPieceEnum);

	/**
	 * @param affectedPieceEnum
	 * @param piecetProtect
	 */
	public void updateModelStateProtectPiece(PieceType affectedPieceEnum, PieceType piecetProtect);

	/**
	 * @param affectedPieceEnum
	 * @param isMode
	 */
	public void updateModelAttackingEagleCapture(PieceType affectedPieceEnum, boolean isMode);

	/**
	 * @param affectedPieceEnum
	 */
	public void updateModelStateAggressiveSharkCapture(PieceType affectedPieceEnum);

	/**
	 * @param affectedPieceEnum
	 */
	public void updateModelStateHealingSharkRevive(PieceType affectedPieceEnum);

	/**
	 * @param affectedPieceEnum
	 */
	public void updateModelAfterHealingSharkUseMode(PieceType affectedPieceEnum);
}