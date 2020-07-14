package modelcontroller.contract;

import java.util.List;
import java.util.Map;

import model.contract.Piece;
import model.contract.Player;
import model.enumtype.PieceType;
import model.enumtype.TeamType;

/**
 * @author ted &#38; kevin
 * 
 *         A facade or model APIs to expose methods on model to controller to
 *         call - a way to minimize coupling between model and controller and
 *         hence minimize unwanted manipulation of model. Any method call on
 *         model from controller has to be made via this interface
 */
public interface ControllerModelInterface {

	/**
	 * 
	 */
	public void cancelTimerPauseGame();

	/**
	 * @param pieceType
	 * @return
	 */
	public boolean checkCorrectTurnOfSelectedPiece(PieceType pieceType);

	/**
	 * @return activeSharks
	 */
	public List<Piece> getActiveSharks();

	/**
	 * @return
	 */
	public Player getCurrentActivePlayer();

	/**
	 * @return
	 */
	public boolean getGameCurrentlyRunning();

	/**
	 * @return
	 */
	public Player getInitialActivePlayer();

	/**
	 * @return numPiece
	 */
	public int getNumPiece();

	/**
	 * 
	 */
	public void setAlreadyUseUndo();

	/**
	 * 
	 */
	public void setResumeGame();

	/**
	 * @param teamType
	 */
	public void setTurnStartingGame(TeamType teamType);

	/**
	 * @param affectedPieceEnum
	 */
	public void updateModelAfterHealingSharkUseMode(PieceType affectedPieceEnum);

	/**
	 * @param newPos
	 * @param pieceType
	 */
	public void updateModelAfterMovingPiece(Map<String, Integer> newPos, PieceType pieceType);

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
	 * @param teamName
	 */
	public void updateModelStateForNextTurn(TeamType teamName);

	/**
	 * @param affectedPieceEnum
	 */
	public void updateModelStateHealingSharkRevive(PieceType affectedPieceEnum);

	/**
	 * @param affectedPieceEnum
	 * @param piecetProtect
	 */
	public void updateModelStateProtectPiece(PieceType affectedPieceEnum, PieceType piecetProtect);

	/**
	 * @param affectedPieceEnum
	 */
	public void updateModelStateSwapPiece(PieceType affectedPieceEnum);
}