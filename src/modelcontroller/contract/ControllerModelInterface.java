package modelcontroller.contract;

import java.util.Map;

import model.enumtype.PieceType;
import model.enumtype.TeamType;
import model.player.Player;

/**
 * @author ted &#38; kevin
 *
 */
public interface ControllerModelInterface {

	public Player getCurrentActivePlayer();

	public Player getInitialActivePlayer();

	public boolean getGameCurrentlyRunning();

	public void cancelTimerPauseGame();

	public void setTurnStartingGame(TeamType teamType);
	
	public void setResumeGame();

	public void setAlreadyUseUndo();

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

	public void updateModelStateSwapPiece(PieceType affectedPieceEnum);

	public void updateModelStateProtectPiece(PieceType affectedPieceEnum, PieceType piecetProtect);

	public void updateModelAttackingEagleCapture(PieceType affectedPieceEnum, boolean isMode);

	public void updateModelStateAggressiveSharkCapture(PieceType affectedPieceEnum);

	public void updateModelStateHealingSharkRevive(PieceType affectedPieceEnum);

	public void updateModelAfterHealingSharkUseMode(PieceType affectedPieceEnum);
}