package viewcontroller.facade;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Map;

import javax.swing.AbstractButton;

import controller.MovePieceController;
import model.enumtype.PieceType;
import viewcontroller.contract.ViewControllerInterface;

/**
 * @author kevin 7 ted
 *
 */
public class ViewControllerFacade implements ViewControllerInterface {

	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	@Override
	public void addListenerOnValidMovesCell(AbstractButton buttonClicked,
			MovePieceController movePieceController) {
		pcs.firePropertyChange("AddListenerValidMoveCell", movePieceController,
				buttonClicked);
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	@Override
	public void enableAvailableMove(AbstractButton buttonClicked) {
		pcs.firePropertyChange("EnableAvailableMove", null, buttonClicked);
	}

	@Override
	public void locateNewPos(AbstractButton buttonClicked,
			Map<String, Integer> newPos) {
		pcs.firePropertyChange("LocateNewPosition", buttonClicked, newPos);
	}

	@Override
	public void notifyNotStartGame() {
		pcs.firePropertyChange("NotifyNotStartGame", null, null);
	}

	@Override
	public void notifySelectWrongTeam() {
		pcs.firePropertyChange("NotifySelectWrongTeam", null, null);
	}

	@Override
	public void readdMovePieceController(PieceType pieceType,
			MovePieceController movePieceController) {
		pcs.firePropertyChange("ReaddMovePieceController", movePieceController,
				pieceType.toString());
	}

	@Override
	public void restoreButtonStateForNextTurn() {
		pcs.firePropertyChange("RestoreButtonStateForNextTurn", null, null);
	}

	@Override
	public void rollbackSelectedPiece(AbstractButton buttonClicked) {
		pcs.firePropertyChange("RollbackSelectedPiece", null, buttonClicked);
	}

	@Override
	public void updateBoardAfterChoosingPiece(PieceType pieceType) {
		pcs.firePropertyChange("UpdateBoardAfterChoosingPiece", null,
				pieceType.toString());
	}

	@Override
	public void updateBoardMovingPiece(AbstractButton buttonClicked,
			PieceType pieceType) {
		pcs.firePropertyChange("UpdateBoardMovingPiece", pieceType.toString(),
				buttonClicked);
	}

	@Override
	public void updateBoardRollback() {
		pcs.firePropertyChange("UpdateBoardRollback", null, null);
	}

}
