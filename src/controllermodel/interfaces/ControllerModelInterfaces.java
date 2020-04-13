package controllermodel.interfaces;

import java.util.Map;

import asset.PieceType;
import asset.TeamType;

/**
 * @author ted &#38; kevin
 *
 */
public interface ControllerModelInterfaces {

	/**
	 * @param newPos
	 * @param pieceType
	 */
	public void updateModelAfterMovingPiece(Map<String, Integer> newPos, PieceType pieceType);

	/**
	 * @param teamName
	 */
	public void updateModelStateForNextTurn(TeamType teamName);
}