package modelcontroller.contract;

import java.util.Map;

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

	public void updateModelStateAttackingEagle(PieceType affectedPieceEnum);
}