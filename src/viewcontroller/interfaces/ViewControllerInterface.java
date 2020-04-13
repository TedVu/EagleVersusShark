package viewcontroller.interfaces;

import java.beans.PropertyChangeListener;
import java.util.Map;

import javax.swing.AbstractButton;

import asset.PieceType;
import controller.MovePieceController;

/**
 * @author ted &#38; kevin
 *
 */
public interface ViewControllerInterface {

	/**
	 * @param buttonClicked
	 * @param movePieceController
	 */
	public void addListenerOnValidMovesCell(AbstractButton buttonClicked, MovePieceController movePieceController);

	/**
	 * @param listener
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener);

	/**
	 * @param buttonClicked
	 */
	public void enableAvailableMove(AbstractButton buttonClicked);

	/**
	 * @param buttonClicked
	 * @param newPos
	 */
	public void locateNewPos(AbstractButton buttonClicked, Map<String, Integer> newPos);

	/**
	 * 
	 */
	public void notifyNotStartGame();

	/**
	 * 
	 */
	public void notifySelectWrongTeam();

	/**
	 * @param pieceType
	 * @param movePieceController
	 */
	public void readdMovePieceController(PieceType pieceType, MovePieceController movePieceController);

	/**
	 * 
	 */
	public void restoreButtonStateForNextTurn();

	/**
	 * @param buttonClicked
	 */
	public void rollbackSelectedPiece(AbstractButton buttonClicked);

	/**
	 * @param pieceType
	 */
	public void updateBoardAfterChoosingPiece(PieceType pieceType);

	/**
	 * @param buttonClicked
	 * @param pieceType
	 */
	public void updateBoardMovingPiece(AbstractButton buttonClicked, PieceType pieceType);

	/**
	 * 
	 */
	public void updateBoardRollback();

}