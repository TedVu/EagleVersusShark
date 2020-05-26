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
 *         A facade or view APIs to expose methods on board view to controller
 *         to call
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
	 * @param playerAction
	 */
	public void getPlayerAction(PlayerAction playerAction);

	/**
	 * @param buttonClicked
	 */
	public void updateBoardAfterSwap(AbstractButton buttonClicked);

	/**
	 * @param btnClicked
	 * @param pieceCapture
	 */
	public void updateBoardAfterCapture(AbstractButton btnClicked, PieceType pieceCapture);

	/**
	 * 
	 */
	public void refreshBoard();

	/**
	 * @param currentTeam
	 */
	public void resumeGame(TeamType currentTeam);

	/**
	 * 
	 */
	public void confirmUndoSuccessful();

	/**
	 * @param revivedPiece
	 */
	public void updateBoardReviveSharkSuccessful(PieceType revivedPiece);

	/**
	 * 
	 */
	public void updateBoardAfterHealingSharkUseMode();

	/**
	 * @param abilityController
	 * @param animalType
	 */
	public void updateBoardBeforeCommitAction(ActionListener abilityController, PieceType animalType);

	/**
	 * 
	 */
	public void updateBoardAfterProtect();

	/**
	 * @param msg
	 */
	public void updateBoardNotiDialog(String msg);

	/**
	 * 
	 */
	public void updateBoardPauseGame();
}