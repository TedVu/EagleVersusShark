package viewcontroller.contract;

import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.Map;

import javax.swing.AbstractButton;

import controller.playinggamecontroller.PlayerAction;
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

	/**
	 * @param buttonClicked
	 */

	public void getPlayerAction(PlayerAction playerAction);

	public void updateBoardAfterSwap(AbstractButton buttonClicked);

	public void updateBoardAfterCapture(AbstractButton btnClicked, PieceType pieceCapture);

	public void refreshBoard();

	public void resumeGame(TeamType currentTeam);

	public void confirmUndoSuccessful();

	public void updateBoardReviveSharkSuccessful(PieceType revivedPiece);

	public void updateBoardAfterHealingSharkUseMode();

	public void updateBoardBeforeCommitAction(ActionListener abilityController, PieceType animalType);

	public void updateBoardAfterProtect();

	public void updateBoardNotiDialog(String msg);

	public void updateBoardPauseGame();
}