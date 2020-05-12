package modelcontroller.contract;

import java.util.Map;

import model.board.Cell;
import model.enumtype.PieceType;
import model.enumtype.TeamType;

/**
 * @author ted &#38; kevin
 *
 */
public interface ControllerModelInterface {

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

	public void updateModelStateProtectLeadership(PieceType affectedPieceEnum);

	public void updateModelAttackingEagleCapture(PieceType affectedPieceEnum,boolean isMode);

	public void updateModelStateAggressiveSharkCapture(PieceType affectedPieceEnum);

	public void updateModelStateDefensiveSharkMove(Cell newPos);

	public void updateModelStateDefensiveSharkProtect(PieceType affectedPieceEnum);

	public void updateModelStateHealingSharkRevive(PieceType affectedPieceEnum);

	public void updateModelAfterLeadershipUseMode();
	
	public void updateModelAfterAggressiveSharkUseMode(Cell newPos);
	
	public void updateModelAfterHealingSharkUseMode(PieceType affectedPieceEnum);
}