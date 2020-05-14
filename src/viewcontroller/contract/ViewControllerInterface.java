package viewcontroller.contract;

import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.Map;

import javax.swing.AbstractButton;

import controller.playinggamecontroller.MovePieceController;
import controller.playinggamecontroller.PlayerAction;
import model.board.Cell;
import model.enumtype.PieceType;
import model.enumtype.TeamType;

/**
 * @author ted &#38; kevin
 *
 */
public interface ViewControllerInterface {

	/**
	 * @param listener
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener);

	/**
	 * @param buttonClicked
	 * @param newPos
	 */
	public void locateNewPos(AbstractButton buttonClicked, Map<String, Integer> newPos);

	/**
	 * @param buttonClicked
	 * @param pieceType
	 */
	public void updateBoardAfterMovingPiece(AbstractButton buttonClicked, PieceType pieceType);

	public void updateBoardBeforeMovePiece(AbstractButton buttonClicked, MovePieceController movePieceController);

	/**
	 * @param buttonClicked
	 */
	public void updateBoardSelectAnotherPiece(AbstractButton buttonClicked);

	public void getPlayerAction(PlayerAction playerAction);

	public void updateBoardAfterSwap(AbstractButton buttonClicked);

	public void updateBoardChangeAction();

	public void updateBoardAfterCapture(AbstractButton btnClicked, PieceType pieceCapture);

	public void undoMoveCancelTimer();

	public void resumeGame(TeamType currentTeam);

	public void confirmUndoSuccessful();

	public void updateBoardAfterDefensiveSharkMoveAbility(AbstractButton btnClicked, Cell newPos);

	public void updateBoardReviveSharkSuccessful(PieceType revivedPiece);

	public void updateBoardAfterLeadershipUseMode();

	public void updateBoardAfterAggressiveSharkUseMode(AbstractButton movedBtn, Cell newPos);

	public void updateBoardAfterHealingSharkUseMode();

	// rework
	public void updateBoardBeforeUseSpecialBehaviour(ActionListener abilityController, PieceType animalType);

	public void updateBoardAfterProtect();

	public void updateBoardErrorAction(String errMsg);
}